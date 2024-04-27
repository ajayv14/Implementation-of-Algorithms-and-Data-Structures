package com.app.sorting;

// pivot , Pindex

public class quickSort {
        
    public int[] qSort(int[] arr, int startIndex, int endIndex) {

        if (startIndex <= endIndex) {

            int partitionIndex = partition(startIndex, endIndex, arr);
            
            qSort(arr, startIndex, partitionIndex - 1);
            
            qSort(arr, partitionIndex + 1, endIndex);

        }
        
        return arr;
    }

    // [1, 4, 5, 6,7 ,8 ,9,3,4]
    public int partition(int startIndex, int endIndex, int[] arr) {

        int pivot = arr[endIndex];
        int pIndex = startIndex;
        
        for (int i = startIndex; i <= endIndex - 1; i++) {

            if (arr[i] <= pivot) {
            
                swap(i, pIndex, arr);
                
                pIndex++;
            }
        }
        
        swap(pIndex, endIndex, arr);
        
        return pIndex;
    }


    public int[] swap(int pIndex, int endIndex, int[] arr) {
        int tmp;
        
        tmp = arr[pIndex];
        arr[pIndex] = arr[endIndex];
        arr[endIndex] = tmp;
        
        return arr;
    }


      
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 9, 7, 0};
 
        quickSort q = new quickSort();
        
        int[] res = q.qSort(arr, 0, arr.length - 1);
        
        for (int val : res) {
            System.out.println(val);
        }
      
    }
    
}