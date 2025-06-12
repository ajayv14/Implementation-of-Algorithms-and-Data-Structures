public class OneDayMatrixScroll {



}

// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986
//Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
//Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

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

    // No priority queue needed as : description: 
    //Each list of intervals is pairwise disjoint and in sorted order

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
            
            // ending sooner or shorter interval
            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]); // Pay attention

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}





// LC 56 : https://leetcode.com/problems/merge-intervals

/*
                   s 0 ---------- e 3
                           st 2-----------en 6            

                Now, intersection is between st 2 and e 3.

                if st 2 < e 3, then merge.

                To calc intersection points : 

                  max(s 0 and st 2) -> st 2
                  min(e 3 and en 6) -> e 3 
            */

   // Time O(n log n) space : O(n)         

public class MergeIntervals {


        // Time : O(n log n)
    // Space : O(n)

    public int[][] mergeOptimized(int[][] intervals) {
        
        
        
    /*logic - sort the intervals with the start time value, then if end index of current >= start index of next, merge*/
              
        
        // list of array to hold result (easy to manipulate)
        List<int[]> res = new ArrayList<>();
        
        //sort based on the interval start time         
        Arrays.sort(intervals, (x,y)-> Integer.compare(x[0], y[0]));
                
        int[] prev = intervals[0]; // first set of value
        res.add(prev);
        
        for(int i = 1; i < intervals.length; i++){
            
            
            int prevEnd = prev[1];
            
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            
            if(prevEnd >= currentStart){   // condition to merge
                
                prev[1] = Math.max(prevEnd, currentEnd);  // end value of merged set
                
                // prev[0] won't change as it is already sorted order 
            }
            
            else {
                
                // Move prev to next and add new entry to temp list*/
                prev = intervals[i];  // set prev value to current
                res.add(prev); // to continue the cycle
            }
            
        }
        
        return res.toArray(new int[res.size()][]);
        
    }
    


    
    /*Solve using Priority Queue*/

    public int[][] merge(int[][] intervals) {

        // To store intermediate resut as a list instead of int[][] array
        List<int[]> res = new ArrayList<>();

        // Sorted order based on start time - As opposed to sorting the array in place
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1,n2)-> n1[0] - n2[0]);


        for(int[] interval : intervals){
            pq.add(interval);           
        }

        while(pq.size() > 1){

            int[] n1 = pq.remove();
            int[] n2 = pq.remove();

            // Merge condition   
            if(n2[0] <= n1[1]){

                int end = n1[1] > n2[1] ? n1[1] : n2[1];                

                pq.add(new int[] {n1[0],end}); // Start is n1[0] as we know it is sorted by start time in PQ
            }

            
            else {                         
                res.add(n1);                
                pq.add(n2);
            }
        }

        if(!pq.isEmpty()){          
                           
            res.add(pq.remove());
        }


        return res.toArray(new int[res.size()][]);
        
        
    }
}





//LC 252 : https://leetcode.com/problems/meeting-rooms/

// Apply Interval intersection logic

public class MeetingRooms1 {


       public boolean canAttendMeetings(int[][] intervals) {
        
        if(intervals.length == 0 ) return true; 

        // Sort by start time
        Arrays.sort(intervals, (x,y) -> x[0] - y[0]);
        
        int[] prev = intervals[0];

        for(int i = 1; i < intervals.length; i++){

            if(prev[1] > intervals[i][0]) return false;
            
            prev = intervals[i];
        }        
        return true;        
    } 

}    


// LC 253 : https://leetcode.com/problems/meeting-rooms-ii/

/* Approach :

        Sort by start time to order the array elements in chronological order.
        Now add first entry into a min-heap to denote a room is occupied.
        Go thro other intervals and see :
            if other meeting starts after current one in heap. Discard and replace.
            else add this meerting to heap
    */    
public class MeetingRooms2 {

        
    public int minMeetingRooms(int[][] intervals) {

        if(intervals == null || intervals.length == 0) return 0;
 
        // Sort by start time
        Arrays.sort(intervals, (x,y) -> x[0] - y[0]);      

        // Ascending order, end time  
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[1] - y[1]);
     
        pq.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++){

            int[] interval = intervals[i];

            // Interval starts after the room is available
            if(interval[0] >= pq.peek()[1]) {
           
                pq.remove();           
            }
        
            // Add this new interval
            pq.add(interval);             
        }

        return pq.size();
    }

}   