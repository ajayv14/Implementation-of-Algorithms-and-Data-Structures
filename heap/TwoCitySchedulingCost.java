
package com.app.heap;
import java.util.PriorityQueue;

/**
  * credits : https://www.youtube.com/watch?v=vtNoP43hGJA
  * leetcode : https://leetcode.com/problems/two-city-scheduling/
  * logic : We sort the cost array based on max profit when guests travel to city A.
**/

class TwoCitySchedulingCost {

        /*
        Can sort array based on cost or use PQ to maintain sorted order.
        Compute cost of first half elements to city A + cost of second half of elements to city B.         
    
     */


     public int twoCitySchedCost(int[][] costs) {

        int minCost = 0;

        // Get sorted order based on efficient cost - Pick city A or B based on cost.
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> x.price - y.price);

        int idx = 0;

        for(int[] cost : costs){            
            pq.add(new Node(idx,(cost[0] - cost[1])));
            idx++;
        }    
   
        /*while(!pq.isEmpty() && pq.size() > idx/2){
            minCost += costs[pq.remove().index][0]; 
        }

        while(!pq.isEmpty()){
            minCost += costs[pq.remove().index][1];    
        }*/
    
        int len = idx/2;
        
        
        for(int i = 0; i < len; i++){
            minCost += costs[pq.remove().index][0];    
        }

        // Do not use i < pq.size() as size is dynamic 
        for(int i = len; i < idx; i++){
            minCost += costs[pq.remove().index][1];
        }

        return minCost;

    }


    class Node {

        int index;
        int price;

        public Node(int idx, int price){
            this.index = idx;
            this.price = price;
        }
    }
       
    
}