package com.app.trie;

import java.util.Map;
import java.util.TreeMap;

public class WordDictionarySearchAlt1 {


    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionarySearchAlt1() {
        
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        
        TrieNode node = root;
        
        for(char c : word.toCharArray()){            
           
            node.subNode.putIfAbsent(c, new TrieNode());
            
            node = node.subNode.get(c);                       
        }
        
        node.isComplete = true;        
    }
    
    public boolean search(String word) {
        
       return dfs(root,word); 

    }

    
    public boolean dfs(TrieNode root, String word){
        
        TrieNode node = root;
                
        int d = 0;
        
        for(char c : word.toCharArray()){
            
                       
            if(c == '.'){
                
                for(char key : node.subNode.keySet()){               
                                                          
                    if(dfs(node.subNode.get(key), word.substring(d + 1))) return true;    
                                                           
                }        
                
                return false;
            }                        
             
            else if(node.subNode.get(c) == null) return false;                                  
            
            node = node.subNode.get(c);
            d++;
        }   
        
        return node.isComplete;
        
    }    
    

    static class TrieNode {
               
        Map<Character, TrieNode> subNode = new TreeMap<>();
        boolean isComplete = false;     
        
    }
}
