package com.app.hashmap;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// LC 460. LFU Cache
// https://leetcode.com/problems/lfu-cache/

public class LFUCache {

    
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