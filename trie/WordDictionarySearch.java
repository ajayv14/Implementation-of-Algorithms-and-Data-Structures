package com.app.trie;

//credit: https://leetcode.com/problems/design-add-and-search-words-data-structure
// credit : https://leetcode.com/problems/design-add-and-search-words-data-structure/discuss/995936/Java-Straightforward-and-Clean

class WordDictionarySearch {

    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionarySearch() {
        
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        
        TrieNode node = root;
        
        for(char c : word.toCharArray()){            
           
            if(node.getChild(c) == null)  node.insert(c);
            
            node = node.getChild(c);                       
        }
        
        node.setIsComplete();        
    }
    
    /*use dfs instead of just basic trie search*/
    public boolean search(String word) {
        
       return dfs(root,word); 
       
    }

    
    public boolean dfs(TrieNode root, String word){
        
        TrieNode node = root;
        
        
        int d = 0;
        
        for(char c : word.toCharArray()){
            
                       
            if(c == '.'){
                
                for(int i = 0; i < 26; i++){               
                    
                    if(node.node[i] != null){
                        
                        if(dfs(node.node[i], word.substring(d + 1))) return true;                        
                    }                    
                    
                }         
                
                return false;
            }
                
                          
            else if(node.getChild(c) == null) return false;
                                  
            
           node = node.getChild(c);
            d++;
        }   
        
        return node.getIsComplete();
        
    }
    
    



    class TrieNode {
        
        
        TrieNode[] node;
        boolean isComplete = false;
        
        
        public TrieNode(){
            
            node = new TrieNode[26];          
            
        }
        
        
        public void insert(char c){        
            
            node[c - 'a'] = new TrieNode();
            
        }
        
        public TrieNode getChild(char c){
            
            return node[c - 'a'];            
        }
        
        
        public void setIsComplete(){
            
            this.isComplete = true;
        }
        
        
        public boolean getIsComplete(){
            return this.isComplete;
        }
    }

}



