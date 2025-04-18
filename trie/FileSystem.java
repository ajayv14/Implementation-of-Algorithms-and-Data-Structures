package com.app.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/design-in-memory-file-system/
//588. Design In-Memory File System

// credits : https://leetcode.com/problems/design-in-memory-file-system/solutions/4333653/easy-to-implement-use-trienode/


//Use a Trie node.
//Most operations involve splitting given path : /a/b/c into individual strings and then traversing the TrieNode   


class FileSystem {

   TrieNode root;

   public FileSystem() {

       root = new TrieNode();
   }

   
   public List<String> ls(String path) {  

       String[] dirs = path.split("/");
       
       TrieNode node = root;

       List<String> res = new ArrayList<>();

       for(int i = 1; i < dirs.length; i++){

           node = node.subNode.get(dirs[i]);        
       }

       if(node.isFile) res.add(dirs[dirs.length - 1]); // Just add the file name 
               

       else {

           for(String key : node.subNode.keySet()){                                  
               res.add(key);
           } 
       }       
           
       return res;
   }
   
   
   public void mkdir(String path) {
       
      String[] dirs = path.split("/");

      TrieNode node = root;

      for(int i = 1; i < dirs.length; i++){

           node.subNode.putIfAbsent(dirs[i], new TrieNode());
           node = node.subNode.get(dirs[i]); // move node pointer
      }    

   }
   
   public void addContentToFile(String filePath, String content) {     
        
      String[] dirs = filePath.split("/");

      TrieNode node = root;

      for(int i = 1; i < dirs.length; i++){

           node.subNode.putIfAbsent(dirs[i], new TrieNode()); // Missing dir, create a dir
          
           node = node.subNode.get(dirs[i]);            
      }    
          
      node.isFile = true;
      node.content += content;
       
   }

   
   public String readContentFromFile(String filePath) {

      String[] dirs = filePath.split("/");

      TrieNode node = root;

      for(int i = 1; i < dirs.length; i++){
          
           node = node.subNode.get(dirs[i]);            
      }              

      return node.content;              
   }


   
   
   class TrieNode {

       Map<String, TrieNode> subNode = new TreeMap<>(); // Lexicographic ordering

       boolean isFile;

       String content = "";         
   }


   
}

/**
* Your FileSystem object will be instantiated and called as such:
* FileSystem obj = new FileSystem();
* List<String> param_1 = obj.ls(path);
* obj.mkdir(path);
* obj.addContentToFile(filePath,content);
* String param_4 = obj.readContentFromFile(filePath);
*/