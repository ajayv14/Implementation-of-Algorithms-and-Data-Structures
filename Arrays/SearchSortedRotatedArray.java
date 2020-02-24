// credits: nick white https://www.youtube.com/watch?v=QdVrY3stDD4
class SearchSortedRotatedArray {
    
    public int search(int[] nums, int target) {
        
        if(nums == null || nums.length <= 0) return -1;
        
        /*find smallest number's index */
        
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right){
            
            int mid = left + (right - left)/2;
            
             if(nums[mid] > nums[right]){
                 left = mid + 1;
             } 
            
            else{
                right = mid;                
            }            
        }  
        
        
        int start = left; // smallest num index stored in start
        left = 0;
        right = nums.length - 1;
        
        while(left <= right){    // boundary
            
            if(target >= nums[start] && target <= nums[right]){
                left = start;   // meaning number is on right side in example [4,5,6,7,0,1,2]            
            }
            else{
                right = start;  // on left side of array in example [4,5,6,7,0,1,2]             
            } 
            
            /*binary search*/
            while(left <= right){
                
                int mid = left + (right - left)/2;
                
                if(target == nums[mid]) return mid;
                
                else if(target < nums[mid]){  // target on left side
                     right = mid -1;
                } 
                
                else {
                    left = mid + 1;
                }
                
            }
            
        }
        
        return -1;
    }
    
    
    
    /*Test*/
    public static void main(String[] args){
      
      int[] nums = {4,5,6,7,0,1,2}; // op = 4a
      SearchSortedRotatedArray obj = new SearchSortedRotatedArray();
      int res = obj.search(nums,0);
      System.out.println(res);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}