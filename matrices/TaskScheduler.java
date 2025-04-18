package com.app.matrices;

import java.util.Arrays;

public class TaskScheduler {
    
    /*logic - 1) find the frequency of occurence and sort 2) calculate the number of idle cpu cycles 3) Try to reduce the idle CPu cycles by executing some other task during the cool down period. If still idle tasks are left, return the count + all tasks, else no idle tasks, so return count */
    
    public int leastInterval(char[] tasks, int n) {
        
        //assuming tasks are all alphabets A - Z
        int[] charMap = new int[26]; 
        
        //  create a frequency map
        for(char c : tasks){            
            charMap[c-'A']++;            
        }
        
        // sort and value at last index will have max freq after sorting       
        Arrays.sort(charMap);
        
        // last index is 25, -1 to deal with last idle cpu cycle is not reqd, eg ABx ABx AB, instead of ABx ABx ABx
        int maxFreq = charMap[25] - 1; 
        
        int idleSlots = maxFreq * n;  
        
        /*try to fill the idle cycles with rest of remaining tasks*/
        for(int i = 24; i>=0; i--){
            idleSlots -= Math.min(charMap[i], maxFreq);            
        }
        
        /*if still idle slots are left, then slots + the cycles for all tasks, else (no idle waste cycles) just num of tasks*/
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;  
        
    }
}