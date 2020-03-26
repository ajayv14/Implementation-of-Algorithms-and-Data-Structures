/*credits: https://www.youtube.com/watch?v=3dqR2nYElyw
           https://leetcode.com/problems/minimum-cost-to-connect-sticks/ */

import java.util.PriorityQueue;

class MinimumCostToConnectSticks {

   /*Logic : insert value of sticks into mnin heap, pull out two sticks at a time , combine them..account for the cost and insert back the combined stick entity*/

     public int minimumCostToConnect(int[] nums){
         
         if(nums.length == 0 ) return 0;
         
         int cost = 0;
         
         PriorityQueue<Integer> pq = new PriorityQueue<>();
         
         for(int num : nums){
            pq.add(num);
         }
         
         // continue till there is only one combined stiock left in queue
         while(pq.size() > 1){
         
            //remove two minimum sticks and add the cost
            int sumOfSticks = pq.remove() + pq.remove();
            cost += sumOfSticks;  
            pq.add(sum);            
         }     
         
         return cost;
     
     } 

     public static void main(String[] args){
        // {2,4,3} // 14
        int[] arr = {1,8,3,5}; // expected o/p: 30 
        
        MinimumCostToConnectStciks obj = new MinimumCostToConnectStciks();
        int cost = obj.minimumCostToConnect(arr); 
        System.out.println(cost); 
         
     } 


}