package com.app.array;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class TripletsSumToZero {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue; // skip repeating values

            int start = i + 1;
            int end = nums.length - 1;

            int target = -1 * nums[i]; // set target as -ve of the nums[i] so that it sums to 0;

            while (start < end) {

                if (nums[start] + nums[end] == target) {

                    result.add(Arrays.asList(nums[start], nums[end], nums[i]));
                    start++;
                    end--;

                    // start is pointing to next element and if it is same as previously found, skip
                    // it and move start pointer to right(start++)

                    while (start < end && nums[start] == nums[start - 1])
                        start++;

                    // end is pointing to next element on its left and if same, then skip and move
                    // end pointer to left (end--)

                    while (start < end && nums[end] == nums[end + 1])
                        end--; // ignore same result

                }

                else if (nums[start] + nums[end] > target) { // due to sorting , number to the left are small in array

                    end--;
                }

                else { // nums[start] + nums[end] < target

                    start++;
                }

            }

        }

        return result;

    }
}