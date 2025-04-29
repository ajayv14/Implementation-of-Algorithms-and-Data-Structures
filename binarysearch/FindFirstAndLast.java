

// LC 34 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


/**
 * 
 * Minimization problem - Find first - somewhat like find in first half of array, 
 * so we try to bring high closer to mid. Return high
 * 
 * Maximization - find last - Somewhat answer is towards end of array, so push low towards end, return low.
 * 
 * nums = [5,7,7,8,8,8,8,8,8,8,10], target = 8
 * 
 *  Find first      | |
 *             [5,7,7,8,8,8,8,8,8,8,10]
 *                low high
 * 
 * 
 *  Find last                     | |
 *             [5,7,7,8,8,8,8,8,8,8,10]
 *                               low high
 * 
 * 
 */                



public class FindFirstAndLast {

    /**
        Use binary search minimization and maximization template.
     */
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];

        res[0] = findFirst(nums,target);
        res[1] = findLast(nums,target);

        return res;
        
    }

    private int findFirst(int[] nums, int target){

        int low = -1;
        int high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low) /2;

            if(nums[mid] >= target) high = mid;

            else low = mid;
        }

        // Number doesn't exist - Either too high or too low
        if(high == nums.length || nums[high] != target) return -1;

        return high;
    }

    private int findLast(int[] nums, int target){

        int low = -1;
        int high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low) /2;

            if(nums[mid] <= target) low = mid;

            else high = mid;
        }

        // Number doesn't exist - Either too high or too low
        if(low == -1 || nums[low] != target) return -1;

        return low;

    }

}
