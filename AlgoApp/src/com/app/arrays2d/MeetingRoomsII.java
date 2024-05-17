package com.app.arrays2d;

import java.util.*;

import com.app.common.CommonUtil;

// LC 253

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {

        // Base case
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort by intervals by start time - makes it easier to merger intervals later
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        // minHeap to keep list of meetings that end earliest (by end time)
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));

        pq.offer(intervals[0]); // Add first interval end time to begin with

        for (int i = 1; i < intervals.length; i++) {

            int startTime = intervals[i][0];
            int endTime = intervals[i][1];

            if (!pq.isEmpty() && startTime >= pq.peek()[1]) {

                // No overlap detected
                int[] prev = pq.poll();

                // Update end time of current interval as it is larger.
                prev[1] = endTime;
                pq.offer(prev);

            }

            else {
                // Merge is possible, hence need more rooms
                pq.offer(intervals[i]);
            }

        }

        return pq.size();
    }

    public static void main(String[] args) {

        MeetingRoomsII obj = new MeetingRoomsII();

        int[][] intervals = new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } };

        CommonUtil.runExample("input : [0,30],[5,10],[15,20]", "2", obj.minMeetingRooms(intervals) + "");

        int[][] intervals2 = new int[][] { { 7, 10 }, { 2, 4 } };

        CommonUtil.runExample("input : [7,10],[2,4] ", "1", obj.minMeetingRooms(intervals2) + "");
    }

}