package com.app.dynamicprogramming;

// credits: Kevin Naugton jr. https://www.youtube.com/watch?v=xlvhyfcoQa4
// 2) JAVAAID : https://www.youtube.com/watch?v=ge_Fv2ZQAaE&list=PLSIpQf0NbcCk2O05hkxHPtVqGRGtnClh8&index=9

class HouseRobber {
    /*Logic : Basically, we can't rob two adjacent homes, so in the dp, we can either choose to include the current home (rob it)
        In doing so, we can only add the sum of home[i - 2] as it is not adjacent.
        or we can skip it...so we will choose [i - 1] home
        So we take max of dp[current - 1]--> The best tally upto the one minus current home vs (current - 2) + curren5t home To make sure we don't           rob adjacent homes.    
    */    
    
    public int rob(int[] nums) {
        // base cases
        if(nums == null | nums.length <= 0 )return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[nums.length];   
        
        // since we are considering i -1 or i -2 + current, to avoid running into index not found exception
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        
        for(int i=2; i<dp.length; i++){
            
            /* If we skip current home, then we automatically have to choose [i - 1] adjacent home or
            if we choose current home, then we can add items from [i - 2] home as we cant rob adjacent homes*/
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);            
            
        } 
            
        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
        
    }
    
    
    public static void main(String[] args){
         
         //input
         int[] arr = {1,2,3,1}; //op 4
         //int[] arr = {20,7,3,12,1}; //op 32
         
         HouseRobber obj = new HouseRobber();
         int res = obj.rob(arr);
         System.out.println(res);  
    }
}