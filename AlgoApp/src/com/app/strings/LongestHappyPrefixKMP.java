package com.app.strings;

// LC 1392
// https://leetcode.com/problems/longest-happy-prefix/


public class LongestHappyPrefixKMP {

    /* Apply KMP sub string matching algo

        lps examples :
        
        "abc" - LPS = [0, 0, 0]

        "AABAACAABAA" - LPS = [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]
      
     */
   
     public String longestPrefix(String pattern) {

        int[] lps = lps(pattern);

        return pattern.substring(0, lps[pattern.length() - 1]);      

    }

    private int[] lps(String pattern) {

        int[] lps = new int[pattern.length()];       

        int j = 0;

        for(int i = 1 ; i < pattern.length(); i++){

            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)){

                j = lps[j - 1];
            }    


            if(pattern.charAt(i) == pattern.charAt(j)) j++;       
                            
            lps[i] = j;
        }

        return lps;               
       
    }

    /*
    s = "level" Expected = "l"

    s = "ababab" Expected = "abab"

    s = "babbb" Expected = "b"    
    
    s = "bba" Expected = ""

    s = "aaaaa" Expected = "aaaa"
    
    
     */


    /*
          The Knuth-Morris-Pratt (KMP) algorithm searches for a pattern in a text by preprocessing the pattern to create an LPS (Longest Prefix Suffix) array. 
          This array allows the algorithm to skip redundant comparisons by reusing previous match information. When a mismatch occurs, the algorithm shifts the pattern based on the LPS values, avoiding rechecking already matched characters. 
          
          This results in an efficient search with a time complexity of O(n + m), where n is the length of the text and m is the length of the pattern.   
    
     */


}
