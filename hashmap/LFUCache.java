package com.app.hashmap;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// LC 460. LFU Cache
// https://leetcode.com/problems/lfu-cache/

/*
 
    Approach : 
    
    Why simply moving to the front won't work for LFU:
    LFU prioritizes frequency, not recency: When an item is accessed, its frequency increases. If you simply move it to the front of a DLL based on access (like an LRU), the DLL will still be ordered by recency of access, not by frequency. A less frequently used item that was just accessed would move to the front, even if many other items have a higher overall frequency.


    Pitfall 1 : Have two maps, one for <key,value> and one for <key, freq>. : 
        How to get least frequently used ?  How about multiple entries with same frew ?
    
    Soln : Stick to 2 Maps, 
        1. Map <key,value> and 2. Map <freq, key>.
        2. Map<freq, set>, linked hash set to maintain order.(Helps in tie breaker situation) 

    Pitfall 2 : Now after populating both maps, how to update a key-value pair ? How to know what
         Map <frequency, set> entry they are stored at ??

    Soln : Use a Node (key, value, freq).
        1. Map<key, Node> 
        2. Map<freq, Set<Node>>        
     */

  

class LFUCache {
  
    int capacity;

    // key, nodes
    Map<Integer,Node> cache = new HashMap<>();
    
    // freq, nodes
    Map<Integer, Set<Node>> freqMap = new HashMap<>(); 

    int minFrequency; // pointer to lowest freq values in the freqMap. Makes it easier to evict

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {

        if(cache.containsKey(key)){

            Node node = cache.get(key);

            // Update frew
            updateFreqMap(node);

            return node.value;

        }   
         
         else return -1;
        
    }
    
    public void put(int key, int value) {
      
        if (capacity == 0) return;

        if(cache.containsKey(key)){
                       
            // Update the new value in cache and then update frequency in freq map
            Node node = cache.get(key);
            node.value = value; // updated value
            updateFreqMap(node);
        }

        else {

            // Capacity check
            if(cache.size() == capacity) {
                // remove least freq used
                evict();
            }   
            
            // Insert node into cache and freq map
            Node node = new Node(key, value, 1);
            cache.put(key, node);

            // Here frequency is the key    
            freqMap.putIfAbsent(1, new LinkedHashSet<>()); // LinkedHashSet to maintain order of insertion
            freqMap.get(1).add(node);

            minFrequency = 1; // Useful when evicting least frequently used 
        }
        
    }

    // Updates frequency count in freqMap
    private void updateFreqMap(Node n){
        
        int curFreq =  n.freq;  

        Set<Node> set = freqMap.get(curFreq);
        set.remove(n); // LinkedHashSet - hence just updating n.freq will be incorrect 

        //cleanup and update minimum freq
        if(set.isEmpty()){

            // delete empty set
            freqMap.remove(curFreq);

            // Current min freq happens to be current freq of node/empty set, needs to be incremented to next    
            if(minFrequency == curFreq) minFrequency++; // Next bigger freq

        } 

        // Increase freq and re-insert into freq map
        n.freq++;

        freqMap.putIfAbsent(n.freq, new LinkedHashSet<>());
        freqMap.get(n.freq).add(n);      
        
    }

    private void evict(){

        Set<Node> set = freqMap.get(minFrequency);
                
        // Remove first node
        Node firstNode = set.iterator().next();
        set.remove(firstNode);

        //cleanup empty set 
        if(set.isEmpty()) freqMap.remove(minFrequency);
        
        // Finally evict from cache as well
        cache.remove(firstNode.key);
    }


    class Node {

        int key;
        int value;
        int freq;
        
        public Node(int k, int v, int f){
            key = k;
            value = v;
            freq = f;
        }

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



public class LFUCacheOld {

    
    // Key, Node(key,value,freq)
    Map<Integer,Node> cache;
    // freq, Node(key,value,freq)
    Map<Integer, LinkedHashSet<Node>> freqMap;

    int capacity;
    int minFrequency;

    public LFUCache(int capacity) {
        
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();        
        this.capacity = capacity;
        this.minFrequency = 0;
    }
    
    public int get(int key) {

        if(!cache.containsKey(key)) return -1;
        
        Node node = cache.get(key);

        //node.frequency++; Can't read old freq from freqMap if freq is incremented here

        updateFreqMap(node);

        return node.value;

    }
    
    
    public void put(int key, int value) {
             
        // 1. Start with containsKey condition (easy to update existing node, 
        //dont have to deal with max capacity, <freq, set> not populated etc)
        // 2. Then deal with capacity full condition - Perform evict.
        // 3. Post eviction, add new node condition
        
        if(cache.containsKey(key)){

            Node node = cache.get(key);
            node.value = value;

            updateFreqMap(node); 
            return;  // Otherwise will continue to step 3- add new node.          
        } 

        if(isFull()) evict();  

        // Add new node 
        Node n = new Node(key, value,1);

        cache.put(key,n);            

        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(n);

        minFrequency = 1; // Next easy canditate to be evicted             
        
    }

    private void updateFreqMap(Node node) {
       
        int curFreq = node.frequency;

        freqMap.get(curFreq).remove(node);

        // Remove empty list to avoid min frequency pointing to empty set
        if (freqMap.get(curFreq).isEmpty()) {

            freqMap.remove(curFreq);
            
            if (minFrequency == curFreq) {
                minFrequency++;
            }
        }
        
        // Finally update freq here
        node.frequency++;

        freqMap.computeIfAbsent(node.frequency, k -> new LinkedHashSet<>()).add(node);
    }



    private void evict(){

        // cache is full & doesn't contain Node to be inserted

        // Get least used nodes & delete the first one
        Set<Node> nodes = freqMap.get(minFrequency);

        Node firstNode = nodes.iterator().next();

        nodes.remove(firstNode);

        // To handle empty set issue.
        if(nodes.isEmpty()) freqMap.remove(minFrequency);    
     
        cache.remove(firstNode.key);            

    }
    

    private boolean isFull(){
        
        return cache.size() >= capacity;
        
    }

    class Node {

        int key;
        int value;
        int frequency;

        public Node(int key, int value, int frequency){
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

}


/**
 * 
 * ip 1 : ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
 * op 1 : [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
 *
 * ip2 : ["LFUCache","get","put","get","put","put","get","get"]
 * op2 : [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
 *  
 */

 /**

    Intution : 

    Pitfall 1 : Have two maps, one for <key,value> and one for <key, freq>. : 
        How to get least frequently used ?  How about multiple entries with same frew ?
    
    Soln : Stick to 2 Maps, 
        1. Map <key,value> and 2. Map <freq, key>.
        2. Map<freq, set>, linked hash set to maintain order.(Helps in tie breaker situation) 

    Pitfall 2 : Now after populating both maps, how to update a key-value pair ? How to know what
         Map <frequency, set> entry they are stored at ??

    Soln : Use a Node (key, value, freq).
        1. Map<key, Node> 
        2. Map<freq, Set<Node>>        
     */

    // Start with put method, then work on get. 