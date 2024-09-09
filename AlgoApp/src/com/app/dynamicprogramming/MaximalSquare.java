package com.app.dynamicprogramming;

class MaximalSquare {
     

    public int maximalSquare(char[][] matrix) {

        // LC 221
        // https://leetcode.com/problems/maximal-square
        
        /*DP non optimized-- https://www.youtube.com/watch?v=_Lf1looyJMU Thushar Roy*/

        /* Intution - Take current cell, it makes a square of 1 X 1.
            How can you create a square larger, like 2 x 2 ? - You need '1' at top, left and top-left diagonal.
            Or bottom, right and bottom-right cell. But for programming, former is easier.

            But when we start with cell (0,0, we don't have top, left and diagonal cell, hence we pad with '0's on row and col. 
         */    

        /* Padding row and col with 0 is the easiest way to code without bothering abt boundaries. 
         *  

         Runtime O(m * n) :
         Space : O(m * n)
         */      
        
        /*base case*/
        if(matrix.length == 0 || matrix[0].length == 0) return 0;          
       
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        
        int max = 0;
        
        for(int row = 1; row < matrix.length + 1; row++){
            
            for(int col = 1; col < matrix[0].length + 1; col++){

               
                // Referring to original matrix. Don;t have to calculate for '0'    
                if(matrix[row - 1][col - 1] == '1') {

                    int left = dp[row][col - 1];

                    int top = dp[row - 1][col];

                    int diagonal = dp[row - 1][col - 1];
                   
                    dp[row][col] = 1 + Math.min(diagonal, Math.min(left,top));     

                    max = Math.max(max, dp[row][col]);             
                }                           

               

               // printArray(dp);
              
                      
            }            
            
        }           
        return max * max;  
        
    }
    
     
 public static void printArray(int[][] a){
     
     
     for(int i = 0; i < a.length; i++){

         for(int j = 0; j < a[0].length; j++){
             
             
             System.out.print(a[i][j] + " ");
             
         }
         
         System.out.println("--------");
         
         
     }
     
     
 }   
   
   
}