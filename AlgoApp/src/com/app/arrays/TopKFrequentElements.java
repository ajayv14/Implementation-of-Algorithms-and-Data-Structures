package com.app.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// LC : 347 : https://leetcode.com/problems/top-k-frequent-elements

public class TopKFrequentElements {
     // credits : LC Editorial

    Map<Integer,Integer> freq;
    int[] unique;

    // Using quick sort pivot approach 
    public int[] topKFrequent(int[] nums, int k) {
                      
        // Store frequencies              
        // <Number, Freq Of Ocurence>              
        freq = new HashMap<>();
        
        for(int n : nums){
            freq.put(n,freq.getOrDefault(n,0) + 1);
        }
          
                         
        // Create an array of unique elements to perform quickselect
        unique = new int[freq.size()];
        int n = 0;
        for(int key : freq.keySet()){
            unique[n++] = key;
        }
        

        // Perform quick select till we match pivot index with kth smallest
        quickselect(0,unique.length - 1, unique.length - k);
        
        return Arrays.copyOfRange(unique,unique.length - k, unique.length);
    }

    // Quickselect pivot logic 
    // Partially sort a list from left to right. till k elemets are sorted
    private void quickselect(int left, int right, int kSmallest){

        if(left == right) return;

        //Random pivot btw left and right
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left);

        // Perform partition and update new  pivot position.
        pivotIndex = partition(left,right,pivotIndex);

        // Adjust pivot index appropriately
        if(kSmallest == pivotIndex) return;

        else if(kSmallest < pivotIndex) quickselect(left,pivotIndex - 1, kSmallest);

        else quickselect(pivotIndex + 1, right, kSmallest);        
    }

    // partition logic -
    /*

     */
     // Optimization - Chose random pivot and partition
     //  nums = [4 1 4 2 1 3 4 3 5] -> freq map 4:3 1:2 2:1 3:2 5:1    
    private int partition(int left, int right, int pivotIndex){

        int pivotFreq = freq.get(unique[pivotIndex]);

        // Move pivot index to end
        swap(pivotIndex, right, unique);

        int ptr = left; 


        // Rearrange array lookinf at pivot freq for reference
        for(int i = left; i <= right; i++ ){

            if(freq.get(unique[i]) < pivotFreq){
                swap(ptr,i,unique);
                ptr++;
            }            
        }    

        // post re-arranging , swap pivot index with right
        swap(ptr,right,unique);  

        return ptr;
    }

    private void swap(int i, int j, int[] unique){
        int temp = unique[i];
        unique[i] = unique[j];
        unique[j] = temp;
    }


}
