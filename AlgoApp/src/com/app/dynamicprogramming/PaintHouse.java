package com.app.dynamicprogramming;

// LC 256 https://leetcode.com/problems/paint-house/description/
// Custom soln for min cost to paint house
public class PaintHouse {

    public int minCost(int[][] costs) {

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
  /*  public int minCost(int[][] costs) {

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
