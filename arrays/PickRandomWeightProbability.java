package com.app.arrays;
public class PickRandomWeightProbability {




    /*
        input = [1 2 4]
        total sum = 7, so 1 should have 1 out of 7 chance, 2 -> 2/7 and 4 -> 4/7
        probablity of occurenceCan be represented as [1 2 2 4 4 4 4]. 
        Then we generate a random number between 0 and 7, like 5.42
        so return num at index 5 -> 4

        Optimize the array [1 2 2 4 4 4 4]  
        Use prefix sum instead -> [1 3 7] 
        generate a random number between 0 and 7, like 5.42
        Find index where 5.42 < total sum (7) - index 2. Return index 2, corresponmding to 4 in weights array.  

        LC https://leetcode.com/problems/random-pick-with-weight

     */

     // Soln using linear search and prefix sum. Can use binary search to optimize

     int[] prefixSum;

     public PickRandomWeightProbability(int[] w) {
         
         prefixSum = new int[w.length];
 
         prefixSum[0] = w[0];
        
         // cummulative sum
         for(int i = 1; i < w.length; i++){
             
             prefixSum[i] = prefixSum[i - 1] + w[i];
         }
     }
     
     public int pickIndex() {
         
         double target  = Math.random() * prefixSum[ prefixSum.length - 1]; // 0.00 - 1.00 ?
 
         System.out.println(target);
 
         // target range
         for(int i = 0; i < prefixSum.length; i++){
 
             if(target < prefixSum[i]){
                 return i;
             }
         }
 
         return -1;
 
     }


     public int pickIndexOptimized
}
