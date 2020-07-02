
/**
  * logic : 
  * - We insert the given array of words in a trie
  * - We search word by word if it is present in trie, while doing so we compare each char and its children recursively (one char at time).
  * - To make sure the word is built one char at a time, we also check if word is complete at each char instance. 
  **/  

class LongestWordInDictionary {
    public String longestWord(String[] words) {
        
        /*create an instance of Trie*/
        Trie trie = new Trie(); 
        String maxLengthWord = "";
        
        /*populates the trie with words*/
        for(String word : words){                
           trie.insert(word);            
        }           
        
        /*checks if each word is present in the Trie*/
        /*when each char is checked against trie node, we also check if wordComplete is true for each char, */
        for(String word : words){
            if(trie.search(word)){
                
                /*checks for two possibilities: 1) The word length > max Word length. 2) or if lengths are equal, then apply lexicographical order*/
                if(word.length() > maxLengthWord.length() || (word.length() == maxLengthWord.length() && word.compareTo(maxLengthWord) < 0)){
                
                    maxLengthWord = word;
                    //System.out.println(maxLengthWord);                  
                    
                    
                }               
            }  
        }       
        
        return maxLengthWord;        
        
    }                  
        
 /*Trie class, to construct Trie*/       
 class Trie {
        
     TrieNode root;
        
      public Trie(){
           root = new TrieNode();
       }
        
        /** inserts a word into trie **/
        public void insert(String word){
            
            TrieNode node = root;
            
            for(char c : word.toCharArray()){
                if(node.getChild(c) == null){
                    node.insert(c);
                }
                node = node.getChild(c);
            }
            
            node.setIsWordComplete(true);            
        }     
        /*searches for a word and if found, returns true*/
        public boolean search(String word){
            
            TrieNode node = root;
            
            for(char c : word.toCharArray()){
                
                if(node.getChild(c) == null ) return false;
                node = node.getChild(c); 
                if(!node.getIsWordComplete()) return false; // modified to check if each char is also a word
                
            }
            
            return node.getIsWordComplete();             
        }      
    }
    
    
    /*classic Trie Node*/
    class TrieNode {
        
        TrieNode[] node;        
        boolean isWordComplete = false;
        
        public TrieNode(){
            node = new TrieNode[26];
        }
        
        
        public void insert(char c){
            node[c - 'a'] = new TrieNode(); 
        }
        
        public TrieNode getChild(char c){
            return node[c - 'a'];            
        }
        
        public void setIsWordComplete(boolean status){
            this.isWordComplete = status;
        }
        
        public boolean getIsWordComplete(){
            return this.isWordComplete;
        }
        
    }  
    
    
    /*Test*/
    public static void main(String[] args){
        
        String[] input =  {"a","banana","app","appl","ap","apply","apple"}; // expected output : "apple"
                
        LongestWordInDictionary obj = new LongestWordInDictionary();
        String result = obj.longestWord(input);        
        System.out.println(result);
    }
    
    
}