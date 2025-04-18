package com.app.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * logic :
 * - We insert the given array of words in a trie
 * - We search word by word if it is present in trie, while doing so we compare
 * each char and its children recursively (one char at time).
 * - To make sure the word is built one char at a time, we also check if word is
 * complete at each char instance.
 **/

class LongestWordInDictionary {
    public String longestWord(String[] words) {

        /* create an instance of Trie */
        Trie trie = new Trie();
        String maxLengthWord = "";

        /* populates the trie with words */
        for (String word : words) {
            trie.insert(word);
        }

        /* checks if each word is present in the Trie */
        /*
         * when each char is checked against trie node, we also check if wordComplete is
         * true for each char,
         */
        for (String word : words) {
            if (trie.search(word)) {

                /*
                 * checks for two possibilities: 1) The word length > max Word length. 2) or if
                 * lengths are equal, then apply lexicographical order
                 */
                if (word.length() > maxLengthWord.length()
                        || (word.length() == maxLengthWord.length() && word.compareTo(maxLengthWord) < 0)) {

                    maxLengthWord = word;
                    // System.out.println(maxLengthWord);

                }
            }
        }

        return maxLengthWord;

    }

    /* Trie class, to construct Trie */
    class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /** inserts a word into trie **/
        public void insert(String word) {

            TrieNode node = root;

            for (char c : word.toCharArray()) {
                if (node.getChild(c) == null) {
                    node.insert(c);
                }
                node = node.getChild(c);
            }

            node.setIsWordComplete(true);
        }

        /* searches for a word and if found, returns true */
        public boolean search(String word) {

            TrieNode node = root;

            for (char c : word.toCharArray()) {

                if (node.getChild(c) == null)
                    return false;
                node = node.getChild(c);
                if (!node.getIsWordComplete())
                    return false; // modified to check if each char is also a word

            }

            return node.getIsWordComplete();
        }
    }

    /* classic Trie Node */
    class TrieNode {

        TrieNode[] node;
        boolean isWordComplete = false;

        public TrieNode() {
            node = new TrieNode[26];
        }

        public void insert(char c) {
            node[c - 'a'] = new TrieNode();
        }

        public TrieNode getChild(char c) {
            return node[c - 'a'];
        }

        public void setIsWordComplete(boolean status) {
            this.isWordComplete = status;
        }

        public boolean getIsWordComplete() {
            return this.isWordComplete;
        }

    }

    /* Test */
    public static void main(String[] args) {

        String[] dict1 = { "a", "banana", "app", "appl", "ap", "apply", "apple" }; // expected output : "apple"

        LongestWordInDictionary obj = new LongestWordInDictionary();
        
        System.out.println("Expected : " + "apple");
        System.out.println("Actual : " + obj.longestWord(dict1));


        String[] dict2 = {"ogz","eyj","e","ey","hmn","v","hm","ogznkb","ogzn","hmnm","eyjuo","vuq","ogznk","og","eyjuoi","d"};  //output: eyj
        
        System.out.println("Expected : " + "eyj");
        System.out.println("Actual : " + obj.longestWord(dict2));


        String[] dict3 = {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};  //output:  latte
        
        System.out.println("Expected : " + "latte");
        System.out.println("Actual : " + obj.longestWord(dict3));

    }

    /*
     * Logic : Apple in order to be longest should contain a..ap..app..appl..apple
     * in dict. We first sort the array as it gives us in lexicographical order.
     * Then we add all words into a set...check if previous combination exists in
     * set..like while checking for ap, check for a, ..for apple..check if appl
     * exists in set.
     * Optimized version - we don't add everything into set at once..we add as and
     * when we check if previus combination exists
     */

    public String longestWordWithoutTrie(String[] words) {

        Set<String> set = new HashSet<>();

        Arrays.sort(words);

        // maxCount = 0;
        String longest = "";

        /*
         * for(String word : words){ // further optimized by not adding all the words
         * into Set in one shot
         * set.add(word);
         * }
         */

        for (String word : words) {

            // int count = 0;

            /*
             * for(int i = 0; i < word.length(); i++){
             * 
             * if(set.contains(word.substring(0,i+1))) count++;
             * else break;
             * 
             * if(count > maxCount){
             * maxCount = count;
             * longest = word;
             * }
             * }
             */

            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {

                if (word.length() > longest.length()) {
                    longest = word;
                }

                set.add(word);
            }
        }

        return longest;
    }

}