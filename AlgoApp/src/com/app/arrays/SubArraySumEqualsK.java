package com.app.arrays;

import java.util.HashMap;
import java.util.Map;

import com.app.common.CommonUtil;

// https://leetcode.com/problems/subarray-sum-equals-k/description/
// LC 560
class SubArraySumEqualsK {
    

    /*
    Logic - Store prefix sum at each index. 
    Now find if current prefixSum - K is present in the stored prefixSums.
    
    sum(nums[i,j] = prefixSum[j] -  prefixSum[i - 1])
    prefixSum[i - 1] = prefixSum[j] - k; 
  */

// Time and space : O(N)
  public int subarraySum(int[] nums, int k) {

    // PrefixSum - frequency
    Map<Integer, Integer> map = new HashMap<>();

    int prefixSum = 0, ans = 0;

    // imagine a prefix sum array. Starts with 0 outside.
    map.put(0,1); 

    for(int num : nums){

        prefixSum += num;

        // Since we have prefix sum, find if a number prefixSum - k exists, so it satisifies equation or find missing complementing the equation.

        ans += map.getOrDefault(prefixSum - k, 0);

        // update prefix sum count
        map.put(prefixSum, map.getOrDefault(prefixSum,0) + 1);
    }

    return ans;

  }




    // Easy to understand soln:
    // Time complexity = O(n^2)
    // Space complexity = O(1)
    public int subarraySum2(int[] nums, int k) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            int sum = 0;

            for (int j = i; j < nums.length; j++) {

                sum += nums[j];

                if (sum == k) {
                    count++;
                    // break; //  but [0,0,0,0,0,0,0,0,0,0] has 55 cont.sub arrays where sum = 0;
                }
            }
        }
        return count;
    }

    /* test */
    public static void main(String[] args) {
        
        SubArraySumEqualsK obj = new SubArraySumEqualsK();

        int[] input1 = new int[]{1,1,1};
        int k1 = 2;
        CommonUtil.runExample("1,1,1","2", obj.subarraySum(input1, k1) + "");


        int[] input2 = new int[]{1,2,3};
        int k2 = 3;
        CommonUtil.runExample("1,2,3","2", obj.subarraySum(input2, k2) + "");

        int[] input3 = new int[]{0,0,0,0,0,0,0,0,0,0};
        int k3 = 0;
        CommonUtil.runExample("0,0,0,0,0,0,0,0,0,0", "55", obj.subarraySum(input3, k3) + "");


    }

}