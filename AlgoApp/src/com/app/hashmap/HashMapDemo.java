package com.app.hashmap;
import java.util.Map;
import java.util.HashMap;

class HashMapDemo{
         
   public static void main(String[] args){
      
      Map<Character, String> map = new HashMap<>();
       
      map.put('a', "aspirin");  // insert one key, value pair
             
      System.out.println(map.get('a')); // get value by passing key 
            
      map.put('a', "airbus"); // insert another value with already existing key -- the value gets overwritten
       
      System.out.println(map.get('a'));
       
      System.out.println(map.containsKey('A')); // check if map contains key
       
      System.out.println(map.containsValue("airbus")); // check if map contains key
    
      System.out.println(map.entrySet()); // get all map entries
       
      //checks equals condition if obj is also a map - returns boolean 
      Map<Character, String> obj = new HashMap<>();
            obj.put('a', "airbus");
      System.out.println(map.equals(obj));
        
      System.out.println(map.hashCode()); // gets hashcode of map object 
       
      System.out.println(map.isEmpty()); //check if map is empty - returns boolean 
       
      System.out.println(map.keySet()); // get all keys stored in the map object 
       
      System.out.println(map.values()); // get all values stored in map object 
      
      System.out.println(map.size()); // get size of the map in int
       
      map.remove('a'); // remove key-value pair by passing the key        
      System.out.println(map.entrySet());
                
      Map<Character, String> obj2 = new HashMap<>();
            obj2.put('a', "airbus");
            obj2.put('b', "boeing");      
      map.put('a', "airbus"); // adding one single key-value pair
      map.putAll(obj2);  // adding the key-value pair from another map -- identical values gets overwritten    
      System.out.println(map.entrySet());
      
      map.clear(); // delete all key-value entries from map object
      System.out.println(map.entrySet());
         
   }

}