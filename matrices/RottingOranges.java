package com.app.graph;


/*
 Logic - DFS will not work as more than one orange can be infected and also oranges spread infection at same time.
         BFS with updating time and infected orange for every rotten orange at same time.         
*/

import java.util.Queue;
import java.util.LinkedList;

class RottingOranges {

    public int orangesRotting(int[][] grid) { 
       
        // To discover oranges in left, right, top and bottom
        int[][] directions = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        int countOfMinutes = 0;
        
        Queue<int[]> q = new LinkedList<>(); // to perform bfs

        // Sometimes a fresh orange may be unreachable, hence maintain count
        int freshOranges = 0; 

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == 1) freshOranges++;

                else if(grid[i][j] == 2){                              
                    q.add(new int[]{i,j});               
                }
            }
        }    

        while(!q.isEmpty() && freshOranges > 0){

            int qSize = q.size();

            // Loop to make sure ALL infected oranges continue to infect other neighbors at each time interval, simultaneously
            for(int i = 0; i < qSize; i++){

                int[] curRotenOrange = q.remove();

                for(int[] dir : directions){
                    int row = curRotenOrange[0] + dir[0];
                    int col = curRotenOrange[1] + dir[1]; 

                    // Boundary check and check for fineOrange
                    if(0 <= row && row < grid.length && 0 <= col && col < grid[0].length ){                

                        if(grid[row][col] == 1 && freshOranges > 0){

                            // Mark as rotten, reduce count and enqueue      
                            freshOranges--;
                            grid[row][col] = 2;
                            q.add(new int[] {row, col});    
                        }                       
                    }
                 }             
            }            
        
            countOfMinutes++;
                
        }

        return (freshOranges > 0) ? -1 : countOfMinutes;          
    }


    public static void main(String[] args){

        RottenOranges obj = new RottenOranges();
    
        int[][] grid1 = new int[][]{{2,1,1},{1,1,0},{0,1,1}}; //  expected res = 4
        int[][] grid2 = new int[][]{{2,1,1},{0,1,1},{1,0,1}}; //  expected res = -1
    
        System.out.println("Expected res : " + obj.orangesRotting(grid1));
        System.out.println("Expected res : " + obj.orangesRotting(grid2));
    
    }



}





