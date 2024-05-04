package com.app.trie;

//credits: https://leetcode.com/problems/longest-word-in-dictionary/discuss/560359/Java-HashSet-intuitive-solution

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class LongestWordInDictionary {
   
    
    
    
    
    /*test*/ 
    public static void main(String[] args){
      
      //input
      //String[] dict = {"ogz","eyj","e","ey","hmn","v","hm","ogznkb","ogzn","hmnm","eyjuo","vuq","ogznk","og","eyjuoi","d"};  //output: eyj
      String[] dict = {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};  //output:  latte
         
      LongestWordInDictionary obj = new LongestWordInDictionary();
      String result = obj.longestWord(dict);
         
      System.out.println(result);   
         
         
         
         
         
    }
    
    
}