//https://leetcode.com/problems/find-all-duplicates-in-an-array/
//credits : https://www.youtube.com/watch?v=aMsSF1Il3IY 

import java.util.List;
import java.util.ArrayList;

/*Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.*/


class AllDuplicates {
   
   /*note: 1 <= a[i] <= n (n = size of array), so each a[i] - 1 is going to be an indes value.
     Loop thro the array and mark the corresponding index value as negative, the repetitive number(index) will then have a -ve value */
   
   public List<Integer> findAllDuplicates(int[] nums){
        
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            
            int index = Math.abs(nums[i]) - 1; // certain values may be already set to -ve in previous run 
                        
            // if number is positive 
            if(nums[index] > 0){
                nums[index] *= -1; // set it to negative               
            } 
            
            else result.add(index + 1); // +1 to compensate previous step in finding index           
        }
        
        return result; 
   }
   
   
   
   
   /*test*/
   public static void main(String[] args){
      
      //int[] nums = {4,3,2,7,8,2,3,1};   //op [2,3]
      int[] nums = {4,4,1,2,5}; //op [4]
      
      AllDuplicates obj = new AllDuplicates();
      List<Integer> res = obj.findAllDuplicates(nums);
      System.out.println("Result: " +res);      
   }
}