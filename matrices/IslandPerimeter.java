public class IslandPerimeter {


    /**
     Approach : For a cell, each side surrounded by water adds one to perimeter. S0 for each cell compute 
      (max perimeter - number of sides facing land) -> 4 - number of sides facing land.
      
      Basically look at the walls : 
      Cells in first row ? Up is 0,  grid[i - 1][j] otherwise 
      Cells in first col ? left is 0,  grid[i][j - 1] otherwise
      Cells in last row ? down - bottom is 0, grid[i + 1][j] otherwise
      Cells in last column ? right = 0,   grid[i][j + 1] otherwise      
     */

    // Non-optimized 
    public int islandPerimeter(int[][] grid) {

        int perimeter = 0;

        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == 1){

                    // Up is zero for first row cells
                    int up = (i == 0) ? 0 : grid[i - 1][j];

                    // left is zero for first column cells    
                    int left = (j == 0) ? 0 :  grid[i][j - 1];

                    // Down is zero for last row cells
                    int down = (i == grid.length - 1) ? 0 : grid[i + 1][j];

                    // right is zero for last column cells
                    int right = (j == grid[0].length - 1) ? 0 : grid[i][j + 1];

                    perimeter += 4 - (up + left + down + right);

                }
            }
        }
        
        return perimeter;
    }
}
