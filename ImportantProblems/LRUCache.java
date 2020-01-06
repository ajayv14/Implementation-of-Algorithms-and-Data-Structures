//  credits 1 : nick white : https://www.youtube.com/watch?v=NDpwj0VWz1U&t=752s 
//  credits 2 : leetcode : https://leetcode.com/problems/lru-cache/ 

import java.util.Map;
import java.util.HashMap;

class LRUCache {

   private final Node head;
   private final Node tail;
   private int cacheCapacity; // to keep track of cache size
   private Map<Integer, Node> map; // stores key, Node to acheieve get() in O(1) time
   
   public LRUCache(int capacity){
      
      this.cacheCapacity = capacity; 
      map = new HashMap(capacity);   
      
      /*initailize the double linked list*/
      head = new Node();
      tail = new Node();
      head.next = tail;
      tail.prev = head;
   }
   
   /*get recently used item from cache*/
   public int get(int key){
      
      int result = -1; // if doesn't exist return -1
            
      if(map.containsKey(key)){
         
         Node node = map.get(key); // get the node and read the value
         result = node.val;
         
         // update the linked list, remove the node and insert at front - recently used 
         remove(node);
         add(node);         
      }      
      return result; 
   }
   
   
   /*chech capacity and insert/update cache*/
   public void put(int key, int value){
      
      // if exists, then update
      
      if(map.containsKey(key)){
         
         Node node = map.get(key);
         node.val = value; // value updated
         
         /*update the list and map*/
         remove(node);
         add(node);
         map.put(key,node);            
      }
      
      else {
         
         //remove last node if capacity is full
         if(cacheCapacity == map.size()){
            
            map.remove(tail.prev.key);
            remove(tail.prev);// remove from list as well             
         }
         
         Node node = new Node(key,value);
         add(node);
         map.put(key,node); // update map                  
      }      
      
   }
   
   /*auxillary method to add a new node at front of list*/
   private void add(Node node){
      
      Node tempNext = head.next;
      head.next = node;
      node.prev = head;
      node.next = tempNext;
      tempNext.prev = node;      
   }
   
   /*Auxillary method to remove node from end of list*/
   public void remove(Node node){
      
      Node tempNext = node.next;
      Node tempPrev = node.prev;
      
      tempNext.prev = tempPrev;
      tempPrev.next = tempNext;
        
   }
   
   public static void main(String[] args){
      
      LRUCache cache = new LRUCache(2);
         cache.put(1, 1);
         cache.put(2, 2);
         System.out.println(cache.get(1)); // returns 1

         cache.put(3, 3); // evicts key 2
         System.out.println(cache.get(2)); // returns -1 (not found)

         cache.put(4, 4);    // evicts key 1
         
         System.out.println(cache.get(1)); // returns -1 (not found)
         System.out.println(cache.get(3)); // returns 3
         System.out.println(cache.get(4)); // returns 4        
   }   
}




/*using custom double linked list*/
class Node {
   
   Node next;
   Node prev;
   int key;
   int val;
   
   public Node(){
   
   }   
   
   public Node(int key,int val){
      this.key = key;
      this.val = val;
   }
}