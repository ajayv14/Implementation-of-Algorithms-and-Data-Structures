// credits: Nick White : https://www.youtube.com/watch?v=uvB-Ns_TVis

/**
Initialize three pointers: low, mid, and high.

low points to the beginning of the array, mid points to the current element being considered, and high points to the end of the array.

Traverse the array with the mid pointer:

    If the current element is 0, swap it with the element at the low pointer and increment both low and mid pointers.
If the current element is 1, simply move the mid pointer forward.
If the current element is 2, swap it with the element at the high pointer and decrement the high pointer without moving the mid pointer.
Continue this process until the mid pointer surpasses the high pointer.
 */


 // https://leetcode.com/problems/sort-colors

// Time O(n) - 1 pass
// Space O(1)
 class SortColors {

    
    public void sortColors(int[] nums) {
        
        
        int start = 0, end = nums.length - 1;
        
        int i = 0;
        
        while(i <= end  && start < end){
            
            
            if(nums[i] == 0) {
                // swap current index value with index start
                nums[i] = nums[start];
                nums[start] = 0;
                start++;
                i++; // only for == 0 case
            }
            
            else if(nums[i] == 2){
                // push it to end- swap with end
                nums[i] = nums[end];
                nums[end] = 2; 
                end--;   // we dont increment i here as 2 is to be pushed to end and corner case --[2,0,2,1,1,0]
            }            
            
            else {
                i++; // Should not increment i, if a swap has been performed for == 2-- e.g [1,2,0]
            }
        }      
    }
}