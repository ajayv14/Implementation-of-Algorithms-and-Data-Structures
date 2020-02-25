class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        
        int sum  = 0;
        int result = 0;
        
        for(int i = 0; i < nums.length; i++){
            
            sum += nums[i];
            
            if(map.containsKey(sum - k )) { // subarray is found
                result += map.get(sum - k);                
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1); // if not present in hashmap, will get 0        
        }        
        return result;
    }
    
    
    
    /*test*/
    
    
}