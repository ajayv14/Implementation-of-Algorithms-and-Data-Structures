package com.app.dynamicprogramming;

class ClimbStairs{

      public int climbStairs(int n) {
        
        //F(n) = F(n - 1) + F(n - 2)
        /* store n - 1 and n - 2 into two variables, as we just require previous two values */
        
        int a = 1, b = 2, c = 0; // a, b are two possible ways to reach a step
        
        if( n <= 2) return n; // n = 1, just one way and n == 2, just 2 ways are possible
        
        // for 3 steps onwards
        for(int i = 3; i <= n; i++){
            
            c = a + b; // F(n - 1 ) + F (n - 2)
            a = b;
            b = c;
        }      
        return c;        
    }      



     /*optimal cost depends on the previous step and the one before that*/

     public int minCostClimbingStairs2(int[] cost) {
                  
      int[] dp = new int[cost.length];
      
      dp[0]=cost[0];
      dp[1]=cost[1];
      
      for(int i=2;i<cost.length;i++){
          dp[i]=cost[i] + Math.min(dp[i-1],dp[i-2]);
      }        
      return Math.min(dp[cost.length-1],dp[cost.length-2]);
      
  }
      
      public static void main(String[] args){
        ClimbStairs obj = new ClimbStairs();
            System.out.println(obj.climbStairs(3));
      }

}