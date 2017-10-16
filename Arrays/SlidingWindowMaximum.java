import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
     
        /*k - sliding window size*/
        
        if(nums == null || k <= 0 ) return new int[0];
               
      
        int[] result = new int[nums.length -k+1];  /*nums.length -k + 1 -- size adjusted according to sliding window*/  
        
        int ptr = 0;
        
        
        Deque<Integer> queue = new ArrayDeque<>();
        
             
        for(int i = 0; i < nums.length; i++ ){
               
            /*Remove smaller numbers than current one as the idea is to find max value -  from end of queue */ 
           
           while(!queue.isEmpty() && queue.peekLast() < nums[i]){
               
               queue.pollLast();
           } 
            
            queue.addLast(nums[i]); /*Add current element to the queue*/
            
            
            /*remove elements out of sliding window range- */
            
           if(i > k - 1 && queue.peek() == nums[i - k]){
             
               queue.poll();
           }          
                     
           
           /*add max element to list */
            if(i >= k - 1){
        		result[ptr++] = queue.peekFirst();
        	}         
        
            
            
            
        }
        
        
        
        return result;
        
    }
}