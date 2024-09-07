package com.app.dynamicprogramming;

public class MinimumPathSum {

    /**
     * 
     * LC - 64
     * https://leetcode.com/problems/minimum-path-sum/ 
     * 
        Intution : Start from end and see how to pick paths backwards.

        Algo : For each cell, compute min path, looking at cell to top or cell to left of this cell.
        
        Apply constraints for cells that either are at left wall, top wall or both(0,0).


        Runtime O(m * n)
    
     */


     public int minPathSum(int[][] grid) {


        for(int i = 0; i < grid.length; i++ ){
           
           for(int j = 0; j < grid[0].length; j++){


                if(i > 0 && j > 0){

                   grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);

                }

                // j <= 0 - Left wall - Only item to top exists  
                else if(i > 0) grid[i][j] += grid[i - 1][j];
                
                // i <= 0 Top wall - Only item to left exist  
                else if (j > 0)  grid[i][j] += grid[i][j - 1];
           }
           
       }
       return grid[grid.length - 1][grid[0].length - 1];
       
   }

}
