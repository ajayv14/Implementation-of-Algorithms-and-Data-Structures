package com.app.arrays2d;

import java.util.Arrays;

//LC 252
public class MeetingRoomsI {

    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length==0){
            return true;
        }

        Arrays.sort(intervals, (x,y) -> x[0] - y[0] );
        
        int[] newInterval = intervals[0];

        for(int i = 1; i < intervals.length; i++){

            // End time of prev interval greater than start time of current interval -> overlap detected.
            if(newInterval[1] > intervals[i][0]){ return false;}
            else{
                newInterval= intervals[i];
            }
        }        
        return true;        
    }


}
