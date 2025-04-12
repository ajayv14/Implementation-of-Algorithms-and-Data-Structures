package com.app.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// LC 827 https://leetcode.com/problems/making-a-large-island

public class MakingLargeIsland {


    /*
        Approach : 

        Use modified dfs num of island count technique to get count of islands.
        The cells of each island should be replaced by the island id.
        Also keep a map to track island id and size.

        Now, visit cells in grid that are 0 and flip then to 1, one at a time.
        Check for neighboring cells - up, right, down, left to see if it hits any island.
        If so, then this flipped cell is a bridge.  

        Count sizes of neighboring islands using map and the current cell itself.    
     */

    // Time O(M X N) and Space - O(M X N) dfs call stack worst case
    

    // Directions up, right, down, left  
    int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    
    // <ids, island sizes>
    Map<Integer, Integer> map = new HashMap<>(); 

    public int largestIsland(int[][] grid) {

        int maxCount = 0;    

        int ids = 2; // Start with 2 as we have 0 and 1 representing water and island  
        
        // Get each island and add to map
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){
                   
                   if(grid[i][j] == 1){

                       int islandSize =  dfs(grid, i, j, ids);

                       map.put(ids++, islandSize);            

                   }               
            }           
        }  

        //System.out.println("map : " + map);

        // flip zeros (one at a time) and find if islands are neighbors
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){
                   
                   if(grid[i][j] == 0){

                       grid[i][j] = 1;

                       int newCount = findNeighbors(i, j, grid); 
                       
                       maxCount = Math.max(maxCount, newCount);

                       grid[i][j] = 0; // Flip it back

                   }               
            }           
        }  

     return (maxCount > 0) ? maxCount : map.get(2);

    }


    // Find neighbors - up, right, down and left who maybe previously seen island
    /*
            2 2 2 0 3  
            2 2 0 3 3
            2 2 0 0 3

            Flipping 0 in the middle, hits neighbors 2, 3, 0 and 2.
            So ignore repeated 2, just count sizes of 2, 3 and the 0 flipped cell. 

    */

    private int findNeighbors(int row, int col, int[][] grid){

        Set<Integer> seenIsland = new HashSet<>();

        int combinedSize = 0;

        for(int i = 0; i < dirs.length; i++){

            int newRow = row + dirs[i][0];
            int newCol = col + dirs[i][1];

            // Boundary check 
            if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) continue;    

            int islandId = grid[newRow][newCol];

            if(seenIsland.contains(islandId)) continue;

            seenIsland.add(islandId);

            int size = map.getOrDefault(islandId,0);

            combinedSize += size;
        }    

        return 1 + combinedSize; // Add 1 to count the flipped cell

    }


    // Calculate sizes
    private int dfs(int[][] grid, int row, int col, int islandId){
       
        // Set boundaries
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) return 0;
      
      // All cells of an island should have ids representing island.
      // Makes it easy to detect neighboring islands when later we flip 0s
        grid[row][col] = islandId; 
        
        int size = 0;

        for(int i = 0; i < dirs.length; i++){
            
            size += dfs(grid, row + dirs[i][0], col + dirs[i][1], islandId);
        }

        return 1 + size;

    }

}
