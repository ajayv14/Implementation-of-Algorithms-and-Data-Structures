

// LC 1254 : https://leetcode.com/problems/number-of-closed-islands/

public class NumberOfClosedIslands {


    public int closedIsland(int[][] grid) {

        int count = 0;    

        int[][] dirs = new int[][] { {-1,0}, {0, 1}, {1, 0}, {0,-1}};

        // Don't have to start from i - 0, j = 0 as island in edge is to be ignored.
        for(int i = 1; i < grid.length; i++){

            for(int j = 1; j < grid[0].length; j++){

                if(grid[i][j] == 0 && dfs(grid, i, j, dirs)) {

                    count++;
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int row, int col, int[][] dirs){

        // Out of boundary    
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return false;
        }   

        else if(grid[row][col] == 1) return true; // water or revisited island 0


        // else the cell contains 0. so check all 4 sides to detect if cell is closed/not closed

        grid[row][col] = 1; // To prevent revisiting same cell with 0;

        boolean isIslandClosed = true;

        for(int[] dir : dirs){

            int newRow = row + dir[0];
            int newCol = col + dir[1];
        
            if(!dfs(grid, newRow, newCol, dirs)) isIslandClosed = false;
        }

        return isIslandClosed;
                    
    }
}
