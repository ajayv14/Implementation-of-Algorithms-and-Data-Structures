package com.app.doublylinkedlist;
//  credits 1 : nick white : https://www.youtube.com/watch?v=NDpwj0VWz1U&t=752s 
//  credits 2 : leetcode : https://leetcode.com/problems/lru-cache/ 

/*Logic : cache - fixed size queue and recently used items are in front and least used are are at end of list. So we maintain a Doubly Linked List.
      Operations: get(key)--> value in O(1). So we maintain a hashmap and when the key is accessed, then we re insert the Node(key,value) back into the front of queue.
                  put(key,value) --> we create a Node and insert into front of D. linked list(Deque), then make an entry/modify entry in hashmap. 

 */


import java.util.Map;
import java.util.HashMap;

class LRUCache {


   Map<Integer,DLLNode> map;

    DLLNode head = null;
    DLLNode tail = null; 
    int capacity;

    public LRUCache(int capacity) {        
        map = new HashMap<>(capacity);   
        this.capacity = capacity;    
    }
            
    public void put(int key, int value) {

        if(map.containsKey(key)){

            DLLNode node = map.get(key);

            findAndRemove(node);            
            
            // Update new value
            DLLNode newNode = new DLLNode(key, value);
            addFront(newNode);
            map.put(key,newNode);

        }

        else {

            DLLNode node = new DLLNode(key,value);

            if(map.size() >= capacity){

                DLLNode removed = removeLast();
                map.remove(removed.key);
            }                  

            addFront(node);   
            map.put(key, node);                     
        }       

    }


    public int get(int key) {

        if(map.containsKey(key)){
                
            DLLNode node = map.get(key);          
            int val = node.value;
            findAndRemove(node);           
            addFront(node);
            return val;
        }

        return -1;
        
    }

    public void addFront(DLLNode node){

        if(head == null){
            head = tail = node;                              
        } 

        else {
            node.next = head;
            head.prev = node;
            head = node;            
        }                   
    }

    public DLLNode removeLast(){

        if(tail == null) return null;

        else if(head == tail){
            DLLNode d = head;
            head = tail = null;
            return d;
        }
        else {
            DLLNode d = tail;    
            tail = tail.prev;
            tail.next = null;
            return d;
        }            
             
    }


    public void findAndRemove(DLLNode node) {
               
        if (head == null) return;
        
        else if(head == tail){
            head = tail = null;
        }
    
         // Check if the node to be removed is the head
        else if (head == node) {
          
            head = head.next;
            head.prev = null;           
        }

        // Check if the node to be removed is the tail
        else if (tail == node){
            tail = tail.prev;
            tail.next = null;            
        }

        // Somewhere in middle
        else {

            node.prev.next = node.next;
            node.next.prev = node.prev;          

        }
    
    
}




    class DLLNode {
        int key;
        int value;
        DLLNode next;
        DLLNode prev;

        public DLLNode(int key, int value){
            this.key = key;
            this.value = value;            
        }   }

   
}