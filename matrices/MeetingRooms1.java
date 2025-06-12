package com.app.matrices;

import java.util.Arrays;

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

    public static void main(String[] args){
   
        int[][] schedule = {{5, 10}, {15, 20}, {0, 20}};   
     
        MeetingRoomsI obj = new MeetingRoomsI();
        boolean result = obj.canAttendMeetings(schedule);
        System.out.println(result); 
     }

}
