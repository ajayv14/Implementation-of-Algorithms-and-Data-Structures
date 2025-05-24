

//credit 1: https://leetcode.com/problems/subsets
// credit 2: https://leetcode.com/problems/subsets/discuss/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning

import java.util.ArrayList;
import java.util.List;


// LC 78 :  https://leetcode.com/problems/subsets/

// input [1,2,3] 
//expected [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// output [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
class Subsets {
   
    public List<List<Integer>> subsets(int[] nums) {
        
        /*Final List to be returned*/
        
        List<List<Integer>> MainList = new ArrayList<>();       
        //Arrays.sort(nums); not necessary, if order of subsets is not important
        
        /*backtracking helper function*/
        
        backtracking(MainList,nums, new ArrayList<>(),0); /*with start index 0*/
        
        return MainList;
        
    }
    
    
    public void backtracking(List<List<Integer>> MainList, int[] nums, List<Integer> tempList, int start){
        
        MainList.add(new ArrayList<>(tempList)); /*initially add empty list*/
        
        /*run from start to nums.length - 1*/
        for(int i = start;  i < nums.length; i++){
            
            tempList.add(nums[i]);
            backtracking(MainList,nums,tempList,i+1);
            tempList.remove(tempList.size() - 1); /*remove last element from list*/
                     
        }
             
        
        
    }   
    
    
}