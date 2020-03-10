//credits : https://www.youtube.com/watch?v=jnoVtCKECmQ 

// Kadane's Algorithm

class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //Using DP
            
        int currentMax = nums[0]; //first element by default
        int maxOfMax = nums[0];
        
        for(int i=1;i<nums.length;i++){ //note: start from index 1
            
            currentMax = Math.max(nums[i],currentMax+nums[i]);
            maxOfMax = Math.max(maxOfMax,currentMax);
            
        }
        
        return maxOfMax;
        
    }
}
