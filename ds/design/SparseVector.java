


import java.util.HashMap;
import java.util.Map;


// LC  1570 : https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

class SparseVector {
    
    Map<Integer,Integer> map = new HashMap<>();
    
    SparseVector(int[] nums) {
               
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) map.put(i, nums[i]);
        
        }
    
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {

        int dotProduct = 0;

       // key is non zero index     
       for(Integer key : map.keySet()){

            // if second sparse vector also contains non-zero key
            if(vec.map.containsKey(key)) {

                dotProduct += vec.map.get(key) * map.get(key);
            }

       }
       

       return dotProduct;
                
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);