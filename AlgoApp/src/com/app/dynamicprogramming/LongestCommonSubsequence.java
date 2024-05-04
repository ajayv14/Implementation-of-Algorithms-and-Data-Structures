package com.app.dynamicprogramming;

import com.app.common.CommonUtil;

// LC 1143
class LongestCommonSubsequence {
	
	//dp - runtime complexity O(M X N), Space - O(M X N)
    // Credits : https://www.youtube.com/watch?v=ASoaQq66foQ

    /** Approach : Comparing "abcd" & "aqce" --> result : 2

        Two possibilities, character match or no match. See example below:
        Starting from left, first character in both strings -> "a"
        If there is a character match, then we can count it as "1" and the move on with rest of string.    
        1. "a" and "a" match, so 1 + LCS(previous computed string) --> 1 + LCS(" "," ")
        2. Next, in remaining string "b" and "q" do not match, 
            so --> Max(LCS("ab","a") and LCS("b", "aq")) and so on.
        
        Follow the above logic at each cell in matrix:
        1. --> dp[i][j] = 1 + dp[i - 1][j - 1];
        2. --> dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        return answere from last rightmost cell.

            Matrix :    0 a b c d
                       0     
                       a
                       q
                       c
                       e 
     */
   

    //  "abc" & "aec"  - LCS = ac = 2

        /*
            "abc" & "aec"

            " a b c
          " 0 0 0 0 
          a 0 1 1 1 
          e 0 1 1 1 
          c 0 1 1 2 

        */

public int longestCommonSubsequence(String text1, String text2) {
       
        //Optimization
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;
        
        
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // + 1 to account for empty string "" 
        
        
        //fill first row and col with 0s as we initially compare empty string "" against text 1 and text 2 in 2D matrix at beginning 
       
        for(int i = 0; i < text1.length() + 1; i++){
            dp[i][0] = 0;           
        }
        
        for(int j = 0; j < text2.length() + 1; j++){
            dp[0][j] = 0;
        }
        
        // Slowly expand the window to include each character in string 


                
        for(int i = 1; i < text1.length() + 1; i++){
            
            for(int j = 1; j < text2.length() + 1; j++){

                /* Note, we compare text1.charAt(i - 1) instead of text1.charAt(i), to compensate  
                for 0 padding in dp matrix */ 

                // Character is a match scenario               
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){ // here we take i - 1, j - 1 as string index starts from 0
                    
                  dp[i][j] = 1 + dp[i - 1][j - 1];      
                }
                
                // Character is not a match scenario
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }                 
            }             
        }     
     
        return dp[text1.length()][text2.length()];
    }


    public static void main(String[] args){

        LongestCommonSubsequence obj = new LongestCommonSubsequence();

        String text1 = "abcde", text2 = "ace";
        String expected1 = "3";
        
        CommonUtil.runExample(text1+ " " +text2, expected1, obj.longestCommonSubsequence(text1, text2) + "");


        String text11 = "abc", text22 = "abc";
        String expected2 = "3";
        
        CommonUtil.runExample(text11+ " " +text22, expected2, obj.longestCommonSubsequence(text11, text22) + "");

        String text1111 = "abc", text2222 = "def";
        String expected3 = "0";
        
        CommonUtil.runExample(text1111+ " " +text2222, expected3, obj.longestCommonSubsequence(text1111, text2222) + "");

    }
}

