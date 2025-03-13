package com.app.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

//credits: 1) Kevin Naughton Jr. : https://www.youtube.com/watch?v=1rEUgAG7f_c&list=PLi9RQVmJD2fZgRyOunLyt94uVbJL43pZ_&index=19
//         2) leetcode: https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPoints {
    
    /*Logic : distance to a point from  origin for (a1,a2) = a1*a1 + a2*a2 .  
    How ? -> D = root( (x2 - x1)^2 + (y2 - y1)^2)  -> Here origin P(x2, y2) = (0,0) -> root( (0 - x1) + (0 - x2)^2) -> x1^2 + x2^2 
              Use priority Queue - max heap to add all points, then pick kth point*/
    
    public int[][] kClosest(int[][] points, int K) {
        
        int[][] result = new int[K][2];        
           
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        //PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b)->( Math.sqrt( (b[1] - a[1]) * (b[1] - a[1])  + (b[0] - a[0]) * (b[0] - a[0])  ) ) );
        
        
       PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            
            
            public int compare(int[] a, int[] b){
                
                return ( ( (b[0]*b[0]) + (b[1]*b[1]) ) - ( (a[0]*a[0]) + (a[1]*a[1]) )  );
                
            }            
            
            
        });
        
        
        
        for(int[] point : points){
            pq.add(point);
            
            if(pq.size() > K) pq.remove(); 
        }
        
        for(int i = 0; i < K; i++){
            result[i] = pq.remove();            
        }      
        
        return result;        
    }
}
