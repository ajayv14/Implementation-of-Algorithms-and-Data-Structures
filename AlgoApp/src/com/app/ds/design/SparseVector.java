package com.app.ds.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// LC  1570 : https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

// Optimized version.
class SparseVector {
    
    List<int[]> vectors;
    
    SparseVector(int[] nums) {
        vectors = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) vectors.add(new int[] {i, nums[i]});
        
        }
    
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        
       // two pointers
       int i = 0, j = 0, product = 0;

       while(i < vectors.size() && j < vec.vectors.size()){

            int[] vectors1 = vectors.get(i);
            int[] vectors2 = vec.vectors.get(j);


            // if index is the same
            if(vectors1[0] == vectors2[0]){

                product += vectors1[1] * vectors2[1];

                i++;
                j++;
            }

            else if (vectors1[0] < vectors2[0]){
                i++;
            }

            else j++;
       }

       return product;
                
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);



// Nopn optimized - Can lead to collision and other issues with hashmap when a large dataset is used.

class SparseVector2 {
    
    private Map<Integer, Integer> map;
    
    SparseVector2(int[] nums) {
        
        map = new HashMap<>();       
        
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) map.put(i, nums[i]);
            
        }
        
        
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector2 vec) {
        
        int dotProduct = 0;
        
        for(int key : map.keySet()){
            
            dotProduct += map.get(key) * vec.map.getOrDefault(key,0);            
            
        }
        
        
        return dotProduct;
    }
}

