class LongestIncreasingSubSequence {
    public int lengthOfLIS(int[] nums) {
        
      /*DP Solution, easy to understand O(n^2)*/  
        int n = nums.length;
        
        int[] dp = new int[n];
         
        /*fill array with 1s---- minumum length of subsequence is 1 */        
        
        Arrays.fill(dp,1);
        
        
        /*i - starting from index 1, if value at index j is smaller, we have an increasing subsequence*/
        for(int i = 1; i < n; i++){
            
            for(int j = 0; j < i; j++){               
                
                if(nums[j] < nums[i]){
                    
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    
                }        
                
            }
            
        }
        
        
        int res = 0;
        
        /*Iterate through the new array and find the largest number*/
        for(int c = 0; c < dp.length; c++){
            
            res = Math.max(res, dp[c]);
            
            //System.out.println(dp[c]);          
            
        }
        
        
       return res;       
        
    }
}