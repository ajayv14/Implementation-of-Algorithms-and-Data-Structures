package com.app.arrays2d;

import java.util.*;

/** 
  * credits : https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/  
  * logic : sort the intervals wrt start time. Check if end time of i th interval is not clashing with start time of i+1 th interval   
  *
**/

public class MeetingRooms{


   private boolean minimumMeetingRooms(int[][] schedule){
      
     Arrays.sort(schedule, (a,b) -> a[0] - b[0]); 
      
     for(int i = 0; i < schedule.length - 1; i++){
         
         if(schedule[i][1] > schedule[i + 1][0] ) return false; // end time  > start time return false
         
     }             
     return true;
   
   }

   public static void main(String[] args){
   
      int[][] schedule = {{5, 10}, {15, 20}, {0, 20}};   
   
      MeetingRooms obj = new MeetingRooms();
      boolean result = obj.minimumMeetingRooms(schedule);
      System.out.println(result); 
   }

}