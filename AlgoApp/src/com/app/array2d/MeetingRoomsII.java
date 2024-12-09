package com.app.array2d;

import java.util.*;

import com.app.common.CommonUtil;

// LC 253

public class MeetingRoomsII {

    /**
     * 
     * We sort time intervals by start time.
     * 
     * Insert end time of first time interval. Loop thro rest of intervals.
     * 
     * Compare current interval start time with end time of interval in top of priority queue.
     * 
     * If they overlap, we need a new meeting room. 
     * 
     * Else, remove interval from pq and insert new time interval. 
     * 
     */
    public int minMeetingRoomsOptimized(int[][] intervals) {

        // Base case
        if (intervals == null || intervals.length == 0)  return 0;

        // Sort by intervals by start time - makes it easier to merger intervals later
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(intervals[0][1]); // Add first interval end time to begin with

        for (int i = 1; i < intervals.length; i++) {

            int[] interval  = intervals[i];
                      
            if (interval[0] >= pq.peek()) {
                // No additional room required.
                pq.poll(); 
            }
           
            // Update pq with new end time 
            pq.offer(interval[1]);            
        }

        return pq.size();
    }

    public int minMeetingRooms(int[][] intervals) {

        // Base case
        if (intervals == null || intervals.length == 0)  return 0;

        // Sort by intervals by start time - makes it easier to merger intervals later
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));

        pq.offer(intervals[0]); // Add first interval to begin with

        for (int i = 1; i < intervals.length; i++) {

            int[] interval = intervals[i];
                      
            if (interval[0] >= pq.peek()[1]) {
                // No additional room required.
                pq.poll(); 
            }
           
            // Update pq with new time interval
            pq.offer(intervals[i]);            
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