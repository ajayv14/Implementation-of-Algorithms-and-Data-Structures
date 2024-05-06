

//edge case

// input [4,4,4,1,4]

// expected [[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // necessary when duplicates are present
        backtracking(res,new ArrayList<Integer>(),nums,0);        
        return res;        
    }
    
    private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int start){
        
        res.add(new ArrayList<>(temp));
        
        for(int i = start; i < nums.length; i++){
                       
           if(i > start && nums[i] == nums[i - 1]) continue;
 
           /* we have duplicates, so [1,2,2] will have multiple duplicate subsets like 
                [[],[1],[1,2],[1,2,2],[1,2],[2],[2,2],[2]]
                
                to avoid it, we check if nums[i] == nums[i -1] and continue if so.
                
                at index i = 0, i - 1 = -1 will throw arrayOut of bounds exception.
                
                i > start prevents index out of bounds exception, continue - breaks and moves to next iteration of loop*/
           
            
          
            temp.add(nums[i]);
            backtracking(res,temp,nums, i + 1);
            temp.remove(temp.size() - 1); 
        }        
    }
}