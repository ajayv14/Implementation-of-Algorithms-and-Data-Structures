package com.app.sorting;

public class InsertionSort {
   /*algo 
       1-2-3-4-5-
       i,j
       n--> length of arr
       for i 0---> n-1 
         for j i---> >0
       swap if arr [j} < arr[j-1]      
   */

    public int[] insertionSort(int[] arr) {
    
        for (int i = 0; i < arr.length; i++) {
        
            for (int j = i; j > 0; j--) {
            
                if (arr[j] < arr[j - 1]) {
                
                    swap(j, j - 1, arr);
                }
            }
        }
        return arr;
    }

    public int[] swap(int i, int j, int[] arr) {
        
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        
        return arr;
    }

    public void printArray() {
            }
    
     public static void main(String[] args) {
        
        int[] arr = {2, 5, 1, 4, 7, 1, 10};
 
        InsertionSort h = new InsertionSort();
        
        int[] res = h.insertionSort(arr);
        
        for (int i = 0; i < res.length; i++) {
        
            System.out.println(res[i]);
        }        
    }

}