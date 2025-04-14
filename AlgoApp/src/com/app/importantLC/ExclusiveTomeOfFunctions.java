

import java.util.List;
import java.util.Stack;


// LC : 636 : https://leetcode.com/problems/exclusive-time-of-functions/

public class ExclusiveTomeOfFunctions {


    // optimized
/*
        Use a stack to keeop track of start - end iof a func.
        Timer to keep track of time
    */
    // Time O(n), space O(n/2) - only start of func is stored
    public int[] exclusiveTime(int n, List<String> logs) {

        // n - num of unique functions
        int[] res = new int[n];    

        Stack<Integer> stack = new Stack<>();

        int timer = 0;

        // pre-populate stack
        String[] logPrev = logs.get(0).split(":");
        
        stack.push(Integer.parseInt(logPrev[0])); //id
        timer = Integer.parseInt(logPrev[2]);

        for(int i = 1; i < logs.size(); i++){
                        
            String[] log = logs.get(i).split(":");

            // Process log
            int id = Integer.parseInt(log[0]);
            String action = log[1];
            int timeUnits = Integer.parseInt(log[2]);
            
            // optimization
            if(action.equals("start")){

                // Update time cosumed by previpus func                              
                if(!stack.isEmpty()) res[stack.peek()] += timeUnits - timer;
                                
                timer =  timeUnits; 

                stack.push(id);          
            } 
            
            // action is "end" 
            else {
                
                // Update time consumed by current func that ended
                // func runs till end time, so add + 1               
                res[stack.pop()] += timeUnits - timer + 1;
                
                timer = timeUnits + 1; // Update previous
            }      
           
        }        
        
        return res;
    }




    // Non optimized
    public int[] exclusiveTime2(int n, List<String> logs) {

        // n - num of unique functions
        int[] res = new int[n];    

        Stack<Integer> stack = new Stack<>();

        int time = 0;

        // pre-populate stack
        String[] logPrev = logs.get(0).split(":");
        stack.push(Integer.parseInt(logPrev[0])); //id
        time = Integer.parseInt(logPrev[2]);

        for(int i = 1; i < logs.size(); i++){
                        
            String[] log = logs.get(i).split(":");
         
            int id = Integer.parseInt(log[0]);
            String action = log[1];
            int timeUnits = Integer.parseInt(log[2]);
            
            // Run the timer
            while(time < timeUnits){
                res[stack.peek()]++;
                time++;
            }

            if(action.equals("start")) stack.push(id);
            
            // action is "end" 
            else {
                // func runs till end time, so 
                res[stack.pop()]++; 
                time++;
            }      
           
        }        
        
        return res;
    }
}
