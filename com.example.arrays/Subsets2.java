import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * credits :
 * https://leetcode.com/problems/subsets/discuss/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
 * : https://leetcode.com/problems/subsets-ii/
 **/

class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {

        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1])
                continue;
            /*
             * i > start prevents index out of bounds exception, continue - breaks and moves
             * to next iteration of loop
             */
            temp.add(nums[i]);
            backtracking(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}