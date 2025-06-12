package com.app.matrices;

import java.util.*;

import com.app.common.CommonUtil;

// LC 253 : https://leetcode.com/problems/meeting-rooms-ii/

public class MeetingRooms2 {

    /* Approach :

        Sort by start time to order the array elements in chronological order.
        Now add first entry into a min-heap to denote a room is occupied.
        Go thro other intervals and see :
            if other meeting starts after current one in heap. Discard and replace.
            else add this meerting to heap
    */        
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
  

    public static void main(String[] args) {

        MeetingRoomsII obj = new MeetingRoomsII();

        int[][] intervals = new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } };

        CommonUtil.runExample("Example 1 : input : [0,30],[5,10],[15,20]", "2", obj.minMeetingRoomsOptimized(intervals) + "");

        int[][] intervals2 = new int[][] { { 7, 10 }, { 2, 4 } };

        CommonUtil.runExample("Example 2 : input : [7,10],[2,4] ", "1", obj.minMeetingRoomsOptimized(intervals2) + "");

        int[][] intervals3 = new int[][] { { 13, 20 }, { 1, 13 } };

        CommonUtil.runExample("Example 3 : input : [13,20],[1,13] ", "1", obj.minMeetingRoomsOptimized(intervals3) + "");

    }

}