package AlgoApp.bin.com.app.heap;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    // LC https://leetcode.com/problems/k-closest-points-to-origin/description/
    // 973. K Closest Points to Origin

    public int[][] kClosest(int[][] points, int K) {
        
        
        int[][] result = new int[K][2];
                
        //PriorityQueue<Point> pq = new PriorityQueue<>((p,q)-> eucledeanDist(p) - eucledeanDist(q));

        // Optimize by using max heap - Insert as many points as K, then at each iteration remove the top most (max value) and insert a new point. In the end we will have 2 points with min euclid dist to origin
        PriorityQueue<int[]> pq = new PriorityQueue<>((p,q)-> q[0] - p[0]); // p[0] -> eucl dist, p[1] -> point index
       

        for(int i = 0; i < points.length; i++){

            int[] entry =  { eucledeanDist(points[i]), i};


            if(pq.size() < K){

                pq.add(entry);

            }

            else if(pq.peek()[0] > entry[0] ){

                 pq.remove();            
                 pq.add(entry); 
            }
                      
        }
        
        
        int i = 0;
        
        while(K > 0){
            
            int[] entry = pq.poll();
            
            result[i] = points[entry[1]];
            K--;
            i++;
        }
        
        return result;
    }
    
    // eucledean distance from origin (0,0). Can return squaredDist for comparison
    private int eucledeanDist(int[] a){
        // Root( (x1 - x2) ^ 2 + (y1 - y2)^) = Root( (0 - x2) ^ 2 + (0 - y2) ^ 2)
        // = Root(x2 ^ 2 - Y2 ^ 2)
        return (a[0] * a[0]) + (a[1] * a[1]); 
    }    

}
