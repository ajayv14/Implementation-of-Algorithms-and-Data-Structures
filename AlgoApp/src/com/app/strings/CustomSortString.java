package com.app.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// LC 791 : https://leetcode.com/problems/custom-sort-string

public class CustomSortString {


    // 1.  Non - optimized solution

    // Order string -> Add character and its index to a map. 
    // Use this id/index to sort string s using custom comparator based on map values
    public String customSortString(String order, String s) {
        
        // Character, Index
        Map<Character, Integer> sortOrder = new HashMap<>();

        int id = 0;

        for(char c : order.toCharArray()){

            sortOrder.put(c, id++);
        }

        char[] arr = s.toCharArray(); // Cant directly sort using Arrays.sort()
        Character[] a = new Character[arr.length];


        for (int i = 0; i < arr.length; i++) {
            a[i] =  arr[i];
        }
       

        Arrays.sort(a,(x,y) -> checkMap(x, sortOrder) - checkMap(y, sortOrder));

        StringBuilder sb = new StringBuilder();

        for(char c : a) sb.append(c);
        
        return sb.toString();

    }

    private int checkMap(char c, Map<Character,Integer> sortOrder){

        if(sortOrder.containsKey(c)) return sortOrder.get(c);

        else return 100;
    }


    

    // 2. Optimized version using frequency hash map
    // Example  order - "abcx" s - "cbdab" ; Expected -> abbcd
    // Approach 

    //Count frequence of occurence of each word in s
    // cbdab -> c = 1, b = 2, a = 1, d = 1 
    // Pick order from order string "abcx" using pointer, get freq from map and apply to s : a  bb  c d
    
    public String customSortStringOpt1(String order, String s) {
        
        StringBuilder res = new StringBuilder();    

        Map<Character, Integer> freq = new HashMap<>();

        for(char c : s.toCharArray()){
                
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }    

        for(int l = 0; l < order.length(); l++){

            char co = order.charAt(l); 

            if(freq.containsKey(co)) {

                int occurence = freq.get(co);
                freq.remove(co);

                for(int i = 0; i < occurence; i++){
                    res.append(co);
                }            
            }
        }    

        for(char key : freq.keySet()){

            int occurence = freq.get(key);

            for(int i = 0; i < occurence; i++){

                res.append(key);
            }
        }

        return res.toString();
    }        


    // 3. Optimized using array to store freq


     //Count frequence of occurence of each word in s
    // abbc -> a = 1, b = 2, c = 1
    // Pick order from order string "abcx" using pointer, get freq from map and apply to s -> a, bb, c
    // Converting int to alphabet character : (char) ('a' + num - 1)
    
    public String customSortStringOpt2(String order, String s) {
        
        StringBuilder res = new StringBuilder();    

        int[] freq = new int[26];

        for(char c : s.toCharArray()){

            freq[c - 'a']++;      
           
        }    

        for(int l = 0; l < order.length(); l++){

            char co = order.charAt(l); 

            if(freq[co - 'a'] > 0) {

                int occurence = freq[co - 'a'];
                
                freq[co - 'a'] = 0;

                for(int i = 0; i < occurence; i++){
                    res.append(co);
                }            
            }
        }    

        for(int j = 0; j < freq.length; j++){

            int occurence = freq[j];

            if(freq[j] > 0){

                for(int i = 0; i < occurence; i++){

                    res.append((char) ('a' + j)); // (char) ('a' + j - 1) will not work as we srat j from 0 not 1
                }
            }            
        }

        return res.toString();
    }        


}
