import java.util.HashSet;
import java.util.Set;

class DuplicateInArray {
    public boolean containsDuplicate(int[] nums) {

        /* using hash set -- Time O(N) Space O(N) */

        Set<Integer> distinct = new HashSet<>();

        for (int n : nums) {

            if (!distinct.contains(n)) {

                distinct.add(n);

            }

            else
                return true;

        }

        return false;

    }
}