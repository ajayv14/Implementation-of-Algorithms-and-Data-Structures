package com.app.arrays;

import java.util.HashMap;

import com.app.common.CommonUtil;

class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if (map.containsKey(sum - k)) { // subarray is found
                result += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1); // if not present in hashmap, will get 0
        }
        return result;
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
        CommonUtil.runExample("1,1,1","2", obj.subarraySum2(input1, k1) + "");


        int[] input2 = new int[]{1,2,3};
        int k2 = 3;
        CommonUtil.runExample("1,2,3","2", obj.subarraySum2(input2, k2) + "");

        int[] input3 = new int[]{0,0,0,0,0,0,0,0,0,0};
        int k3 = 0;
        CommonUtil.runExample("0,0,0,0,0,0,0,0,0,0", "55", obj.subarraySum2(input3, k3) + "");


    }

}