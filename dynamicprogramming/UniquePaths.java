package com.app.dynamicprogramming;

class UniquePaths {
    public int uniquePaths(int m, int n) {
                
        /*dp --- Non optimized*/
        
        /*ref: https://www.youtube.com/watch?v=GO5QHC_BmvM -- Thushar Roy*/        
        
        if(m <= 0 || n <= 0) return 0;
        
        
        int[][] dp = new int[m][n];     

        
        /*fill 1s in first row*/
        
        for(int i = 0; i < n; i++){
       
            dp[0][i] = 1;
            
        }
        
        /*fill 1s in first column*/
        
        for(int j = 0; j < m; j++){
            
            dp[j][0] = 1;
        }
        
        /*all paths for each cell*/
        
        for(int i = 1; i < m; i++){
            
            for(int j = 1; j < n; j++){
               
               /*current path = path to left and path to top + 1*/ 
              
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                
                
            }             
        }
        
        
        
    
        return dp[m - 1][n - 1];
        
    }
}