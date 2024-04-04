/**
 * credits: nick white https://www.youtube.com/watch?v=QdVrY3stDD4
 * logic : find the actual starting point of the array from which it was
 * rotated.
 * perform binary search accordingly
 *
 **/

class SearchSortedRotatedArray {

    public int search(int[] nums, int target) {

        if (nums == null || nums.length <= 0)
            return -1;

        /* find smallest number's index (start point of array) */

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }

            else {
                right = mid;
            }
        }

        int start = left; // smallest num index stored in start
        left = 0;
        right = nums.length - 1;

        while (left <= right) { // boundary

            if (target >= nums[start] && target <= nums[right]) {
                left = start; // meaning number is on right side in example [4,5,6,7,0,1,2]
            } else {
                right = start; // on left side of array in example [4,5,6,7,0,1,2]
            }

            /* binary search */
            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (target == nums[mid])
                    return mid;

                else if (target < nums[mid]) { // target on left side
                    right = mid - 1;
                }

                else {
                    left = mid + 1;
                }

            }

        }

        return -1;
    }

    /* Easy to understand - old method */

    public int searchOld(int[] nums, int target) {

        if (nums == null || nums.length < 1)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = (low + high) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[low] <= nums[mid]) { /* checking if left half is in ascending order */

                if (target >= nums[low] && target < nums[mid]) { // target present in left half

                    high = mid - 1;

                } else { // target is in right half

                    low = mid + 1;
                }
            }

            else { /* left half not in ascending order */

                if (target > nums[mid] && target <= nums[high]) { // target present in right half

                    low = mid + 1;

                } else {

                    high = mid - 1; // target present in left half
                }

            }

        }

        return nums[low] == target ? low : -1;

    }

    /* Test */
    public static void main(String[] args) {

        int[] nums = { 4, 5, 6, 7, 0, 1, 2 }; // op = 4a
        SearchSortedRotatedArray obj = new SearchSortedRotatedArray();
        int res = obj.search(nums, 0);
        System.out.println(res);
    }

}