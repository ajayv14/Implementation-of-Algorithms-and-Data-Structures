package com.app.sorting;

public class BubbleSort {

    /*Bubble sort
	   	logic - push the largest element to the end of array-, eventually array gets sorted thsi way
	    1) take element i, check if i+1 is less that, if so then swap
	    2) repeat this iteration for N number of times.
	  */
    
    public int[] bubbleSort(int[] arr) {
        
        int N = arr.length; 
        int flag = 0;
        
        for (int i = 0; i < N; i++) { // outer loop
        
            for (int j = 0; j < N - i - 1; j++) {
            
                // stops 2 indices before the end of array
                // because i+1 will exceed end of array
                
                if (arr[j] > arr[j + 1]) {
                
                    swap(j, j + 1, arr);
                    //optimization- if the iteration passes without swaps,
                    //meaning array is already sorted, so check if flag is 0
                    // set flag to 1 , if there is a swap
                    flag = 1;
                }
            }

            if (flag == 0) break; // array is sorted
        }
        
        return arr;
    }

    public int[] swap(int i, int j, int[] arr) {
        
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        
        return arr;
    }

    public static void main(String[] args) {
	  
        int[] arr = {2, 5, 1, 4, 1, 7, 10};
          
        BubbleSort h = new BubbleSort();
        
        int[] res = h.bubbleSort(arr);
        
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
       
    }  

 }