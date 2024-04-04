// credits : Nick White : https://www.youtube.com/watch?v=qKczfGUrFY4

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class MergeIntervals {
    
    public int[][] merge(int[][] intervals) {       
        
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