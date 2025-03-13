package com.app.matrix;

/*
 * Intituion - Simple dfs. Similar to number of islands problem.
 *  Additionally use stepOverCells count to make use all cells with '0' are crossed  atmost once in each path (No shortcuts)
 * 
 */


public class UniquePathsIII {

    int paths = 0;
    int stepOverCells = 1; 
    int[][] dirs = new int[][]{ {1,0}, {-1,0}, {0,-1}, {0,1} };

    public int uniquePathsIII(int[][] grid) {

        
        int startRow = 0, startCol = 0; 

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){

                 if(grid[row][col] == 0) stepOverCells++;

                 else if(grid[row][col] == 1){
                        startRow = row;
                        startCol = col;
                 }              
            }
        }    

         dfs(grid, startRow, startCol, stepOverCells);
                    
        return paths;
    }


    public void dfs(int[][] grid, int row, int col, int stepOverCells){

        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == -1) return;

        if(grid[row][col] == 2){

            if(stepOverCells == 0) paths++;

            return; 
        } 

        grid[row][col] = -1;
        stepOverCells--;
        
        for(int i = 0; i < dirs.length; i++){
            dfs(grid, row + dirs[i][0], col + dirs[i][1], stepOverCells);
        }
        
        grid[row][col] = 0;
        stepOverCells++;
    }

}
