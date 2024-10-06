// credits : Nick White : https://www.youtube.com/watch?v=qKczfGUrFY4

//https://leetcode.com/problems/merge-intervals/

package com.app.arrays2d;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

public class MergeIntervals {
    
    /*Solve using Priority Queue*/

    public int[][] merge(int[][] intervals) {

        // To store intermediate resut as a list instead of int[][] array
        List<int[]> res = new ArrayList<>();

        // Sorted order based on start time - As opposed to sorting the array in place
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)-> n1.start - n2.start);


        for(int[] interval : intervals){

            pq.add(new Node(interval[0], interval[1]));           
        }

        while(pq.size() > 1){

            Node n1 = pq.remove();
            Node n2 = pq.remove();

            // Merge consition    
            if(n1.end >= n2.start){
                
                n1.end = (n1.end > n2.end)? n1.end: n2.end;
                pq.add(n1);
            }

            
            else {

                int[] r =  new int[] {n1.start, n1.end};               
                res.add(r);                
                pq.add(n2);
            }
        }

        if(!pq.isEmpty()){

            Node n = pq.remove();

            int[] r =  new int[] {n.start, n.end};                    
            res.add(r);
        }


        return res.toArray(new int[res.size()][]);


        /*int[][] result = new int[res.size()][2];

        int idx = 0;

        for(int[] r : res){
            result[idx][0] = r[0];
            result[idx][1] = r[1];
            idx++;        
        }

        return result;
        */
        
    }

    
    class Node {
        int start;
        int end;

        public Node(int s1, int e1){
            start = s1;
            end = e1;
        }
    }

/*
 * 
Time Complexity Analysis
    The time complexity of the provided merge function can be broken down into the following components:
        Adding intervals to the priority queue: O(n log n)
        Removing nodes from the priority queue and merging intervals: O(n log n)
        Converting the result list to an array: O(n)
    Therefore, the overall time complexity is O(n log n) due to the priority queue operations.

    Space Complexity Analysis
    The space complexity of the provided merge function can be broken down into the following components:
        Storing intervals in the priority queue: O(n)
        Storing merged intervals in the result list: O(n)
        Additional space for the array conversion: O(n)
    
    Therefore, the overall space complexity is O(n), where n is the number of intervals.
  * - Meta ai
 */














    public int[][] mergeWithSort(int[][] intervals) {       
        
    /*logic - sort the intervals with the start time value, then if end index of current >= start index of next, merge*/
        
        
        if(intervals.length <= 1) return intervals;
        
        
        // list of array to hold result (easy to manipulate)
        List<int[]> res = new ArrayList<>();
        
        
        /* sort based on the interval start value*/
        
        Arrays.sort(intervals, (arr1,arr2)-> Integer.compare(arr1[0], arr2[0]));
        
        
        int[] prev = intervals[0]; // first set of value
        res.add(prev);
        
        for(int[] interval : intervals){
            
            int prevStart = prev[0];
            int prevEnd = prev[1];
            
            int currentStart = interval[0];
            int currentEnd = interval[1];
            
            if(prevEnd >= currentStart){   // condition to merge
                
                prev[1] = Math.max(prevEnd, currentEnd);  // end value of merged set
                
            }
            
            else {
                
                /*update prev set value*/
                prev = interval;  // set prev value to current
                res.add(prev); // to continue the cycle
            }
            
        }
        
        return res.toArray(new int[res.size()][]);        
    }
    
    
    
    /*test*/
    public static void main(String args[]){
      
      //input
      int[][] intervals = {{1,3},{2,6},{8,10},{15,18}}; // o/p [[1,6],[8,10],[15,18]]
      
      
      MergeIntervals obj = new MergeIntervals();
      int[][] result = obj.merge(intervals);
      
      /*print output*/
      for(int[] res : result){
            System.out.println("["+ res[0] + ", "+ res[1] +"]");            
      }      
      
    }
    
}