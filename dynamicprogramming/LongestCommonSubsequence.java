
import java.util.Arrays;


// https://leetcode.com/problems/longest-common-subsequence/description/
// LC 1143 : 1143. Longest Common Subsequence


// Use 2D DP table where dp[i][j] represents LCS length for text1[0...i-1] and text2[0...j-1].
// If chars match, add 1 to diagonal value. If not, take max of left and top values. Final answer in dp[m][n]

// Credits : https://www.youtube.com/watch?v=sSno9rV8Rhg
class LongestCommonSubsequence {

    // Step 1: Recursion


     // Recursion - "abcd" & "aqce"
     public int longestCommonSubsequenceRecursion(String text1, String text2) {

        //Optimization
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();    

        return LCS(0,0,s1,s2);
        
    }

    private int LCS(int i, int j, char[] s1, char[] s2){

        if(i >= s1.length || j >= s2.length) return 0;

        if(s1[i] == s2[j]) return 1 + LCS(i + 1, j + 1, s1, s2);

        else {
            return Math.max(LCS(i + 1, j, s1, s2) , LCS(i, j + 1, s1, s2));
        }

    }


    // Step 2 : 

// Recursion + Memoization - "abcd" & "aqce"


    /*
    Why memoization ? 
         "abcd" & "aqce"

         Compare 1 :   ([ "a", "a"]) true
                    
         Compare 1 :   1 + ("b", "q") false

         Compare 3 :  Max of :  ("b" , "c") false  &   ("c", "c") true           
                                    |                           |
         Compare 4:  Max of : ("b" , "e") & ("c", "c")          Max of : ("c", "e") & ("d", "c")

    Repetative calls like ("c", "c") can be cached 
    
    
     */
    public int longestCommonSubsequenceMemoization(String text1, String text2) {

        //Optimization
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int[][] mem = new int[text1.length()][text2.length()];
        
        for(int[] row : mem){
             Arrays.fill(row,-1);
        }
      

        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();    

        LCS(0,0,s1,s2, mem);

        return mem[0][0];        
    }

    private int LCS(int i, int j, char[] s1, char[] s2, int[][] mem){

        if(i >= s1.length || j >= s2.length) return 0;

        // Make use of cached value
        if(mem[i][j] != - 1) return mem[i][j];

        if(s1[i] == s2[j]) {

            mem[i][j] =  1 + LCS(i + 1, j + 1, s1, s2, mem);
        } 

        else {
            
            mem[i][j] =  Math.max(LCS(i + 1, j, s1, s2, mem) , LCS(i, j + 1, s1, s2, mem));
        }

        return mem[i][j];
    }


    // Step 3 : Using DP

	
	//dp - runtime complexity O(M X N), Space - O(M X N)
    // Credits : https://www.youtube.com/watch?v=ASoaQq66foQ


    /** Approach : Comparing "abcd" & "aqce" --> result : 2

        Two possibilities, character match or no match. See example below:
        Starting from left, first character in both strings -> "a"    
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
     

        Note : why dp[i][j] = 1 + dp[i - 1][j - 1]; and dp[i][j] = 1 + dp[i + 1][j + 1] during recursion ??
        In Recursion, it is solving subproblems on a stack, so one char at a time.
        In DP, we have 0 padding and we calculate subproblems looking at dp array. 
     
     */
   
   public int longestCommonSubsequence(String text1, String text2) {

        //Optimization
         if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        // Form 2D dp matrix
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // For 0 padding

        // Zero padding - " " matched with any string will yield 0;    
        for(int m = 0; m < dp.length; m++){
            dp[m][0] = 0;
        }
        // Zero padding - " " matched with any string will yield 0;    
        for(int n = 0; n < dp[0].length; n++){
            dp[0][n] = 0;
        }


        for(int i = 1 ; i < dp.length; i++){

            for(int j = 1 ; j < dp[0].length; j++){

                /* Note, we compare text1.charAt(i - 1) instead of text1.charAt(i), to compensate  
                for 0 padding in dp matrix */      
                
                // Character is a match scenario: 
                // i - 1 in string actually represents i in dp array as we start from empty strign at 0th index in dp[]
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 

                // Character is not a match scenario
                // Eliminate oen char from text2 - Move up one col
                // Eliminate one character from text1 -  Move up one row
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }    

        return dp[dp.length - 1][dp[0].length - 1]; 
        
    }


    // Test
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

