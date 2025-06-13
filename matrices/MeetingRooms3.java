public class MeetingRooms3 {

    // https://leetcode.com/problems/meeting-rooms-iii/

    /*
      Approach : 

      Sort by start time to order events chronologically.
      Have a min-heap to store all empty rooms by id. - Satisfies constraint that room with min id is choosen first
      Another min heap to store meeting end time and room currently occupied.

      1. Remove all finished meetings.
      2. Imagine like preparing a schedule, so even if all rooms are occupied, we can remove one from occupied queue that is set to end soon and update   

     */


    public int mostBooked(int n, int[][] meetings) {

        Arrays.sort(meetings, (x,y) -> x[0] - y[0]); // Sort by start times
        
        // Tracks max used room
        int[] count = new int[n];   

        // Room id - minHeap
        PriorityQueue<Integer> available = new PriorityQueue<>();

        // Meeting End time, Room id - minHeap
        PriorityQueue<int[]> occupied = new PriorityQueue<>((x,y)-> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);

        // Fill in available rooms
        for(int i = 0; i < n; i++){
            available.add(i);
        }    
             
     
        for(int[] meeting : meetings){

            int start = meeting[0];
            int end = meeting[1];


             // Remove finished meetings
             while(!occupied.isEmpty() && occupied.peek()[0] <= start){

                int roomId = occupied.remove()[1];

                available.add(roomId);        
                              
             }

             // Try adding current meet into pq

            if(!available.isEmpty()){

                int roomId = available.remove();

                occupied.add(new int[] {end, roomId});

                count[roomId]++;
            
            } else {

                // Unavailable ?? Pre-schedule the meeting - Remove from occupied queue
                int[] occupiedRoom = occupied.remove();

                // Add the current meeting duration to occupied
                occupiedRoom[0] = occupiedRoom[0] + (end - start);

                occupied.offer(new int[] {occupiedRoom[0], occupiedRoom[1]});

                count[occupiedRoom[1]]++;
            }          

             
        }  



        // pick the most occupied room

        int maxKey = -1;
        int maxVal = -1;       

        for(int i = 0; i < count.length; i++){

            if(count[i] > maxVal){

                maxVal = count[i];
                maxKey = i;
            }          
        }
       
        return maxKey;        
    }



}
