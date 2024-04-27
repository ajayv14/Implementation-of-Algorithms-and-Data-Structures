package com.app.dynamicprogramming;

class MaximalSquare {
     public int maximalSquare(char[][] matrix) {
        
        /*DP non optimized-- https://www.youtube.com/watch?v=_Lf1looyJMU Thushar Roy*/
        
        
        /*base case*/
        if(matrix.length <= 0 || matrix[0].length <=0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int maxDimSquare = 0;
        
        //m + 1 and n + 1 to pad matrix with zeros on top - row and left - column
        
        int[][] dp = new int[m + 1][n + 1];
        
        
        for(int i = 1; i < m + 1; i++){
            
            for(int j = 1; j < n + 1; j++){
                
                
                if(matrix[i - 1][j - 1] == '1'){ // check only for 1s
                                     
                    
                    // take minimum of left, top, top - left
                    
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    
                    maxDimSquare = Math.max(maxDimSquare, dp[i][j]);
                    
                }                
                
                
            }            
            
        }
        
       // to check  printArray(dp);
        
        
        return maxDimSquare * maxDimSquare;
        
        
    }
    
 /*     
 public static void printArray(int[][] a){
     
     
     for(int i = 0; i < a.length; i++){

         for(int j = 0; j < a[0].length; j++){
             
             
             System.out.print(a[i][j] + " ");
             
         }
         
         System.out.println(" ");
         
     }
     
     
 }   
   
*/   
   
}