class LongestCommonSubsequence {
	
	//dp - runtime complexity O(M X N), Space - O(M X N)
    // Credits : https://www.youtube.com/watch?v=ASoaQq66foQ
<<<<<<< HEAD


    //  "abc" & "aec"  - LCS = ac = 2

        /*
            "abc" & "aec"

            " a b c
          " 0 0 0 0 
          a 0 1 1 1 
          e 0 1 1 1 
          c 0 1 1 2 

        */
=======
>>>>>>> f4366fcdb41673b9ac888c17eb4c4380f9aca8d1


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



        //max of top and left or max of top and (left +1) -- in case of a match
                
        for(int i = 1; i < text1.length() + 1; i++){
            
            for(int j = 1; j < text2.length() + 1; j++){
                
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){ // here we take i - 1, j - 1 as string index starts from 0
                    
                  dp[i][j] = dp[i - 1][j - 1] + 1;      
                }
                
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }                 
            }             
        }
        
       /* 
         for(int i = 0; i < text1.length() + 1; i++){
            
            for(int j = 0; j < text2.length() + 1; j++){
                
                System.out.print(dp[i][j] + " ");
                
            }            
             System.out.print(" ");
            
        }
*/        
        return dp[text1.length()][text2.length()];
    }
}

