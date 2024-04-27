package com.app.arrays2d;

public class NumberOfIslands{
   // find the number of chain-islands/ clusters/blobs 
          
      
   /*Logic : for each cell in m X n matrix, do a recursive call and check all the neighbors - left, right, top, down
              Where ever there is 1, set it to 0 (sinking the island), and when it is == 0, return 0. When recursive  sinking is complete, return 1--denoting the blob/cluster of islands sunk*/
    
    public int numIslands(char[][] grid) {
        
        if(grid == null || grid.length == 0) return 0;
        
        int islands = 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        for(int i =0; i < n; i++){
            for(int j = 0; j < m; j++){
                
                if(grid[i][j] == '1'){
                    
                    islands += dfs(grid, i, j); // each new island will be added                     
                }                
            }            
        }        
        return islands;       
    }    
    
    public int dfs(char[][] grid, int i, int j){
        
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0' ) return 0;
        
        /* traverse the adjacent neighbours recursively - left, right , top, bottom */
        // set the original island to 0, sink it. also try to recursively sink neighboring islands
        grid[i][j] = '0';
        dfs(grid, i + 1, j); //bottom
        dfs(grid, i - 1, j); // top
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
        return 1;        
    }
      
      
       public static void main(String[] args){
         
        char[][] arr =  {{'1','0','0','0'},   // op - 2
                        {'1','0','1','1'},
                        {'1','0','1','1'},
                        {'0','0','0','0'}};
                     
        NumberOfIslands obj = new NumberOfIslands();
        
        System.out.println(obj.numIslands(arr));  
             
      }
}