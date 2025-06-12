package com.app.matrices;

import java.util.ArrayList;
import java.util.List;

// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986


// Time O(M + N) 
// Space O (min(M,N)) - Due to result array


/*
                   s 0 ---------- e 3
                           st 2-----------en 6            

                Now, intersection is between st 2 and e 3.

                if st 2 < e 3, then merge.

                To calc intersection points : 

                 low -> max(s 0 and st 2) -> st 2
                  high -> min(e 3 and en 6) -> e 3 


                Note: The below isn't possible as input is in sorted order  
                 s            2-----------3   
                     st   1--------------------6

*/


public class IntervalListIntersections {

    // No priority queue needed as : description: Each list of intervals is pairwise disjoint and in sorted order

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        
        List<int[]> overlaps = new ArrayList<>();

        int i = 0, j = 0;

        while(i < firstList.length && j < secondList.length){

            int[] A =  firstList[i], B =  secondList[j];

            int low = Math.max(A[0], B[0]);
            int high = Math.min(A[1], B[1]);

            if(low <= high){
                overlaps.add(new int[] {low, high}); // Not merging intervals, but just calc intersection region
            }    

            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]);

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}
