package com.app.arrays;

import java.util.HashMap;

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

    // easy to understand soln:

    public int subarraySum2(int[] nums, int k) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            int currentSum = nums[i];

            if (currentSum == k)
                count++;

            for (int j = i + 1; j < nums.length; j++) {

                currentSum += nums[j];
                if (currentSum == k) {
                    count++;
                    // break; // this is a good condition to keep, but [0,0,0,0,0,0,0,0,0,0] has 55
                    // cont.sub arrays where sum = 0;
                }
            }

        }

        return count;

    }

    /* test */

}