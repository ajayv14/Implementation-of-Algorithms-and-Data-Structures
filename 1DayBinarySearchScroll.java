


// Time : O(Log n)
// Input: nums = [1,2,3,1]
// Output: index ; 2
public class FindPeakElement {

    public int findPeakElement(int[] nums) {

        int low = -1, high = nums.length - 1;
 
         while(low + 1 < high){
 
            int mid = low + high - low/2;
 
             /* Note : we dont need to check : && nums[mid] < nums[mid - 1] (sorted array)
                 as in the case it fails, the number to left (nums[mid - 1]) will be the peak element. 
             */
            if(nums[mid] > nums[mid + 1]){
 
                 high = mid;    // pay attention    
            }
 
            else {
                 low = mid;
            }
         }
 
         return high;        
     }
 

    // Minimization problem

}


/**
 * 
 *  [2,3,4,7,11], k = 5
 * 
 *  arr[mid] - (mid + 1)
 * 
 * At mid = 2, num is 4, so  4 - (2 + 1) = 1, the 1st missing number
 * At  mid = 3, num is 7, so  7 - (3 + 1) = 2, the 2nd missing number
 *  
 * So arr[mid] - (mid + 1) = k will give the answer
 */


 public class KthMissingPositiveNumber {

  // LC 1539 :  https://leetcode.com/problems/kth-missing-positive-number/
 // Minimization problem
 //Input: arr = [2,3,4,7,11], k = 5
//Output: 9
    public int findKthPositive(int[] arr, int k) {

              
        int low = -1, high = arr.length;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(arr[mid] - (mid + 1) < k) low = mid;

            else high = mid;  

        }
            
        return high + k;
        
    }   
    

}


// optimized binary search version

//https://leetcode.com/problems/missing-element-in-sorted-array/
//  all of its elements are unique 
// missing number starting from the leftmost number of the array.
//Input: nums = [4,7,9,10], k = 1
//Output: 5
class MissingElementInSortedArray {
    
    // Kth missing
    public int missingElement(int[] nums, int k) {
      

        int low = -1, high = nums.length;

        while(low + 1 < high){
            
            int mid = low + (high - low)/2;

            // from start to end, not just compared to previous num
            // Shows the diff btw actual num at mid index vs what is present, 
            //using which we find what is missing 
            int missNumCount = nums[mid] - nums[0] - mid; 

            if(missNumCount < k ) low = mid;

            else high = mid;           

        }      


        // starting point : first number + 
        // current index at high (place where we have k missing numbers) + 
        // k missing nums

        // nums[0] -> starting number + index 'high' represents upto k numbers missing idx -> Identified num of missing. 
        // So add k to this to obtain the kth num, compensate for 0th idx by adding -1.
        return nums[0] + high + k - 1; // - 1 as high is set to out of bounds in begining
    }
}


public class MedianOfTwoSortedArrays {

   

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n1 = nums1.length, n2 = nums2.length;

        int total = (n1 + n2 + 1) / 2;

        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
        
        
        int low = 0, high = n1;

        while(low <= high){

            int mid1 = low + (high - low) / 2;

            int mid2 = total - mid1;



            int l1 = mid1 - 1 >= 0 ? nums1[mid1 - 1] : Integer.MIN_VALUE;
                
            int r1 = mid1 < n1 ? nums1[mid1] : Integer.MAX_VALUE;


            int l2 = mid2 - 1 >= 0 ? nums2[mid2 - 1] : Integer.MIN_VALUE;
                
            int r2 = mid2 < n2 ? nums2[mid2] : Integer.MAX_VALUE;

            
            if(l1 <= r2 && l2 <= r1){

                if((n1 + n2) % 2 == 0) return (Math.max(l1,l2) + Math.min(r1,r2)) / 2.0; 

                else return Math.max(l1, l2);

            }

            else if(l1 > r2) high = mid1 - 1;

            else  low = mid1 + 1;             
                
        }

        
        return -1;

    }

   
}


//Input: ribbons = [9,7,5], k = 3
//Output: 5
// https://leetcode.com/problems/cutting-ribbons/description/
//determine the maximum length of ribbon, x, that allows you to cut at least k ribbons, each of length x
public class CuttingRibbons {

    // LC 

