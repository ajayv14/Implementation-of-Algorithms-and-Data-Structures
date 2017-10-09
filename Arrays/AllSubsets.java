class AllSubsets {
    public List<List<Integer>> subsets(int[] nums) {
        
        /*Final List to be returned*/
        
        List<List<Integer>> MainList = new ArrayList<>();       
        Arrays.sort(nums);
        
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