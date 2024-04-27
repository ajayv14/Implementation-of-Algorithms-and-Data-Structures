package com.app.dynamicprogramming;



class ClimbingStairs {

    /*optimal cost depends on the previous step and the one before that*/

    public int minCostClimbingStairs(int[] cost) {
                  
        int[] dp = new int[cost.length];
        
        dp[0]=cost[0];
        dp[1]=cost[1];
        
        for(int i=2;i<cost.length;i++){
            dp[i]=cost[i] + Math.min(dp[i-1],dp[i-2]);
        }        
        return Math.min(dp[cost.length-1],dp[cost.length-2]);
        
    }
    
    public static void main(String[] args){
        
        int[] cost = new int[]{10,15,20}; 
        
        ClimbingStairs obj = new ClimbingStairs();
        int res = obj.minCostClimbingStairs(cost);
        System.out.println("optimal cost"+res); 

    }


    }