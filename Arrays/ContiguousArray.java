import java.util.Map;
import java.util.HashMap;
class ContiguousArray {
    public int findMaxLength(int[] nums) {
                
        int maxLength = 0;
        int count = 0;    
        
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, -1); // for [0,1] case, the count is 0, but never seen before, hence we add a dummy entry 
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] == 0){
                count++;
            }
            
            if(nums[i] == 1){
                count--;
            }
            
            /* if the same count is seen again, the num of 1s and 0s are equal btw indices (cur - previous index where same count is seen)*/ 
            if(map.containsKey(count)){
                maxLength = Math.max(maxLength, i - map.get(count));
            }        
            
            else{
                map.put(count, i);
            }
            
        }
        return maxLength;
    }
    
    /*test*/
   public static void main(String[] args){ 
      
      int[] arr = {1,1,0,0,1,0,0,1,1}; // expected :  8
      
      ContiguousArray obj = new ContiguousArray();
      int res = obj.findMaxLength(arr);
      System.out.println(res);
      
   }
}

   