public class OneDayMatrixScroll {



}

// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986
//Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
//Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

// Time O(M + N) 
// Space O (min(M,N)) - Due to result array


/*
                   s 0 ---------- e 3
                           st 2-----------en 6            

                Now, intersection is between st 2 and e 3.

                if st 2 < e 3, then merge.

                To calc intersection points : 

                 low -> max(s 0 and st 2) -> st 2
                  high -> min(e 3 and en 6) -> e 3 


                Note: The below isn't possible as input is in sorted order  
                 s            2-----------3   
                     st   1--------------------6

*/


public class IntervalListIntersections {

    // No priority queue needed as : description: 
    //Each list of intervals is pairwise disjoint and in sorted order

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        
        List<int[]> overlaps = new ArrayList<>();

        int i = 0, j = 0;

        while(i < firstList.length && j < secondList.length){

            int[] A =  firstList[i], B =  secondList[j];

            int low = Math.max(A[0], B[0]);
            int high = Math.min(A[1], B[1]);

            if(low <= high){
                overlaps.add(new int[] {low, high}); // Not merging intervals, but just calc intersection region
            }    
            
            // ending sooner or shorter interval
            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]); // Pay attention

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}





// LC 56 : https://leetcode.com/problems/merge-intervals

/*
                   s 0 ---------- e 3
                           st 2-----------en 6            

                Now, intersection is between st 2 and e 3.

                if st 2 < e 3, then merge.

                To calc intersection points : 

                  max(s 0 and st 2) -> st 2
                  min(e 3 and en 6) -> e 3 
            */

   // Time O(n log n) space : O(n)         

public class MergeIntervals {


        // Time : O(n log n)
    // Space : O(n)

    public int[][] mergeOptimized(int[][] intervals) {
        
        
        
    /*logic - sort the intervals with the start time value, then if end index of current >= start index of next, merge*/
              
        
        // list of array to hold result (easy to manipulate)
        List<int[]> res = new ArrayList<>();
        
        //sort based on the interval start time         
        Arrays.sort(intervals, (x,y)-> Integer.compare(x[0], y[0]));
                
        int[] prev = intervals[0]; // first set of value
        res.add(prev);
        
        for(int i = 1; i < intervals.length; i++){
            
            
            int prevEnd = prev[1];
            
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            
            if(prevEnd >= currentStart){   // condition to merge
                
                prev[1] = Math.max(prevEnd, currentEnd);  // end value of merged set
                
                // prev[0] won't change as it is already sorted order 
            }
            
            else {
                
                // Move prev to next and add new entry to temp list*/
                prev = intervals[i];  // set prev value to current
                res.add(prev); // to continue the cycle
            }
            
        }
        
        return res.toArray(new int[res.size()][]);
        
    }
    


    
    /*Solve using Priority Queue*/

    public int[][] merge(int[][] intervals) {

        // To store intermediate resut as a list instead of int[][] array
        List<int[]> res = new ArrayList<>();

        // Sorted order based on start time - As opposed to sorting the array in place
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1,n2)-> n1[0] - n2[0]);


        for(int[] interval : intervals){
            pq.add(interval);           
        }

        while(pq.size() > 1){

            int[] n1 = pq.remove();
            int[] n2 = pq.remove();

            // Merge condition   
            if(n2[0] <= n1[1]){

                int end = n1[1] > n2[1] ? n1[1] : n2[1];                

                pq.add(new int[] {n1[0],end}); // Start is n1[0] as we know it is sorted by start time in PQ
            }

            
            else {                         
                res.add(n1);                
                pq.add(n2);
            }
        }

        if(!pq.isEmpty()){          
                           
            res.add(pq.remove());
        }


        return res.toArray(new int[res.size()][]);
        
        
    }
}





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

}    


// LC 253 : https://leetcode.com/problems/meeting-rooms-ii/

/* Approach :

        Sort by start time to order the array elements in chronological order.
        Now add first entry into a min-heap to denote a room is occupied.
        Go thro other intervals and see :
            if other meeting starts after current one in heap. Discard and replace.
            else add this meerting to heap
    */    
public class MeetingRooms2 {

        
    public int minMeetingRooms(int[][] intervals) {

        if(intervals == null || intervals.length == 0) return 0;
 
        // Sort by start time
        Arrays.sort(intervals, (x,y) -> x[0] - y[0]);      

        // Ascending order, end time  
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[1] - y[1]);
     
