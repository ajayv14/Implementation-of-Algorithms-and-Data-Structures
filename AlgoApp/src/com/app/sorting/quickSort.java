package com.app.sorting;

import com.app.common.CommonUtil;

// Quick Sort Approach:

// 1. Choose a pivot element.
// 2. Partition the array so that:
//    - Elements to the left of the pivot are less than or equal to the pivot.
//    - Elements to the right of the pivot are greater than the pivot.
// 3. After partitioning, the pivot is in its correct sorted position.
// 4. Recursively apply the same process to the left and right sub-arrays (excluding the pivot).
// 5. Base case: when the sub-array has 1 or 0 elements, it is already sorted.


// Time : best, avg O(n log n), worst O(n) pow 2
// Space : average recursion O(log n), worst O(n)

// LC 912 https://leetcode.com/problems/sort-an-array/
public class quickSort {
           
    
    private int[] quicksort(int[] nums, int left, int right){

        if(left < right){

            int partitionIdx = partition(nums, left, right);

            // Now since partition idx is sorted and in correct place
            // recursively partiton rest of array before and after partition index
            quicksort(nums, left, partitionIdx - 1 ); // before p idx
            quicksort(nums, partitionIdx + 1, right);           
        }

        return nums;

    }

    private int partition(int[] nums, int left, int right){
        
        int pivotValue = nums[right];

        int i = left - 1; // one step behind left
        int j = left; // Just for understanding purpose

        //partitionVal acts as a reference point
        for(j = left ; j < right; j++){

            // Find a num lesser than pivot, swap with i    
            if(nums[j] < pivotValue){

                i++; // 

                swap(nums,i,j);
            }
        }    

        // Swap i + 1 with pivot
        swap(nums, i + 1, right);

        return i + 1;
    }

    private void swap(int nums[], int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 9, 7, 0};
 
        quickSort q = new quickSort();
        
        int[] res = q.quicksort(arr, 0, arr.length - 1);
        
        CommonUtil.printArray(arr);      
      
    }
    
}