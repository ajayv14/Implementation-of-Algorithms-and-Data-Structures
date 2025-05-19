public class MeetingRooms3 {

    // https://leetcode.com/problems/meeting-rooms-iii/

    public int mostBooked(int n, int[][] meetings) {

        Arrays.sort(meetings, (x,y) -> x[0] - y[0]); // Sort by start times
        
        int[] count = new int[n];   

        // Room id
        PriorityQueue<Integer> available = new PriorityQueue<>();

        // Endtime, Room id
        PriorityQueue<int[]> occupied = new PriorityQueue<>((x,y)-> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);

        // Fill in available rooms
        for(int i = 0; i < n; i++){
            available.offer(i);
        }    
             
     
        for(int[] meeting : meetings){

            int start = meeting[0];
            int end = meeting[1];


             // Remove finished meetings
             while(!occupied.isEmpty() && occupied.peek()[0] <= start){

                int[] node = occupied.remove();

                available.add(node[1]);        
                              
             }

             // Try adding current meet into pq

            if(!available.isEmpty()){

                int roomId = available.remove();

                occupied.add(new int[] {end, roomId});

                count[roomId]++;
            
            } else {

                // Unavailable ?? Pre-schedule the meeting
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
       
        return (maxKey == -1 )? -1 : maxKey;        
    }


}
