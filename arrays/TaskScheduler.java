package com.app.arrays;
import java.util.Arrays;

// https://leetcode.com/problems/task-scheduler/
// LC 621. Task Scheduler
public class TaskScheduler {

     public int leastInterval(char[] tasks, int n) {

        // Task ids are from A - Z
        int[] count = new int[26];

        for(char c : tasks){            
            count[c - 'A']++;
        }

        // Taskl occuring most pushed to right 
        Arrays.sort(count);

       // -1 -> Say 'A' occurs max time with freq = 3, we need 2 idle slots to separate it -> A - idle - A idle - A. 
       int maxFreq = count[25] - 1; 

       int idleSlots = maxFreq * n; // try to fill the idle cycles with rest of remaining tasks

        //count.length - 2 or 24
        for(int i = 24; i >= 0 ; i--){

            idleSlots -= Math.min(count[i], maxFreq); 
        }

    return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;

    }    
}
