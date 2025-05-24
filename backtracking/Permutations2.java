import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// nums = [1,1,2]
// Output: [[1,1,2], [1,2,1], [2,1,1]]
public class Permutations2 {

    boolean[] track; 

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(nums);

        track = new boolean[nums.length];

        backtrack(results, new ArrayList<>(), nums);

        return results;
        
    }

    /*
    i > 0: Ensures we're not looking at the first element

    nums[i] == nums[i-1]: Checks if the current element is a duplicate of the previous element

    !used[i - 1]: Checks if the previous duplicate element is NOT used in the current permutation path
    
    Like [1,1,2]), we only want to use the second "1" if we've already used the first "1" in the current permutation path.

    If we encounter the second "1" and the first "1" is not used 
    (meaning we've backtracked from a path that used the first "1"),
    we skip the second "1" to avoid generating the same permutation again.    
    */
    private void backtrack( List<List<Integer>> results, List<Integer> tempList, int[] nums){

        if(tempList.size() == nums.length) {

            results.add(new ArrayList<>(tempList));

        }

        for(int i = 0; i < nums.length; i++){

            if(track[i] || i > 0 && nums[i] == nums[i - 1] && !track[i - 1]) continue;

            tempList.add(nums[i]);

            track[i] = true;

            backtrack(results, tempList, nums);    

            tempList.remove(tempList.size() - 1);

            track[i] = false;
        }
    }
}
