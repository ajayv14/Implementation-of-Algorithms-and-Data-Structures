
// LC : 303 : https://leetcode.com/problems/range-sum-query-immutable/


public class RangeSumQuery {


  // 0 <= left <= right < nums.length - constraints

    int[] prefixSum;

    public RangeSumQuery(int[] nums) {
        
        prefixSum = new int[nums.length + 1];

        prefixSum[0] = 0; // Dummy, don't have to assign

        for(int i = 0; i < nums.length; i++){

            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    
    }
    
    public int sumRange(int left, int right) {
       
       return prefixSum[right + 1] - prefixSum[left];
              
    }  

}
