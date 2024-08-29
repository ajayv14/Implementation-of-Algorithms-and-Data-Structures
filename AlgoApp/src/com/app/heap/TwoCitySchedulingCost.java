import java.util.PriorityQueue;

/**
  * credits : https://www.youtube.com/watch?v=vtNoP43hGJA
  * leetcode : https://leetcode.com/problems/two-city-scheduling/
  * logic : We sort the cost array based on max profit when guests travel to city A.
**/

class TwoCitySchedulingCost {
       
    public int twoCitySchedCost(int[][] costs) {
        
        int total = 0;
        
        //pq based on profit b - a
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.profit - b.profit);
        int idx = 0;
        for(int[] cost  : costs){            
            Node node = new Node(idx,(cost[0] - cost[1]));        
            pq.offer(node);
            idx++;
        }        
        
        while(!pq.isEmpty() && pq.size() > idx/2){
            Node node = pq.poll();
            total += costs[node.index][0]; // city A
        }
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            total += costs[node.index][1]; // city B
        }
        
        return total;
    }
    
    class Node {
        int index;
        int profit;
        
        public Node(int index, int profit){
            this.index = index;
            this.profit = profit;
        }
    }
    
}