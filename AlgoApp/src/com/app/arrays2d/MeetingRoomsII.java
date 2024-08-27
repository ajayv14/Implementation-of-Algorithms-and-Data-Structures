package com.app.arrays2d;

import java.util.*;

import com.app.common.CommonUtil;

// LC 253

public class MeetingRoomsII {

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

        CommonUtil.runExample("Example 1 : input : [0,30],[5,10],[15,20]", "2", obj.minMeetingRooms(intervals) + "");

        int[][] intervals2 = new int[][] { { 7, 10 }, { 2, 4 } };

        CommonUtil.runExample("Example 2 : input : [7,10],[2,4] ", "1", obj.minMeetingRooms(intervals2) + "");

        int[][] intervals3 = new int[][] { { 13, 20 }, { 1, 13 } };

        CommonUtil.runExample("Example 3 : input : [13,20],[1,13] ", "1", obj.minMeetingRooms(intervals3) + "");

    }

}