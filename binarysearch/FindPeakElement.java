
// Time : O(Log n)

public class FindPeakElement {

    public int findPeakElement(int[] nums) {

        int low = -1, high = nums.length - 1;
 
         while(low + 1 < high){
 
            int mid = low + high - low/2;
 
             /* Note : we dont need to check : && nums[mid] < nums[mid - 1]
                 as in the case it fails, the number to left (nums[mid - 1]) will be the peak element. 
             */
            if(nums[mid] > nums[mid + 1]){
 
                 high = mid;        
            }
 
            else {
                 low = mid;
            }
         }
 
         return high;        
     }
 

    // Minimization problem

}
