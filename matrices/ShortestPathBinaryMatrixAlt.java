package com.app.matrices;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


//LC https://leetcode.com/problems/shortest-path-in-binary-matrix
// LC 1091. Shortest Path in Binary Matrix

// Custom implementation

/* Approach - BFS - Guranteed shortest path in undirected graph
            
 DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
*/



class ShortestPathBinaryMatrix {
    
    static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base - start and end cell should contain 0 
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;


        Queue<int[]> q = new LinkedList<>(); // bfs queue
        q.add(new int[]{0,0});

        grid[0][0] = 1; // to calculate distance starting from 1 unit


        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];
           

            //Check if last right exit cell 
            // This check can also be done below the dirs loop, but will fail for input [[0]]
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return grid[row][col];
            } 

            for(int[] dir : dirs){

               int newRow = row + dir[0];
               int newCol = col + dir[1];

                // Why not replace walls 1 with -1 ?? 
                //-> We will never go back to cell 0,0 which is '1' but not a wall, so can skip this modification. 
                
                // Why ignore grid[newRow][newCol] > 0 ??  
                // -> 1 - wall. Any other positive number is already a shorter path in progress

                if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length 
                    || grid[newRow][newCol] > 0){
                       
                        continue;
                }

                q.add(new int[] {newRow,newCol});

                grid[newRow][newCol] = dist + 1; 

                //System.out.println(dist + 1);               
                
            }        
        }   

        return - 1;                          
    }

      
}















public class ShortestPathBinaryMatrixAlt {


     static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;

        Queue<int[]> q = new LinkedListImplementation<>();
        q.add(new int[]{0,0});

        grid[0][0] = 1;

        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];

            //Check if last right cell
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return dist;
            }

            List<int[]> neighbors = getNeighborsin8D(n, grid);
                                   
            for(int[] neighbor : neighbors){

                int r = neighbor[0], c = neighbor[1];                
                grid[r][c] = dist + 1;
                q.add(neighbor);
            }
        }   

        return - 1;                          
    }

    private List<int[]> getNeighborsin8D(int[] node, int[][] grid){

            List<int[]> neighbors = new ArrayList<>();

            for(int[] dir : dirs){

                int[] newDir = new int[] { (dir[0] + node[0]), (dir[1] + node[1])};

                if(newDir[0] >= 0 && newDir[0] < grid.length && 
                        newDir[1] >= 0 && newDir[1] < grid[0].length &&
                        grid[newDir[0]][newDir[1]] == 0
                        ){

                    neighbors.add(newDir);            
                }
            }
            return neighbors;
    }


}
