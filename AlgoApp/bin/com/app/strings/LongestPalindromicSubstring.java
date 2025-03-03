package AlgoApp.bin.com.app.strings;

//https://leetcode.com/problems/longest-palindromic-substring/description/
// LC 5. Longest Palindromic Substring

public class LongestPalindromicSubstring {

    class Solution {
    
        // time : O(N)^3
        public String longestPalindrome(String s) {
            
            // Start with longest, move on to smaller strings   
            for(int i = s.length() - 1; i >= 0 ; i--){
    
                System.out.println("i : " + i);
    
                // Find longest palindromic substring 
                for(int j = 0; j <= s.length() - 1 - i; j++){
    
                    /*System.out.println( "j upper bound : " + (s.length() - 1 - i));
    
                    System.out.println("j : " + j + "  " + " j + i : " +(j + i));
    
                    System.out.println(s.substring(j, j + i + 1));
    /*
    
    s.substring(j, j + i + 1):
    
    babad
    baba
    abad
    bab
    aba
    bad
    ba
    ab
    */                
    
                     if(isPalindrome(j, j + i ,s)) return s.substring(j, j + i + 1);  
    
                }
            }
            
            return null;
        }
    
    
        private boolean isPalindrome(int i, int j, String s){
                 
            //System.out.println("i : " + i + " j :" + j);
    
            while(i < j){
    
                if(s.charAt(i) != s.charAt(j)) return false;
                i++;
                j--;
            }
            return true;
        }
    }

}