        pq.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++){

            int[] interval = intervals[i];

            // Interval starts after the room is available
            if(interval[0] >= pq.peek()[1]) {
           
                pq.remove();           
            }
        
            // Add this new interval
            pq.add(interval);             
        }

        return pq.size();
    }

}   



// https://leetcode.com/problems/meeting-rooms-iii/

    /*
      Approach : 

      Sort by start time to order events chronologically.
      Have a min-heap to store all empty rooms by id. - Satisfies constraint that room with min id is choosen first
      Another min heap to store meeting end time and room currently occupied.

      1. Remove all finished meetings.
      2. Imagine like preparing a schedule, so even if all rooms are occupied, we can remove one from occupied queue that is set to end soon and update   

    */


// Hard
//Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
//Output: 0 -> Oth room is used max number of times.
public class MeetingRooms3 {

    

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





//LC https://leetcode.com/problems/shortest-path-in-binary-matrix
// LC 1091. Shortest Path in Binary Matrix

// Custom implementation

/* Approach - BFS - Guranteed shortest path in undirected graph
            
 DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
*/



class ShortestPathBinaryMatrix {
    
    static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base - start and end cell should contain 0 
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;


        Queue<int[]> q = new LinkedList<>(); // bfs queue
        q.add(new int[]{0,0});

        grid[0][0] = 1; // to calculate distance starting from 1 unit


        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];
           

            //Check if last right exit cell 
            // This check can also be done below the dirs loop, but will fail for input [[0]]
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return grid[row][col];
            } 

            for(int[] dir : dirs){

               int newRow = row + dir[0];
               int newCol = col + dir[1];

                // Why not replace walls 1 with -1 ?? 
                //-> We will never go back to cell 0,0 which is '1' but not a wall, so can skip this modification. 
                
                // Why ignore grid[newRow][newCol] > 0 ??  
                // -> 1 - wall. Any other positive number is already a shorter path in progress

                if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length 
                    || grid[newRow][newCol] > 0){
                       
                        continue;
                }

                q.add(new int[] {newRow,newCol});

                grid[newRow][newCol] = dist + 1; 

                //System.out.println(dist + 1);               
                
            }        
        }   

        return - 1;                          
    }

      
}


// meeting scheduler 
//https://leetcode.com/problems/meeting-scheduler
// time : O(MlogM+NlogN)
// space :  O(n) 
class MeetingScheduler  {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {


        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int p = 0, q = 0;


        while (p < slots1.length && q < slots2.length) {
            
            int[] A = slots1[p];
            int[] B = slots2[q];

            // find the boundaries of the intersection, or the common slot
            int low = Math.max(A[0], B[0]);
            
            int high = Math.min(A[1], B[1]);
            
            if (high - low >= duration) {
                return new ArrayList<Integer>(Arrays.asList(low, low + duration)); // Pay attention
            }
            // always move the one that ends earlier
            if (A[1] < B[1]) {
                p++;
            } else {
                q++;
            }
        }
        return new ArrayList<Integer>();
    }


    // Opt heap basesed soln
    // Time : O((M+N)log(M+N)),
    // Space : O(M+N)
    public List<Integer> minAvailableDurationPQ(int[][] slots1, int[][] slots2, int duration) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }
        for (int[] slot: slots2) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }

        while (pq.size() > 1) {
            
            int[] slot1 = pq.poll();
            int[] slot2 = pq.peek();
            
            if (slot1[1] >= slot2[0] + duration) {
                return new ArrayList<Integer>(Arrays.asList(slot2[0], slot2[0] + duration));
            }
        }
        return new ArrayList<Integer>();
    }

}






// LC 200 
// https://leetcode.com/problems/number-of-islands/description/

public class NumberOfIslands {
    // find the number of chain-islands/ clusters/blobs

    /*
     * Logic : for each cell in m X n matrix, do a recursive call and check all the
     * neighbors - left, right, top, down
     * Where ever there is 1, set it to 0 (sinking the island), and when it is == 0,
     * return 0. When recursive sinking is complete, return 1--denoting the
     * blob/cluster of islands sunk
     */

