package com.app.sorting;

import com.app.common.CommonUtil;

public class MergeSort {   
    

    // Time complexity : O(n log n)
    // Space complexity :: O(n)
    public int[] mergeSort(int[] arr) {

        int n = arr.length;
        if (n < 2) return arr; // One item in array
        
        
        int mid = n / 2;

        // Create left and right array to hold the split array values.
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        
        
        // Populate left and right arrays
        for (int i = 0; i < mid; i++)  left[i] = arr[i];
                
        for (int i = mid; i < n; i++)   right[i - mid] = arr[i];
        

        // Keep calling recursively until original array is split into individual components.
        mergeSort(left);
        mergeSort(right);

        merge(left, right, arr);

        return arr;
    }

    private int[] merge(int[] left, int[] right, int[] arr) {
              
        int i = 0, j = 0, k = 0;

        // Compare i and j pointers each time
        while (i < left.length && j < right.length) {

            if (left[i] <= right[j]) {

                arr[k] = left[i];
                i++;

            } else {

                arr[k] = right[j];
                j++;
            }

            k++;
        }

        // To Account for elements which may have left in the array without being compared
        
        while (i < left.length) {

            arr[k] = left[i];

            i++;
            k++;
        }

        while (j < right.length) {

            arr[k] = right[j];
            
            j++;
            k++;
        }

        return arr;
    }




    public static void main(String args[]) {

        int[] arr = {0, 8, 1, 1, 4, 3, 2, 6, 7, 5, 9};
        MergeSort m = new MergeSort();
        arr = m.mergeSort(arr);
        CommonUtil.printArray(arr);
    }


}
