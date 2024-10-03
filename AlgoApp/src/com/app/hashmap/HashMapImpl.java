package com.app.hashmap;

import java.util.*;

public class HashMapImpl<K,V> {

    List<ListNode<K,V>>[] map;
           
    public HashMapImpl(int size) {
        map = new ArrayList[size];

        for(int i = 0; i < map.length; i++){
            map[i] = new ArrayList<>();
        }
    }

    public void put(K key,V value){

        int hash = hash(key);
        System.out.println("hash : " + hash);
        map[hash].add(new ListNode<K,V>(key, value));
    }

    public V get(K key){
        
        int hash = hash(key);
        
        List<ListNode<K,V>> values = map[hash];

        for(ListNode<K,V> n : values){

            if(n.key.equals(key)) return n.value;

        }

        return null;
    }   
    
    public int hash(K key){
        return (int) key / map.length;
    }



    public class ListNode<K,V> {
        
         K key;
         V value;

         public ListNode(K key, V value){
            this.key = key;
            this.value = value;
         }

    }



    public static void main(String[] args) {
        HashMapImpl<Integer, String> hashMap = new HashMapImpl(10);

        hashMap.put(12, "alpha");
        hashMap.put(12, "beta");
        hashMap.put(10, "alpha");
        
       // List<ListNode> l = hashMap.map[1];

        //System.out.println(hashMap.map[1].toString());

        //System.out.println(hashMap.get(12));
    }


}


