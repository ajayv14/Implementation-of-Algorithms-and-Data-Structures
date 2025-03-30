package com.app.array;

public class MinCostPositions {

    // LC 3502 : https://leetcode.com/problems/minimum-cost-to-reach-every-position/

    public int[] minCosts(int[] cost) {

        if(cost == null || cost.length == 0) return new int[] {};
        
        int[] spend = new int[cost.length];

        spend[0] = cost[0];

        int minCost = spend[0];
        
        for(int i = 1; i < cost.length; i++){

            if(cost[i] >= minCost){
                spend[i] = minCost;
            }

            else {

                spend[i] = cost[i];

                minCost = Math.min(minCost, cost[i]);
            }
        }

        return spend;
        
    }
}