    // Binary search optimized 
    public int maxLength(int[] ribbons, int k) {

        // k is not the length, but number of ribbons   
   
           // min len is 1, so left boundary is 1, right boundary ?? below
           int max = 0;
   
           for(int i = 0; i < ribbons.length; i++ ){
   
               max = Math.max(max, ribbons[i]);
           }    
   
           // Now length of ribbon is btw 1 and max. We optimize this value by binary search.
   
           // Binary search
           // Maximization problem
           // min length = 1 and max len = max
           // invalid range to begin with 
           // in maximization problem return lo  
           
           // Lets say max is 9, then possible lengths are 0 1 2 3 4 5 6 7 8 9. 
           // Use binary search to pic the right length
           int low = 0, high = max + 1;
   
           while(low + 1 < high){
   
               int mid = low + (high - low)/2;
   
               boolean isPossible = isMaxLenPossible(ribbons, mid, k);
   
               if(isPossible) low = mid;
   
               else high = mid;
           }    
           
           return low;
       }
   
       private boolean isMaxLenPossible(int[] ribbons, int len, int k){
   
           int sum = 0;
   
           for(int r = 0; r < ribbons.length; r++){
   
               sum += Math.floor(ribbons[r] / len);
           }
   
           return sum >= k ? true : false;
   
       }



    //Input: ribbons = [9,7,5], k = 3
    //Output: 5
    // Brute force  
    public int maxLength2(int[] ribbons, int k) {

        // k is not the length, byt number of ribbons   
          
   
           // min len is 1, so left boundary is 1, right boundary ?? below
           int max = 0;
   
           for(int i = 0; i < ribbons.length; i++ ){
   
               max = Math.max(max, ribbons[i]);
           }    
   
           // length of ribbons possible
           for(int l = max; l > 0; l--){ // try one length by one, starting from max length
   
               int sum = 0;
   
               for(int j = 0; j < ribbons.length; j++){
   
                   sum += Math.floor(ribbons[j]/l);
               }
   
               if(sum >= k) return l;
               
           }     
   
           return 0;
           
       }
}




// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class SearchRotatedSortedArray {


    // Find min using minimization bin search template, then look for target in right half or left half.

    // Time limit exceeded.

    // LC 33 - https://leetcode.com/problems/search-in-rotated-sorted-array
    
    public int search(int[] nums, int target) {

        int minIndex = findMin(nums);

        //System.out.println("min idx : " + minIndex);
     
        if(target <= nums[nums.length - 1]) return binSearch(nums, target, minIndex, nums.length - 1);

        else return  binSearch(nums, target, 0, minIndex - 1);
        
    }


    private int findMin(int[] nums){

        int low = -1, high = nums.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;
            
            if(nums[mid] < nums[nums.length - 1]) high = mid;

            else low = mid;

            System.out.println("low : " + low + " high : " + high);
        }

        return high;
    }

    private int binSearch(int[] nums, int target, int low, int high){

        //System.out.println("low : " + low + " high : " + high);

        while(low <= high){

            int mid = low + (high - low)/2;

            if(target == nums[mid]) return mid;

            else if(target < nums[mid]) high = mid - 1;

            else low = mid + 1;           

        }
        return -1;

    }

    

}


/**
 * 
 *  [2,3,4,7,11], k = 5
 * 
 *  arr[mid] - (mid + 1)
 * 
 * At mid = 2, num is 4, so  4 - (2 + 1) = 1, the 1st missing number
 * At  mid = 3, num is 7, so  7 - (3 + 1) = 2, the 2nd missing number
 *  
 * So arr[mid] - (mid + 1) = k will give the answer
 */



// First greater element 
// [2,3,4,7,11], target = 4, op = 7


 
// First greater element 
// [2,3,4,7,11], target = 4, output = 7

public class FirstGreater {
    
    // Minimization problem
    public int findGreater(int[] arr, int k) {

              
        int low = -1, high = arr.length; // last element can't have a next greater

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(arr[mid] <= target) low = mid;

            else high = mid;  

        }

        // Check if high is within bounds
        if(high == arr.length) return -1; // No element greater than k found
            
        return high;
        
    }  
    

}


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
        int high = nums.length; // note

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
        int high = nums.length; // note edge case[1]

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





