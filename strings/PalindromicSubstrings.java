// LC 647 : https://leetcode.com/problems/palindromic-substrings/

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        
        int count = 0;


       // Expand around center index 
       for(int i = 0; i < s.length(); i++){


            // Odd length - left and right are same index 
            count += countPalindrome(s,i,i);
            
            // Even length
            count += countPalindrome(s,i,i+1);

       }

       return count;
    }

    public int countPalindrome(String s, int i, int j){
        
        int count = 0;

        // Boundary check 
        while(i >= 0 && j < s.length()){

            if(s.charAt(i) != s.charAt(j)){
                
                break;                        
            }

            count++;           

            // Expand from center

            i--;
            j++;
        }
        return count;
    }

}




// https://leetcode.com/problems/string-to-integer-atoi/


//// ??????










