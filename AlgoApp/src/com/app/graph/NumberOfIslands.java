package com.app.graph;

// LC 200 
// https://leetcode.com/problems/number-of-islands/description/

public class NumberOfIslands {
    // find the number of chain-islands/ clusters/blobs

    /*
     * Logic : for each cell in m X n matrix, do a recursive call and check all the
     * neighbors - left, right, top, down
     * Where ever there is 1, set it to 0 (sinking the island), and when it is == 0,
     * return 0. When recursive sinking is complete, return 1--denoting the
     * blob/cluster of islands sunk
     */

     int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};


     public int numIslands(char[][] grid) {
         
         int count = 0;
        
         for(int i = 0; i < grid.length; i++){
 
             for(int j = 0; j < grid[0].length; j++){
 
                 // Found an island    
                 if(grid[i][j] == '1'){
 
                    count++;
 
                     // merge & expand
                     dfs(i, j, grid);
                    
                 }      
             }
         }
 
         return count;
     }
 
     // Merge islands and replace the 1s to 0s to stop double counting
     public void dfs(int row, int col, char[][] grid){
         
         // Boundary check + water
         if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0' ){
            return;
         }
 
         grid[row][col] = '0'; // mark it visited
 
         /*dfs(row + dirs[0][0], col + dirs[0][1], grid); // up
 
         dfs(row + dirs[1][0], col + dirs[1][1], grid); // right     
 
         dfs(row + dirs[2][0], col + dirs[2][1], grid); // down     
 
         dfs(row + dirs[3][0], col + dirs[3][1], grid); // left   */
 
         // or 
 
         for(int i = 0; i < dirs.length; i++){
 
             dfs(row + dirs[i][0], col + dirs[i][1], grid);
         }  
 
     }

    
   

    

    public static void main(String[] args) {

        char[][] arr = { { '1', '0', '0', '0' }, // op - 2
                { '1', '0', '1', '1' },
                { '1', '0', '1', '1' },
                { '0', '0', '0', '0' } };

        NumberOfIslands obj = new NumberOfIslands();

        System.out.println(obj.numIslands(arr));

    }
}