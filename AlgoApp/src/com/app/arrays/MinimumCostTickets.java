package com.app.arrays;

public class MinimumCostTickets {

    

    public int findMinCost(int[] departing, int[] returning) {

        int minDepCost = departing[0];
        int minTripCost = Integer.MAX_VALUE;
        

        for(int i = 1; i < departing.length; i++){

            minDepCost = Math.min(minDepCost, departing[i]);

            minTripCost = Math.min(minTripCost, minDepCost + returning[i]); // i + 1 -> can't return on same day

        }

        return minTripCost;
        
    }
    
    public static void main(String[] args) {

        int[] departing = {1, 2, 3, 4};
        int[] returning = {4, 3, 2, 1};
        
        MinimumCostTickets obj = new MinimumCostTickets();

        int cost = obj.findMinCost(departing, returning);
        
        System.out.println("Expected : 2 " + "actual : " + cost);
    }
}
