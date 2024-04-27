package com.app.trie;

//credits: https://leetcode.com/problems/longest-word-in-dictionary/discuss/560359/Java-HashSet-intuitive-solution

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class LongestWordInDictionary {
   
      /*Logic : Apple in order to be longest should contain a..ap..app..appl..apple in dict. We first sort the array as it gives us in lexicographical order.
                Then we add all words into a set...check if previous combination exists in set..like while checking for ap, check for a, ..for apple..check if appl exists in set.
                Optimized version - we don't add everything into set at once..we add as and when we check if previus combination exists */
   
    public String longestWord(String[] words) {
        
              
        Set<String> set = new HashSet<>();        
   
        Arrays.sort(words);
        
        // maxCount = 0;
        String longest = "";
        
        /*for(String word : words){  // further optimized by not adding all the words into Set in one shot
            set.add(word);
        }*/        
        
        for(String word : words){
            
           //int count = 0;           
            
           /*for(int i = 0; i < word.length(); i++){ 
                                           
               if(set.contains(word.substring(0,i+1))) count++;               
               else break;
               
                if(count > maxCount){
                maxCount = count;
                longest = word;
            }                 
            }*/            
            
            if(word.length() == 1|| set.contains(word.substring(0,word.length() - 1))){
                
                if(word.length() > longest.length()){
                    longest = word;                    
                }
                
                set.add(word);
            }        
        }    
        
        return longest;
    }
    
    
    
    
    
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