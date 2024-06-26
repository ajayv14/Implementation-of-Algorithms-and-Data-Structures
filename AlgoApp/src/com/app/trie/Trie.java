package com.app.trie;


// Use hasmap to store trienode children ??

class Trie {

    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {       
        root = new TrieNode();        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        
        /*pointer to root TrieNode*/
        TrieNode cur = root;
        
        for(char ch : word.toCharArray()){
                      
            if(cur.getChild(ch) == null){
                
                cur.insert(ch);               
            }            
            
            cur = cur.getChild(ch); // set new trie node as cur & also creates a new TrieNode        
        }      
        
        cur.setIsWordComplete(true); // mark word as complete
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
                
        TrieNode cur = root; // get reference to root Trie Node
        
        for(char c : word.toCharArray()){
            
            if(cur.getChild(c) == null) return false;            
            cur = cur.getChild(c); // go one level deeper            
        }        
               
        return cur.getIsWordComplete();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    /* essentially same implementation a search function, but does not check for comepleteness of a word*/
    public boolean startsWith(String prefix) {
        
        TrieNode cur = root;
        
        for(char c : prefix.toCharArray()){
            
            if(cur.getChild(c) == null) return false;            
            cur = cur.getChild(c); // go one level deeper            
        }  
              
        return true;
    }
    
    
    /*Trie Node*/
    class TrieNode {
        
        TrieNode[] node;
        boolean isWordComplete = false;
                        
        public TrieNode(){
            node = new TrieNode[26];        
        }        
        
        public void insert(char c){
            node[c-'a'] = new TrieNode();            
        }
        
        
        public TrieNode getChild(char c){         
            return node[c-'a'];            
        }   
        
        //
        public void setIsWordComplete(boolean flag){
            this.isWordComplete = flag;
        }
        
        public boolean getIsWordComplete(){
            return this.isWordComplete;
        }
        
    }  
    
    
    
    
    
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
