package com.app.dynamicprogramming;

// LC 256 https://leetcode.com/problems/paint-house/description/
// Custom soln for min cost to paint house
public class PaintHouse {

    // OPtimized solution : https://www.youtube.com/watch?v=-w67-4tnH5U

    /* Optimized solution - We simple need previous row - house costs. Hence compute costc for 3 colors individually and then copy them back into a single dim array, instead of a n * 3 grid */

    public int minCost(int[][] costs) {

        int[] cost = new int[costs[0].length];    
       
        // cost of painting first house with each color in R B G
        for(int j = 0 ; j < costs[0].length; j++){                     
            cost[j] = costs[0][j];
        }

        // for eacg home - row
        for(int i = 1 ; i < costs.length; i++){

                // For each col, compute the next 3 values
                // Apply logic - cost of choosing something + cost of not choosing something
                // col 0
                // Cost of choosing Red and cost of choosing either Blue or Green for previous home (min of Blue and Green)
                int r = costs[i][0] + Math.min(cost[1], cost[2]);

                int g = costs[i][1] + Math.min(cost[0], cost[2]);

                int b = costs[i][2] + Math.min(cost[0], cost[1]);
                                       
                cost[0] = r; cost[1] = g; cost[2] = b;
            
        }


        int minCost = Integer.MAX_VALUE;

        for(int j = 0 ; j < cost.length; j++){                     
            minCost = Math.min(minCost, cost[j]);
        }

        return minCost;
 
    }

    // Grid based solution - 
    public int minCost2(int[][] costs) {

        int[][] cost = new int[costs.length][costs[0].length];    
       
        // cost of painting first house with each color in R B G
        for(int j = 0 ; j < cost.length; j++){                     
            cost[j] = costs[j];
        }

        for(int i = 1 ; i < cost.length; i++){

            for(int j = 0; j < cost[0].length; j++){

                cost[i][j] = costs[i][j];

                // Cost of choosing Red and cost of choosing either Blue or Green for previous home (min of Blue and Green)
                // Apply logic - cost of choosing something + cost of not choosing something
                if(j == 0) cost[i][j] += Math.min(cost[i - 1][j + 1], cost[i - 1][j + 2]);

                else if (j == 1) cost[i][j] += Math.min(cost[i - 1][j - 1], cost[i - 1][j + 1]);

                else if (j == 2) cost[i][j] += Math.min(cost[i - 1][j - 1], cost[i - 1][j - 2]);

            }
        }


        int minCost = Integer.MAX_VALUE;

        for(int j = 0 ; j < cost[0].length; j++){                     
            minCost = Math.min(minCost, cost[cost.length - 1][j]);
        }

        return minCost;
 
    }


    

    /** Subpronblems - dfs based solution */
    public int minCost1(int[][] costs) {

        return Math.min(dfs(0, 2, costs), Math.min(dfs(0, 1, costs),dfs(0, 0, costs)));
    }

   private int dfs(int houseNum, int color, int[][] costs){
      
        int cost = costs[houseNum][color]; 

         if(houseNum == costs.length - 1) return cost;

        if(color == 0) {
            cost += Math.min( (dfs(houseNum + 1, color + 1, costs)) , (dfs(houseNum + 1, color + 2,costs)));
        }  
        else if(color == 1) {
            cost += Math.min((dfs(houseNum + 1, color - 1, costs)), (dfs(houseNum + 1, color + 1, costs)));
        }            
        else if(color == 2) {
            cost += Math.min( (dfs(houseNum + 1, color - 1, costs)) , (dfs(houseNum + 1, color - 2,costs)));
        }           
        return cost;
    }*/
}
