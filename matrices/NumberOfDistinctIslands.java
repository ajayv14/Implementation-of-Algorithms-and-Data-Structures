
// LC : 694 : https://leetcode.com/problems/number-of-distinct-islands



// Time : O(M * N)
// Space : O(M * N) 
public class numDistinctIslands {


    // Another approach using path as hash. 

    // Directions - Up, Right, Down, Left
    int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

   
    StringBuilder sb; // Build hash using distance from cur origin

    Set<String> islands = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {

        int distinct = 0;
        
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                // new island    
                if(grid[i][j] == 1){

                    sb = new StringBuilder();
                                 
                    dfs(grid, i, j,'R');

                    String hash = sb.toString();  

                    //System.out.println(hash); 

                    if(!islands.contains(hash)) {

                        islands.add(hash);
                        distinct++;
                    } 
                
                }        
            }
        }
        return distinct;        
    }

    private void dfs(int[][] grid, int row, int col, char pathChar){

        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0){
            return;
        }

        grid[row][col] = 0; // mark visited

        // Tp compute hash for each cell in island
        sb.append(pathChar);    

           
        dfs(grid, dirs[0][0] + row, dirs[0][1] + col, 'U');
        dfs(grid, dirs[1][0] + row, dirs[1][1] + col, 'D');
        dfs(grid, dirs[2][0] + row, dirs[2][1] + col, 'R');
        dfs(grid, dirs[3][0] + row, dirs[3][1] + col, 'L');

        //Keep account of when we backtracked
        sb.append('B');           
    }


}


public class NumberOfDistinctIslandsNonOptmized {

  

    // Directions - Up, Right, Down, Left
    int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // Each island has an origin, used to calculate distance from all cells
    int curOriginRow;
    int curOriginCol;

    StringBuilder sb; // Build hash using distance from cur origin

    Set<String> islands = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {

        int distinct = 0;
        
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                // new island    
                if(grid[i][j] == 1){

                    sb = new StringBuilder();

                    curOriginRow = i;
                    curOriginCol = j;                    
                    dfs(grid, i, j);

                    String hash = sb.toString();  

                    //System.out.println(hash); 

                    if(!islands.contains(hash)) {

                        islands.add(hash);
                        distinct++;
                    } 
                
                }        
            }
        }
        return distinct;        
    }

    private void dfs(int[][] grid, int row, int col){

        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0){
            return;
        }

        grid[row][col] = 0; // mark visited

        // compute hash for each cell in island
        sb.append(String.valueOf(curOriginRow - row) + String.valueOf(curOriginCol - col));

        for(int[] dir : dirs){

            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
           
            dfs(grid, newRow, newCol);
        }
    }


}



