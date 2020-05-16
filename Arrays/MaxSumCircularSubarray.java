//credits: https://www.youtube.com/watch?v=kssIQAIg9c8

/**
  eg: [5,-6,-8,5] -- max sum - 5 for non circular array and 5 + 5 for circular array.
  So the circular array combinations wud look like [5,-6,-8,5,5,-6,-8,5].
  
  for [5,-6,-8,5] -- how to spot and remove -6,-8...? 
  
  Total Sum = -4  and min_sub array sum [-6,-8] = -14
  
  We use Total - (min_subarraysum) --> -4 -(-14) = 10 -reqd answer
  
  So to spot [-6,-8], we flip the signs for whole array and then calculate max sub arr sum using kadane's algo.
  
  -->  [-5,6,8,-5] -- Kadane's op--> 14
  
  Initial kadane's op of [5,-6,-8, 5]--> 5
   
  So which ever is greater initial kadane's or the (totalSum + kadane's) after flipping sign. is returned 
  Note - here it is TotalSum + kadane's sign flipped value, due to flipping of sign as in we spot [6,8] instead of [-6,-8]

**/


class MaxSumCircularSubarray {

    public int maxSubarraySumCircular(int[] A) {
               
        int circularSum = 0;
        int initValue = kadanesAlgo(A);
        
        /*get the total sum of array and in the end flip the sign for each values*/
        for(int i = 0; i < A.length; i++){
            
            circularSum += A[i];
            A[i] = -A[i]; // at each step make the digits flip the sign                      
        }
        
        circularSum += kadanesAlgo(A);
        
        if(circularSum > initValue && circularSum != 0) return circularSum;
                
        return initValue;
    }
    
    private int kadanesAlgo(int[] A){
        
        int maxSum = A[0];
        int currentSum = A[0];
        
        for(int k = 1; k < A.length; k++){
            currentSum = Math.max(currentSum + A[k], A[k]);
            maxSum = Math.max(maxSum, currentSum);     
        } 
               
        return maxSum;
    }
}