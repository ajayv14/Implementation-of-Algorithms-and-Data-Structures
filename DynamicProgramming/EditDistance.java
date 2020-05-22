class EditDistance {
    public int minDistance(String word1, String word2) {
        
        /**
          * credits : https://www.youtube.com/watch?v=MiqoA-yF-0M&t=6s
          * Leetcode
            --------------------------                
            | replace  |  insert     | 
            --------------------------
            | delete   | current pos |
            --------------------------
                       
           * if char in word1 != char in word2 - find the min among 3 operations(replace, insert, del) for cur position. Add one(operation) to it
           * if char ..... is the same, we just take the min operations in prev step.(no operation is reqd.)           
        **/
           
        int W1 = word1.length();
        int W2 = word2.length();
              
        if(W1 == 0) return W2;
        if(W2 == 0) return W1;
        
        int[][] dp = new int[W1 + 1][W2 + 1];  // accounting for the initial "" string as well
               
        /*fill leftmost col with 0,1,2,3,4...*/
        for(int i = 0; i < W1; i++){
            dp[i][0] = i;
        }
        
        /*fill first row with 1,2,3..*/
        for(int j = 1; j <= W2 ; j++ ){
            dp[0][j] = j;     
        }       
       
        
        for(int i = 1; i <= W1 ; i++){
            for(int j = 1; j <= W2; j++){
                
                /*matching char - we use i - 1 and j - 1 instead of i & j as we compare chars at index, not counting the "" that we added in 2D                     matrix */
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else{
                    // min of delete,replace,insert 
                    // Math.min(math.min(dp[i][j - 1],dp[i - 1][j - 1]),dp[i - 1][j]);
                    int delete = dp[i][j - 1]; 
                    int replace = dp[i - 1][j - 1];
                    int temp = Math.min(delete,replace);
                    
                    dp[i][j] = Math.min(temp, dp[i - 1][j]) +  1; 
                    
                }
                
            }
        }                
        
        return dp[W1][W2];       
        
    }
}