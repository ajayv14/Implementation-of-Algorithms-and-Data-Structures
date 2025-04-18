package com.app.ds.design;

import java.util.PriorityQueue;


// LC : https://leetcode.com/problems/process-tasks-using-servers/
// 1882. Process Tasks Using Servers
public class ProcessTasksInServers {

    public int[] assignTasks(int[] servers, int[] tasks) {

        // Build a queue for servers that are available
        // int[] - weight (NOT server capacity),server index
        PriorityQueue<int[]> available = 
            new PriorityQueue<>((p, q) -> (p[0] == q[0]) ? 
            p[1] - q[1] :  p[0] - q[0]);

        // PQ for unavailable servers - time to become available, weight, index
        PriorityQueue<int[]> unavailable = new PriorityQueue<>((p,q) -> p[0] - q[0]);
            //(p[1] == q[1] ? p[2] - q[2] : p[1] - q[1]) : 
            //p[0] - q[0]);
        
        // Result
        int[] ans = new int[tasks.length];

        // Populate queue
        for(int s = 0; s < servers.length; s++){
            available.add(new int[] {servers[s], s});
        }     

        int time = 0;        
        int task = 0;

        // for each task
        while(task < tasks.length){
            
           // System.out.print(task);
            
            // Check if any unavailable server is available
            while(!unavailable.isEmpty() &&  unavailable.peek()[0] <= time){

                //int[] server =  unavailable.peek();
               
                //if(server[0] <= time) {

                    int[] server = unavailable.remove();
                    available.add(new int[] {server[1], server[2]}); // only weight and index;
                //}                                     
            }


          
            // Focus on task assignment
            // Here task <= time is important as we cannot pre-emtively insert tasks ahead of time.
            while(!available.isEmpty() && task <= time && task < tasks.length) {
                      
                int[] server = available.remove();       
                ans[task] = server[1];
                unavailable.add(new int[] {time + tasks[task], server[0], server[1]});                 
                task++;
            }  


            // Fast-forward time if no available servers
            if (available.isEmpty()) {
                time = unavailable.peek()[0]; // Move to the next available server time
            }

            else    time++;  

                        
        }

        return ans;
    }

}