     int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};


     public int numIslands(char[][] grid) {
         
         int count = 0;
        
         for(int i = 0; i < grid.length; i++){
 
             for(int j = 0; j < grid[0].length; j++){
 
                 // Found an island    
                 if(grid[i][j] == '1'){
 
                    count++;
 
                     // merge & expand
                     dfs(i, j, grid);
                    
                 }      
             }
         }
 
         return count;
     }
 
     // Merge islands and replace the 1s to 0s to stop double counting
     public void dfs(int row, int col, char[][] grid){
         
         // Boundary check + water
         if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0' ){
            return;
         }
 
         grid[row][col] = '0'; // mark it visited
 
         /*dfs(row + dirs[0][0], col + dirs[0][1], grid); // up
 
         dfs(row + dirs[1][0], col + dirs[1][1], grid); // right     
 
         dfs(row + dirs[2][0], col + dirs[2][1], grid); // down     
 
         dfs(row + dirs[3][0], col + dirs[3][1], grid); // left   */
 
         // or 
 
         for(int i = 0; i < dirs.length; i++){
 
             dfs(row + dirs[i][0], col + dirs[i][1], grid);
         }  
 
     }


}    





class ShortestPathBinaryMatrixWithPath {

/* Approach - BFS - Guranteed shortest path in undirected graph
            
 DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
*/
    
    static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };

    // New cell coord : oarent cell coord        
    Map<String,String> path = new HashMap<>();

    String p = null;

    public int shortestPathBinaryMatrix(int[][] grid) {


        //base - start and end cell should contain 0 
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;


        Queue<int[]> q = new LinkedList<>(); // bfs queue
        q.add(new int[]{0,0});

        grid[0][0] = 1; // to calculate distance starting from 1 unit


        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];
           

            

            //Check if last right exit cell 
            // This check can also be done below the dirs loop, but will fail for input [[0]]
            if(row == grid.length - 1 && col == grid[0].length - 1){

                p = (grid.length - 1) +""+ (grid[0].length - 1);

                dfs((grid.length - 1) +""+ (grid[0].length - 1));            
            
                System.out.println("path : " + p); 

                System.out.println(path.get(new int[] {grid.length - 2,grid[0].length - 2}));   


                return grid[row][col];
            } 

            for(int[] dir : dirs){

               int newRow = row + dir[0];
               int newCol = col + dir[1];

                // Why not replace walls 1 with -1 ?? 
                //-> We will never go back to cell 0,0 which is '1' but not a wall, so can skip this modification. 
                
                // Why ignore grid[newRow][newCol] > 0 ??  
                // -> 1 - wall. Any other positive number is already a shorter path in progress

                if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length 
                    || grid[newRow][newCol] > 0){
                       
                        continue;
                }

                q.add(new int[] {newRow,newCol});

                grid[newRow][newCol] = dist + 1;

                path.put(newRow+""+newCol,row+""+col);
                
                //System.out.println(dist + 1);               
                
            }   
           
        }   

        

        return - 1;                          
    }

    private void dfs(String coordinates){

        if(path.containsKey(coordinates)){

           String parentCellCoor = path.get(coordinates);

            p += "," + parentCellCoor ;

            System.out.println(p);

            dfs(parentCellCoor);
        }

        
    }
      
}



/*
 Logic - DFS will not work as more than one orange can be infected and also oranges spread infection at same time.
         BFS with updating time and infected orange for every rotten orange at same time.         
*/


class RottingOranges {

    public int orangesRotting(int[][] grid) { 
       
        // To discover oranges in left, right, top and bottom
        int[][] directions = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};

        int countOfMinutes = 0;
        
        Queue<int[]> q = new LinkedList<>(); // to perform bfs

        // Sometimes a fresh orange may be unreachable, hence maintain count
        int freshOranges = 0; 

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == 1) freshOranges++;

                else if(grid[i][j] == 2){                              
                    q.add(new int[]{i,j});               
                }
            }
        }    

        while(!q.isEmpty() && freshOranges > 0){

            int qSize = q.size();

            // Loop to make sure ALL infected oranges continue to infect other neighbors at each time interval, simultaneously
            for(int i = 0; i < qSize; i++){

                int[] curRotenOrange = q.remove();

                for(int[] dir : directions){
                    int row = curRotenOrange[0] + dir[0];
                    int col = curRotenOrange[1] + dir[1]; 

                    // Boundary check and check for fineOrange
                    if(0 <= row && row < grid.length && 0 <= col && col < grid[0].length ){                

                        if(grid[row][col] == 1 && freshOranges > 0){

                            // Mark as rotten, reduce count and enqueue      
                            freshOranges--;
                            grid[row][col] = 2;
                            q.add(new int[] {row, col});    
                        }                       
                    }
                 }             
            }            
        
            countOfMinutes++;
                
        }

        return (freshOranges > 0) ? -1 : countOfMinutes;          
    }

}