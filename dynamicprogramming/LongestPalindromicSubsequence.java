package com.app.dynamicprogramming;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

        //Use 2D DP where dp[i][j] represents longest palindromic subsequence from index i to j.
        // If chars match, add 2 to dp[i+1][j-1]. If not, take max of dp[i+1][j] and dp[i][j-1]. Fill diagonally.

        // LC https://leetcode.com/problems/longest-palindromic-subsequence/
        // LC 516. Longest Palindromic Subsequence

        /*
        To check LONGEST palindromic subsequence, check if first and last characters match, if so then compare next set of chars and so on. 
        If not a match, then we have to use include or ignore strategy.

        Example -   "bczxab"  
        Step 1 : compare first 'b' and last 'b' - Match. 
        Step 2 : 2 + LCS of next chars('c', 'a') - Do not match
        Step 2 : Max of ( exclude c, include 'a' LCS('z','a') , include 'c', exclude 'a' LCS('c','x') )
                  Max( LCS(i + 1, j), LCS(i, j - 1))
    and so on.
     */


    // Recursion
    public int longestPalindromeSubseqRecursive(String s) {
        
     return LPS1(0, s.length() - 1, s);       

    }

    private int LPS1(int i, int j, String s){
        
        if(i > j || j > s.length() - 1) return 0;

        if(i == j) return 1; // When once char is compared, it is a palindrome.

        if(s.charAt(i) == s.charAt(j)) {
            return 2 + LPS1(i + 1, j - 1, s);
        }

        else {
           return Math.max( LPS1(i + 1, j, s), LPS1(i , j - 1, s));
        }
    }

    // Memoization
    public int longestPalindromeSubseqMem(String s) {
        
        int[][] mem = new int[s.length()][s.length()];

        for(int[] row : mem){
            Arrays.fill(row, -1);
        }

        return LPS2(0, s.length() - 1, s, mem);          
       
    }

    private int LPS2(int i, int j, String s, int[][] mem){
        
        if(i > j || j > s.length() - 1) return 0;
               
        if(i == j) return 1; // When once char is compared, it is a palindrome.

        if(mem[i][j] != -1) return mem[i][j];

        if(s.charAt(i) == s.charAt(j)) {
            mem[i][j] = 2 + LPS2(i + 1, j - 1, s, mem);
        }

        else {
           mem[i][j] = Math.max( LPS2(i + 1, j, s, mem), LPS2(i , j - 1, s, mem));
        }

        //System.out.println("i : " + i + "j : " + j);
        return mem[i][j];
        
    }


    /*  
        b b b a b
       b
       b
       b
       a
       b 

        Basically checking first and last character of a string.
        Outer loop starts with last row 
        dp[i][i] will set 1 at diagonal, 
        Hence, Inner loop - starts at i + 1
                
        Interestingly dp[i + 1][j], dp[i][j - 1] for a cell will be bottom and left values
        Also, dp[i + 1][j - 1] 

        So, matrix gets filled in order - > last row, last col.
        in the end values are present only on right half of diagonal.
     */


    // Tabulation dp
    public int longestPalindromeSubseq(String s) {
        
        int[][] dp = new int[s.length()][s.length()];

        for(int i = s.length() - 1; i >= 0 ; i--){

            dp[i][i] = 1; // single character

            for(int j = i + 1; j < s.length(); j++){

                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }

                else {

                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }    

        return dp[0][s.length() - 1];
    }

}
