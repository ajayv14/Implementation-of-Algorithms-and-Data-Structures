package com.app.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

// LC : 347 : https://leetcode.com/problems/top-k-frequent-elements

public class TopKFrequentElements {

       
    /*
      Approach - Use quick select - modified quick sort algo    
    */
      
    Map<Integer,Integer> freq;
    
    // Using quick sort pivot approach 
    public int[] topKFrequent(int[] nums, int k) {
                      
        // Store frequencies : <number, freq Of occurence>              
        freq = new HashMap<>();
        
        for(int n : nums) freq.put(n,freq.getOrDefault(n,0) + 1);
       
          
        // Basically each unique number will be in the unique array. Corresponding freq in map
        // We can use freq to partially sort them in place                 
        int[] unique = new int[freq.size()];
        int n = 0;
        for(int key : freq.keySet()){
            unique[n++] = key;
        }
        
        // Perform quick select till we match pivot index with kth largest from right end 
        quickselect(unique,0,unique.length - 1, unique.length - k);
        
        // construct response
        int[] res = new int[k];
        
        for (int i = 0; i < k; i++) {
            res[i] = unique[unique.length - k + i];
        }

        return res;
    }
    
    //Almost 99% similar to quicksort

    // Quickselect pivot logic 
    // Partially sort the array such that
    // left of pivot index, we have values smaller than pivot value (based on freq of occurence in map)
    // right of pivot index, we have values larger than pivot value (based on freq of occurence in map)
    private void quickselect(int[] nums, int left, int right, int topK){

        if(left < right) {

            // Perform partition and update new  pivot position.
            int pivotIndex = partition(nums,left,right);

            // Adjust pivot index appropriately
            if(topK == pivotIndex) return;

            else if(topK < pivotIndex) quickselect(nums,left,pivotIndex - 1, topK);

            else quickselect(nums,pivotIndex + 1, right, topK);     

        }
                 
    }
       
    // Apply quicksort partition logic 
    //  nums = [4 1 4 2 1 3 4 3 5] -> freq map 4:3 1:2 2:1 3:2 5:1    
    private int partition(int[] nums, int left, int right){

        // pivot value    
        int pivot = freq.get(nums[right]);

        int i = left - 1; // A step lagging left
        int j = left; 

        // Rearrange array by comparing with pivot value -  freq of occurence
        for(j = left; j < right; j++ ){

            if(freq.get(nums[j]) < pivot){
                
                i++; // Note !
                 swap(i, j, nums);      
                
            }            
        }    
        
        swap(i + 1,right,nums);  

        return i + 1;
    }

    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }







    /*Priority Queue based soln */

        public int[] topKFrequentPQ(int[] nums, int k) {
       
               
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n,map.getOrDefault(n,0) + 1);
        }
        

        // Optimization - Find k top frequent (larger occurence ??) use min heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->a.getValue() - b.getValue());       
        
        for(Map.Entry<Integer,Integer> e : map.entrySet()){            
            
            if(pq.size() < k) pq.offer(e);

            else {

                if(e.getValue() > pq.peek().getValue()){
                    pq.remove();
                    pq.add(e);
                } 
            } 



        }

        int[] res = new int[k];
        int i = 0;
        while(!pq.isEmpty()){            
            res[i++] = pq.poll().getKey();            
        }

        return res;

        //return result.stream().mapToInt(Integer::intValue).toArray();
        
        /*int[] arr = new int[result.size()];
        
        int i = 0;
        
        for(int g : result){
            arr[i] = g;
            i++;    
        }        
        return arr;*/
    }

}
