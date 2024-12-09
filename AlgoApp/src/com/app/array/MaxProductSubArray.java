package com.app.array;

class MaxProductSubArray {
    public int maxProduct(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int maxCopy = max; /* to find min, we need max, but max gets modified by then, hence maxCopy */

            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);

            // System.out.println("max" + max);

            min = Math.min(Math.min(maxCopy * nums[i], min * nums[i]), nums[i]); /* usage of maxCopy */

            // System.out.println("min" + min);

            if (max > result)
                result = max;

            // System.out.println("res" + result);

        }

        return result;

    }
}