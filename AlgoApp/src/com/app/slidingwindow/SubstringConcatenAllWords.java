package com.app.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 30 : https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * 
* Apply sliding window technique with fixed window computed from number of words.
  Create a frequency map with input words.
  Create another frequency map for words in sliding window.
  Since words are of same length, sliding window can be broken down into words.
  Compare both maps.  

  Time complexity:

  O(l wk)

  Where l -> Length of string s

  w -> Number of items in array words and k = fixed length of each word

  Space complexity:
  
  O(w + l) Result list can stre max of l items and freq map store max w items.
 */

public class SubstringConcatenAllWords {


    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> res = new ArrayList<>();

        int windowLen = 0, left = 0, right = 0;

        int wordLen = words[0].length();  

        Map<String, Integer> freq = new HashMap<>();

       
        for(String word : words){

            windowLen += word.length();    
            freq.put(word, freq.getOrDefault(word,0)+1);    

        }          
      

        // <= s.length() is used as while creating substring, the end index should be end + 1
        for(right = windowLen; right <= s.length(); right++){
            
        
            if(checkSubString(freq, left, right, wordLen, s)) res.add(left);
            left++;
        }

        return res;
        
    }

    private boolean checkSubString(Map freq, int left, int right, int wordLen, String s){

        Map<String, Integer> count = new HashMap<>();      

        for(int i = left + wordLen; i <= right ; i+=wordLen){          

            String sub = s.substring(left, i);      

            count.put(sub, count.getOrDefault(sub, 0) + 1);    

            left += wordLen;         

        }      

        return freq.equals(count);

    }

}

/*
Test cases :

Input: s = "barfoothefoobarman", words = ["foo","bar"]

Output: [0,9]


Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]

Output: []

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]

Output: [6,9,12]

s =
"wordgoodgoodgoodbestword"
words =
["word","good","best","good"]
Output
[8]




*/ 