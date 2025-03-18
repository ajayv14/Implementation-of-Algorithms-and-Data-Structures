public class NextPermutationLexi {


        /*
        Example : 1 3 5 4 2
        We have ones, tenth, hundreds and so on based on weights.
        
        1. To get next greater permutation, we need to identify an index from right (ones) to satisfy
        a[i] < a[i + 1] -> 3 < 5 . Why ? -> Replacing smaller number with higher weight with higher number with currently smaller weight.
        But, we also need the next greater permutation, not just any greater permutation, so need
        
        2 To find next greater number than num at index i from the right end that can effectively replace smaller num identified in step 1..
        a[j] > a[i]. -> 4 > 3
        So we swap and get 1 4 5 3 2.
        
        3. We can also observer, after index i, the numbers are in decreasing order -> 5 3 2.
        reverse them and we get 1 4 2 3 5.    

    */

    // LC 31. Next Permutation
    // LC https://leetcode.com/problems/next-permutation/
    public void nextPermutation(int[] nums) {


        // Find a number that satisfies a[i] < a[i + 1] - Basically find a smaller digit that can be  replaced with higher weight
        int i = 0;
        
        for(i = nums.length - 2; i >= 0 ; i--){

            if(nums[i] < nums[i + 1]){
                break;
            }            
        }


         // Find a digit that is greater than at index i, to effectively replace smaller digit with larger digit
        
        if(i >= 0) {

            for(int j = nums.length - 1; j > i; j--){
                    
            if(nums[j] > nums[i]) {
                 
                 swap(i, j,nums);
                 break;
            }               
        }


        }
        


        

        // Swap nums after index i as it is in decreasing order

         int start = i + 1;
         int end = nums.length - 1;

         while(start < end){
             swap(start, end, nums);
             start++;
             end--;
        }
        
    }

    private void swap(int i, int j, int[] nums){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }

     
    public static void main(String[] args) {
        
        
        NextPermutationLexi obj = new NextPermutationLexi();

        int[] nums = {1, 2, 3};

        obj.nextPermutation(nums);

          
        System.out.println("Expected : 1 3 2"); 
        
        for(int i : nums){
            System.out.print(i + " ");
        }
    
        
        
        int[] nums2 = {5, 4, 3, 2, 1};
        obj.nextPermutation(nums2);
        System.out.println();

        
        System.out.println("Expected : 1 2 3 4 5");
        for(int i : nums2){
            System.out.print(i + " ");
        }

    }



}
