

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * credits :
 * https://leetcode.com/problems/subsets/discuss/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 **/

// LC 90 : https://leetcode.com/problems/subsets-ii/

// nums may contain duplicates, set must not contain duplicate subsets

// Input: nums = [1,2,2]
// Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // necessary when duplicates are present
        backtracking(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    /* we have duplicates, so [1,2,2] will have multiple duplicate subsets like 
                [[],[1],[1,2],[1,2,2],[1,2],[2],[2,2],[2]]
                
                to avoid it, we check if nums[i] == nums[i -1] and continue if so.
                
                at index i = 0, i - 1 = -1 will throw arrayOut of bounds exception.
                
                i > start prevents index out of bounds exception, continue - breaks and moves to next iteration of loop*/
           
    private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {

        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) continue;
           
            temp.add(nums[i]);
            backtracking(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}