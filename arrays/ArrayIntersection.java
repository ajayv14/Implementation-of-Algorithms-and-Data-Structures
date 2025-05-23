package com.app.arrays;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Arrayintersection {
      public int[] intersection(int[] nums1, int[] nums2) {
          
          /*349. Intersection of Two Arrays - leetcode*/
          /*insert array2 into set, check if values in array1 are present in set, yes- add to result and remove from set*/
                    
          List<Integer> res = new ArrayList<>();
          HashSet<Integer> set = new HashSet<>();
                
          for(int num:nums2){
              set.add(num);
          }        
          
          for(int i=0;i<nums1.length;i++){
              if(set.contains(nums1[i])){
                 res.add(nums1[i]);
                 set.remove(nums1[i]); 
              }
          }
          
         int[] result = new int[res.size()];
          
         for(int i=0;i<res.size();i++){
             result[i] = (int)res.get(i);
         } 
          
      return result;
      }


      
      public static void main(String[] args) {
    Arrayintersection solution = new Arrayintersection();
    
    // Test case 1
    int[] nums1 = {1,2,2,1};
    int[] nums2 = {2,2};
    
    int[] result = solution.intersection(nums1, nums2);
    
    // Print result
    System.out.print("Intersection of arrays: ");
    for(int num : result) {
        System.out.print(num + " ");
    }
}      
  }