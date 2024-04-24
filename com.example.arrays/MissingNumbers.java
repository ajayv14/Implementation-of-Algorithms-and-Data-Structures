
//credits: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
//credits: Bharthi Kyada  
import java.util.List;
import java.util.ArrayList;

/*Q: Given an array of integers where 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.*/

/*run the loop, calculate the index and set the value in the index to -ve*/
/*the indexes that were skipped by the loop are the missing ones  -- credits Bharthi Kyada*/

class MissingNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int index = Math.abs(nums[i]) - 1;

            if (nums[index] > 0)
                nums[index] *= -1;
            // else ignore
        }

        for (int j = 0; j < nums.length; j++) {

            if (nums[j] > 0)
                res.add(j + 1); // if nums[j] positive, then add + 1 to j to change index to number
        }
        return res;
    }

    /* test */
    public static void main(String[] args) {

        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 }; // [5,6]
        // int[] nums = {4,4,1,2,2}; // 3,5

        MissingNumbers obj = new MissingNumbers();
        List<Integer> res = obj.findDisappearedNumbers(nums);
        System.out.println(res);
    }
}