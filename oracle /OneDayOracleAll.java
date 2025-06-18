public class OneDayAll {

}

/*Logic : cache - fixed size queue and recently used items are in front and least used are are at end of list. So we maintain a Doubly Linked List.
      Operations: get(key)--> value in O(1). So we maintain a hashmap and when the key is accessed, then we re insert the Node(key,value) back into the front of queue.
                  put(key,value) --> we create a Node and insert into front of D. linked list(Deque), then make an entry/modify entry in hashmap. 

 */



class LRUCache {


   Map<Integer,DLLNode> map;

    DLLNode head = null;
    DLLNode tail = null; 
    int capacity;

    public LRUCache(int capacity) {        
        map = new HashMap<>(capacity);   
        this.capacity = capacity;    
    }
            
    public void put(int key, int value) {

        if(map.containsKey(key)){

            DLLNode node = map.get(key);

            findAndRemove(node);            
            
            // Update new value
            DLLNode newNode = new DLLNode(key, value);
            addFront(newNode);
            map.put(key,newNode);

        }

        else {

            DLLNode node = new DLLNode(key,value);

            if(map.size() >= capacity){

                DLLNode removed = removeLast();
                map.remove(removed.key);
            }                  

            addFront(node);   
            map.put(key, node);                     
        }       

    }


    public int get(int key) {

        if(map.containsKey(key)){
                
            DLLNode node = map.get(key);          
            int val = node.value;
            findAndRemove(node);           
            addFront(node);
            return val;
        }

        return -1;
        
    }

    public void addFront(DLLNode node){

        if(head == null){
            head = tail = node;                              
        } 

        else {
            node.next = head;
            head.prev = node;
            head = node;            
        }                   
    }

    public DLLNode removeLast(){

        if(tail == null) return null;

        else if(head == tail){
            DLLNode d = head;
            head = tail = null;
            return d;
        }
        else {
            DLLNode d = tail;    
            tail = tail.prev;
            tail.next = null;
            return d;
        }            
             
    }


    public void findAndRemove(DLLNode node) {
               
        if (head == null) return;
        
        else if(head == tail){
            head = tail = null;
        }
    
         // Check if the node to be removed is the head
        else if (head == node) {
          
            head = head.next;
            head.prev = null;           
        }

        // Check if the node to be removed is the tail
        else if (tail == node){
            tail = tail.prev;
            tail.next = null;            
        }

        // Somewhere in middle
        else {

            node.prev.next = node.next;
            node.next.prev = node.prev;          

        }
    
    
}




    class DLLNode {
        int key;
        int value;
        DLLNode next;
        DLLNode prev;

        public DLLNode(int key, int value){
            this.key = key;
            this.value = value;            
        }   }

   
}


class LongestSubstringNonRepeating {


    // LC 3 : https://leetcode.com/problems/longest-substring-without-repeating-characters/  
    
        
        
    // "abcabcbb" , expected : 3
    //  "dvdf" , 3
    // "bbbbb", 1


    /**
     Approach :
        Brute force - Check all possible substrings
     */

    public int lengthOfLongestSubstringNonOptimized(String s) {
       
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){

            Set<Character> uniqueCharacters = new HashSet<>();

            int j = 0;

            for(j = i; j < s.length(); j++){

                char c = s.charAt(j);

                if(uniqueCharacters.contains(c)) break;

                uniqueCharacters.add(c);
            }      

            maxLen = Math.max(maxLen, j - i);
        }           
        
        return maxLen;
    }


    /**
     Approach :

     Update count in map and keep expaning sliding window to right.
     We may hit a duplicate char, sometimes even adjacent ones like bb in acbbk
     To remove duplicate char, keep on removing chars from left until count of duplicate reaches 1.
     
     edge cases : "bbbbb" , "pwwkew : 3 -> wke"
     */

     public int lengthOfLongestSubstring(String s) {
        
        // frequency map : <character, count>
        Map<Character,Integer> map = new HashMap<>();

        int maxLen = 0;

        int left = 0;

        for(int right = 0; right < s.length(); right ++){

            char c = s.charAt(right);

            map.put(c, map.getOrDefault(c, 0) + 1);      

            // why not "if" ?? -> "pwwkew" : pww -> ww is incorrect. 

            // keep on removing char from left
            while(map.get(c) > 1){

                // Remove leftmost char
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);
                left++;        
            }

            // max btw max and cure window len
            maxLen = Math.max(maxLen, right - left + 1);
        }           
        
        return maxLen;
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

    
   

    

    public static void main(String[] args) {

        char[][] arr = { { '1', '0', '0', '0' }, // op - 2
                { '1', '0', '1', '1' },
                { '1', '0', '1', '1' },
                { '0', '0', '0', '0' } };

        NumberOfIslands obj = new NumberOfIslands();

        System.out.println(obj.numIslands(arr));

    }
}


// LC : 694 : https://leetcode.com/problems/number-of-distinct-islands



// Time : O(M * N)
// Space : O(M * N) 
public class numDistinctIslands {


    // Another approach using path as hash. 

    // Directions - Up, Right, Down, Left
    int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

   
    StringBuilder sb; // Build hash using distance from cur origin

    Set<String> islands = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {

        int distinct = 0;
        
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                // new island    
                if(grid[i][j] == 1){

                    sb = new StringBuilder();
                                 
                    dfs(grid, i, j,'R');

                    String hash = sb.toString();  

                    //System.out.println(hash); 

                    if(!islands.contains(hash)) {

                        islands.add(hash);
                        distinct++;
                    } 
                
                }        
            }
        }
        return distinct;        
    }

    private void dfs(int[][] grid, int row, int col, char pathChar){

        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0){
            return;
        }

        grid[row][col] = 0; // mark visited

        // Tp compute hash for each cell in island
        sb.append(pathChar);    

           
        dfs(grid, dirs[0][0] + row, dirs[0][1] + col, 'U');
        dfs(grid, dirs[1][0] + row, dirs[1][1] + col, 'D');
        dfs(grid, dirs[2][0] + row, dirs[2][1] + col, 'R');
        dfs(grid, dirs[3][0] + row, dirs[3][1] + col, 'L');

        //Keep account of when we backtracked
        sb.append('B');           
    }


}


public class NumberOfDistinctIslandsNonOptmized {

  

    // Directions - Up, Right, Down, Left
    int[][] dirs = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // Each island has an origin, used to calculate distance from all cells
    int curOriginRow;
    int curOriginCol;

    StringBuilder sb; // Build hash using distance from cur origin

    Set<String> islands = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {

        int distinct = 0;
        
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                // new island    
                if(grid[i][j] == 1){

                    sb = new StringBuilder();

                    curOriginRow = i;
                    curOriginCol = j;                    
                    dfs(grid, i, j);

                    String hash = sb.toString();  

                    //System.out.println(hash); 

                    if(!islands.contains(hash)) {

                        islands.add(hash);
                        distinct++;
                    } 
                
                }        
            }
        }
        return distinct;        
    }

    private void dfs(int[][] grid, int row, int col){

        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0){
            return;
        }

        grid[row][col] = 0; // mark visited

        // compute hash for each cell in island
        sb.append(String.valueOf(curOriginRow - row) + String.valueOf(curOriginCol - col));

        for(int[] dir : dirs){

            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
           
            dfs(grid, newRow, newCol);
        }
    }


}



public class IslandPerimeter {


    /**
     Approach : For a cell, each side surrounded by water adds one to perimeter. S0 for each cell compute 
      (max perimeter - number of sides facing land) -> 4 - number of sides facing land.
      
      Basically look at the walls : 
      Cells in first row ? Up is 0,  grid[i - 1][j] otherwise 
      Cells in first col ? left is 0,  grid[i][j - 1] otherwise
      Cells in last row ? down - bottom is 0, grid[i + 1][j] otherwise
      Cells in last column ? right = 0,   grid[i][j + 1] otherwise      
     */

    // Non-optimized 
    public int islandPerimeter(int[][] grid) {

        int perimeter = 0;

        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == 1){

                    // Up is zero for first row cells
                    int up = (i == 0) ? 0 : grid[i - 1][j];

                    // left is zero for first column cells    
                    int left = (j == 0) ? 0 :  grid[i][j - 1];

                    // Down is zero for last row cells
                    int down = (i == grid.length - 1) ? 0 : grid[i + 1][j];

                    // right is zero for last column cells
                    int right = (j == grid[0].length - 1) ? 0 : grid[i][j + 1];

                    perimeter += 4 - (up + left + down + right);

                }
            }
        }
        
        return perimeter;
    }
}


// LC 1254 : https://leetcode.com/problems/number-of-closed-islands/

public class NumberOfClosedIslands {


    public int closedIsland(int[][] grid) {

        int count = 0;    

        int[][] dirs = new int[][] { {-1,0}, {0, 1}, {1, 0}, {0,-1}};

        // Don't have to start from i - 0, j = 0 as island in edge is to be ignored.
        for(int i = 1; i < grid.length; i++){

            for(int j = 1; j < grid[0].length; j++){

                if(grid[i][j] == 0 && dfs(grid, i, j, dirs)) {

                    count++;
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int row, int col, int[][] dirs){

        // Out of boundary    
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return false;
        }   

        else if(grid[row][col] == 1) return true; // water or revisited island 0


        // else the cell contains 0. so check all 4 sides to detect if cell is closed/not closed

        grid[row][col] = 1; // To prevent revisiting same cell with 0;

        boolean isIslandClosed = true;

        for(int[] dir : dirs){

            int newRow = row + dir[0];
            int newCol = col + dir[1];
        
            if(!dfs(grid, newRow, newCol, dirs)) isIslandClosed = false;
        }

        return isIslandClosed;
                    
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


    public static void main(String[] args){

        RottenOranges obj = new RottenOranges();
    
        int[][] grid1 = new int[][]{{2,1,1},{1,1,0},{0,1,1}}; //  expected res = 4
        int[][] grid2 = new int[][]{{2,1,1},{0,1,1},{1,0,1}}; //  expected res = -1
    
        System.out.println("Expected res : " + obj.orangesRotting(grid1));
        System.out.println("Expected res : " + obj.orangesRotting(grid2));
    
    }



}




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

    /*int[][] result = new int[res.size()][2];

        int idx = 0;

        for(int[] r : res){
            result[idx][0] = r[0];
            result[idx][1] = r[1];
            idx++;        
        }

        return result;
        */

/*
 * 
Time Complexity Analysis
    The time complexity of the provided merge function can be broken down into the following components:
        Adding intervals to the priority queue: O(n log n)
        Removing nodes from the priority queue and merging intervals: O(n log n)
        Converting the result list to an array: O(n)
    Therefore, the overall time complexity is O(n log n) due to the priority queue operations.

    Space Complexity Analysis
    The space complexity of the provided merge function can be broken down into the following components:
        Storing intervals in the priority queue: O(n)
        Storing merged intervals in the result list: O(n)
        Additional space for the array conversion: O(n)
    
    Therefore, the overall space complexity is O(n), where n is the number of intervals.
  * - Meta ai
 */




    
    
    /*test*/
    public static void main(String args[]){
      
      //input
      int[][] intervals = {{1,3},{2,6},{8,10},{15,18}}; // o/p [[1,6],[8,10],[15,18]]
      
      
      MergeIntervals obj = new MergeIntervals();
      int[][] result = obj.merge(intervals);
      
      /*print output*/
      for(int[] res : result){
            System.out.println("["+ res[0] + ", "+ res[1] +"]");            
      }      
      
    }
    
}


// LC 253 : https://leetcode.com/problems/meeting-rooms-ii/

public class MeetingRooms2 {

    /* Approach :

        Sort by start time to order the array elements in chronological order.
        Now add first entry into a min-heap to denote a room is occupied.
        Go thro other intervals and see :
            if other meeting starts after current one in heap. Discard and replace.
            else add this meerting to heap
    */        
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
  

    public static void main(String[] args) {

        MeetingRoomsII obj = new MeetingRoomsII();

        int[][] intervals = new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } };

        CommonUtil.runExample("Example 1 : input : [0,30],[5,10],[15,20]", "2", obj.minMeetingRoomsOptimized(intervals) + "");

        int[][] intervals2 = new int[][] { { 7, 10 }, { 2, 4 } };

        CommonUtil.runExample("Example 2 : input : [7,10],[2,4] ", "1", obj.minMeetingRoomsOptimized(intervals2) + "");

        int[][] intervals3 = new int[][] { { 13, 20 }, { 1, 13 } };

        CommonUtil.runExample("Example 3 : input : [13,20],[1,13] ", "1", obj.minMeetingRoomsOptimized(intervals3) + "");

    }

}


// LC 207   
// Toposort - // Kahn's algo'
public class CourseSchedule {

    // Kahn's algo'
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        int[] inDegree = new int[numCourses]; // Incoming edges

        // Create graph
        for (int[] prereq : prerequisites) {

            List<Integer> list = graph.getOrDefault(prereq[1], new ArrayList<>()); // input[2 3] Incoming edge is coming
                                                                                   // from 3 -> 2.
            list.add(prereq[0]);
            graph.put(prereq[1], list);
        }

        // Increment incoming edges on each node listed in pre-reqs
        for (int[] prereq : prerequisites) {
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Pick the nodes with no incoming edges
        for (int i = 0; i < inDegree.length; i++) {

            if (inDegree[i] == 0)
                queue.offer(i);

        }

        int count = 0;

        while (!queue.isEmpty()) {

            int cur = queue.poll();

            if (inDegree[cur] == 0)
                count++;

            if (!graph.containsKey(cur))
                continue; // no more neighbors

            for (int neighbors : graph.get(cur)) {

                inDegree[neighbors]--;
                if (inDegree[neighbors] == 0)
                    queue.add(neighbors);
            }

        }

        return count == numCourses;

    }
}


class CourseSchedule2 {

     /*logic : 

     1) Topological sort - We need a map to map all neighbors of a vertex--[a,b]--> a is neighbor of b.
     2) indegree array to store number of incoming edges for each vertex   
     3) check if any vertex has 0 indegree and add it to a Queue
        Now remove from the queue, add to result. Check if there are any neighbors and 
        decrement the indegree of neigh. coz the current vertex is removed from queue (in-coming edge- indegree is removed) . 
        If indegree is 0, enqueue them. 
     */           


    public int[] findOrder(int numCourses, int[][] prerequisites) {
     
        
        // kahn's Topological sorting
        
        int[] result = new int[numCourses];
        int count = 0; // pointer for result array and check cyclical dependency
        
        //<k,v> vertex, neighbors 
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        
        int[] indegree = new int[numCourses]; // number of incoming edges
        
        for(int[] pre : prerequisites){
            
            List<Integer> list = adjacency.getOrDefault(pre[1], new ArrayList<>());
            list.add(pre[0]);
            indegree[pre[0]]++;
            adjacency.put(pre[1],list);
        } 
        
        //System.out.println(adjacency);
        
        Queue<Integer> queue = new LinkedList<>();
        
        // pick the ones that have zero indegree and add to queue
        for(int i = 0; i < indegree.length; i++ ){
            //System.out.println(indegree[i]);
            if(indegree[i] == 0) queue.add(i); // value of indegree will be 0, so add i to queue             
        }
        
               
        while(!queue.isEmpty()){
            
             int cur = queue.poll();
                       
             result[count++] = cur; 
             
                          
             // does not contain in keyset -- has no neighbors, but cud be a neighbor of some vertex 
              if(!adjacency.containsKey(cur)) continue;
             
             
              // add 0 indegree neighbors to queue, otherwise decrease indegree as the link is broken when a vertex is removed from queue
             //else {
                 
                 List<Integer> neighbors = adjacency.get(cur);
                 
                 for(int neighbor : neighbors){
                     
                     indegree[neighbor]--;
                     
                     if(indegree[neighbor] == 0) queue.add(neighbor);
                     
                 }
                 
                 
             //}        
              
        }
        
         return (count == numCourses)?result: new int[0]; // acts as count to check cyclical dependency 
                 
        
    }
}






// refer to LC link
class FindValidPair {
    // Valid pair and adjacent
    public String findValidPair(String s) {
        
        if(s == null || s.length() == 1) return s;

        Map<Character, Integer> freq = new HashMap<>();
            
        for(int i = 0; i < s.length(); i++){

            char c = s.charAt(i);

            freq.put(c, freq.getOrDefault(c, 0) + 1);                   

        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < s.length(); i++){

            char first = s.charAt(i - 1);
            char second = s.charAt(i);

            // Adjacent and not first not equal to second 
            if(first != second){
                
                if(freq.get(first) == first - '0' && freq.get(second) == second - '0'){
                     sb.append(first).append(second).toString();
                     break;
                }
            }         

        }

        return sb.toString();
    }
}


class GroupAnagrams {
   
    public List<List<String>> groupAnagrams(String[] strs) {
        
        /*return empty list if input string is null*/
        
        if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s : strs){
            
            char[] ch = s.toCharArray();
           
            Arrays.sort(ch);
            //System.out.println(ch);
            
            /*insert into HashMap*/
            
            /*create a key for HashMap put operation*/
            
            String key = String.valueOf(ch); /*convert sorted char array to string to be used as key*/
           // System.out.println("key "+key);
            
            /*insert*/ 
            
            if(!map.containsKey(key)){
                
               List<String> list = new ArrayList<>();
               list.add(s); 
               map.put(key,list);
            }
            
            else{
                
                map.get(key).add(s);
                
            }
            
            
        }
        
        
        return new ArrayList<List<String>>(map.values());   
        
        
        
        
        
    }
}





class SlidingWindowMaximum {

    // LC 239 https://leetcode.com/problems/sliding-window-maximum/

/*
    Sliding window - fixed size of k. 
     - Need to keep track of max value in window. 
     - Need evict left most and add righ most element when window slides.
     - Remove elements that are smaller than the one inserted

    Priority queue can keep track of max element, but cant evict elements smaller than current.

    Hence use ArrayDeque and store index values of max element. Add elements from back, remove smaller elements than current from back.
 */

// Similar to monotonic stack
 public int[] maxSlidingWindow(int[] nums, int k) {
        
    List<Integer> res = new ArrayList<>();
                      
    Deque<Integer> q = new ArrayDeque<Integer>();

    for(int right = 0 ; right < nums.length; right++){

        // Remove elements in front of q if it goes out of window size
        if(!q.isEmpty() && q.peekFirst() <= right - k){
            q.pollFirst();
        }     

        
        // Remove elements smaller than current from back of queue - Maintain descending order
        // Note : This is a feature that isnt available in PriorityQueue 
        while(!q.isEmpty() && nums[q.peekLast()] < nums[right]){
            q.pollLast();    
        }

        // Add current element to queue
        q.offer(right);

        // Include max element of current window to res                                  
        if(right >= k - 1){                
            res.add(nums[q.peekFirst()]);                
        }                   
    }     
    
    return res.stream().mapToInt(Integer::intValue).toArray();
    
}




    /* Using priority Queue */
    // //credits : https://www.youtube.com/watch?v=LiSdD3ljCIE


    public int[] maxSlidingWindow2(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1]; // num of possible windows = N - k + 1
        int r = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(k, (x, y) -> y.val - x.val); // max heap

        for (int i = 0; i < nums.length; i++) {

            // remove head element if it is out of window range

            while (!pq.isEmpty() && pq.peek().index <= (i - k)) {

                pq.poll();
            }

            pq.offer(new Node(nums[i], i));

            if (i >= k - 1) {

                result[r++] = pq.peek().val;
            }

        }

        return result;

    }

    class Node {

        int val;
        int index;

        public Node(int val, int index) {

            this.val = val;
            this.index = index;
        }

    }

    
}





//2SUm  ??

public class Solution {
    
    public int[] twoSum(int[] nums, int target) {
       
     int[] result = new int[2];
        
     Map<Integer,Integer> map = new HashMap<>();
     
     if(nums == null || nums.length < 2) return new int[2] ; 
        
     
     for(int i = 0 ; i < nums.length ; i++){
         
         int num = target - nums[i];
         
         if(map.containsKey(num)){
             
             result[0] = map.get(num);
             result[1] = i;
             
             return result;
             
         }
         
         else {
             
             map.put(nums[i],i);
             
         }         
         
     }      
        
     return result;   
        
    }
}


//https://leetcode.com/problems/3sum/
class 3Sum {
    
    
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
                
        // For each number
        for(int i = 0; i < nums.length - 2; i++){
                        

           // skip same numbers             
           if(nums[i] <= 0 && (i == 0 || nums[i - 1] != nums[i])){

                 twoSum(nums, i, result);   
                                      
           }
                        
        }
        
        return result;
        
        
    }
    
     private int[] twoSum(int[] nums, int i, List<List<Integer>> result) {

        // Start from nest number to i each time
        int low = i + 1, high = nums.length - 1;

        while(low < high){

            int sum = nums[i] + nums[low] + nums[high];
            
            if(sum < 0) low ++;

            else if(sum > 0) high--;

            else {

                result.add(Arrays.asList(nums[i],  nums[low], nums[high]));

                 low++;
                 high--;

                // skip duplicates
                while (low < high && nums[low] == nums[low - 1]) low++;

            } 
        }

        return new int[] {-1, -1};        
    }
}
}


// LC : 347 : https://leetcode.com/problems/top-k-frequent-elements

public class TopKFrequentElements {

       
    /*
      Approach - Use quick select - modified quick sort algo    
    */
      
    Map<Integer,Integer> freq;
    
    // Using quick sort pivot approach 
    public int[] topKFrequent(int[] nums, int k) {
                      
        // Store frequencies : <number, freq Of occurence>              
        freq = new HashMap<>();
        
        for(int n : nums) freq.put(n,freq.getOrDefault(n,0) + 1);
       
          
        // Basically each unique number will be in the unique array. Corresponding freq in map
        // We can use freq to partially sort them in place                 
        int[] unique = new int[freq.size()];
        int n = 0;
        for(int key : freq.keySet()){
            unique[n++] = key;
        }
        
        // Perform quick select till we match pivot index with kth largest from right end 
        quickselect(unique,0,unique.length - 1, unique.length - k);
        
        // construct response
        int[] res = new int[k];
        
        for (int i = 0; i < k; i++) {
            res[i] = unique[unique.length - k + i];
        }

        return res;
    }
    
    //Almost 99% similar to quicksort

    // Quickselect pivot logic 
    // Partially sort the array such that
    // left of pivot index, we have values smaller than pivot value (based on freq of occurence in map)
    // right of pivot index, we have values larger than pivot value (based on freq of occurence in map)
    private void quickselect(int[] nums, int left, int right, int topK){

        if(left < right) {

            // Perform partition and update new  pivot position.
            int pivotIndex = partition(nums,left,right);

            // Adjust pivot index appropriately
            if(topK == pivotIndex) return;

            else if(topK < pivotIndex) quickselect(nums,left,pivotIndex - 1, topK);

            else quickselect(nums,pivotIndex + 1, right, topK);     

        }
                 
    }
       
    // Apply quicksort partition logic 
    //  nums = [4 1 4 2 1 3 4 3 5] -> freq map 4:3 1:2 2:1 3:2 5:1    
    private int partition(int[] nums, int left, int right){

        // pivot value    
        int pivot = freq.get(nums[right]);

        int i = left - 1; // A step lagging left
        int j = left; 

        // Rearrange array by comparing with pivot value -  freq of occurence
        for(j = left; j < right; j++ ){

            if(freq.get(nums[j]) < pivot){
                
                i++; // Note !
                 swap(i, j, nums);      
                
            }            
        }    
        
        swap(i + 1,right,nums);  

        return i + 1;
    }

    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }







    /*Priority Queue based soln */

        public int[] topKFrequentPQ(int[] nums, int k) {
       
               
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n,map.getOrDefault(n,0) + 1);
        }
        

        // Optimization - Find k top frequent (larger occurence ??) use min heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->a.getValue() - b.getValue());       
        
        for(Map.Entry<Integer,Integer> e : map.entrySet()){            
            
            if(pq.size() < k) pq.offer(e);

            else {

                if(e.getValue() > pq.peek().getValue()){
                    pq.remove();
                    pq.add(e);
                } 
            } 



        }

        int[] res = new int[k];
        int i = 0;
        while(!pq.isEmpty()){            
            res[i++] = pq.poll().getKey();            
        }

        return res;

        //return result.stream().mapToInt(Integer::intValue).toArray();
        
        /*int[] arr = new int[result.size()];
        
        int i = 0;
        
        for(int g : result){
            arr[i] = g;
            i++;    
        }        
        return arr;*/
    }

}


// https://leetcode.com/problems/design-authentication-manager

class AuthenticationManager {
    //<token id, time to live>
    Map<String, Integer> ttlMap = new HashMap<>();

    // <token id, token>
    //Map<String, String> tokenMap = new HashMap<>();

    int ttl;

    public AuthenticationManager(int timeToLive) {

        ttl = timeToLive;                
    }
    
    public void generate(String tokenId, int currentTime) {

           ttlMap.put(tokenId, currentTime + ttl);
          // tokenMap.put(tokenId, new Random().toString()); 

    }
    
    public void renew(String tokenId, int currentTime) {

            if(ttlMap.containsKey(tokenId)){

                // unexpired token  
                int expires = ttlMap.get(tokenId);  
                if( expires > currentTime) {
                    ttlMap.put(tokenId, currentTime + ttl);
                }
            }
    }
    
    public int countUnexpiredTokens(int currentTime) {

            int count =  0;

            for(String token : ttlMap.keySet()){

                if(ttlMap.get(token) > currentTime) count++;
            }

            return count;   
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */



// LC 23 : https://leetcode.com/problems/merge-k-sorted-lists/


class MergeKSortedLists {

    //Time O(n log k) // Space O(1)
    // Pick two lists at a time and merge
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0 ) return null;
            
        if(lists.length == 1) return lists[0];

        // two lists at a time
        int i = 1;
        ListNode l1 = lists[0];

        while(i < lists.length){

            ListNode res = merge2Lists(l1, lists[i]);    
            i++;

            l1 = res;
        }    

        return l1;

    }


    private ListNode merge2Lists(ListNode l1, ListNode l2){

        ListNode resultHead = new ListNode(0);// Dummy head
        ListNode ptr = resultHead;

        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                ptr.next = l1;
                l1 = l1.next;
            }  

            else {
                ptr.next = l2;
                l2 =l2.next;
            }  

            ptr = ptr.next;        
        }

        // Remaining
        if(l1 != null) ptr.next = l1;

        else if(l2 != null) ptr.next = l2;

        return resultHead.next;

    }



    // Time - O(n log k), Space - O(k) 

    public ListNode mergeKLists2(ListNode[] lists) {

        if(lists == null) return null;

        ListNode resultHead = new ListNode(0);// Dummy head

        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        for(ListNode head : lists){

            if(head != null) pq.add(head);    
        }

        ListNode node = resultHead;
        
        while(!pq.isEmpty()){

            ListNode cur = pq.remove();
            
            node.next = new ListNode(cur.val);
            node = node.next;
            
            if(cur.next != null) pq.add(cur.next);

        }

        return resultHead.next; // resultHead contains dummy value 0 in teh beginning

    }
    
}


//trees

// LC 450. Delete Node in a BST https://leetcode.com/problems/delete-node-in-a-bst/


//Time complexity - O(log n)
// Space O(h) -> for the recursive stack
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return null;

        // Node not found yet
        if(key < root.val) {

            root.left = deleteNode(root.left, key);
        }

        // Why map return value of deleteNode to root.left above ??
        // Handles cases where key is not found in tree


        // Node not found yet
        else if(key > root.val){

             root.right = deleteNode(root.right, key);
        }

        // Found node to be deleted
        else {

            // is it a leaf node ?? No left and rigth child
            if(root.left == null && root.right == null) {
                return root = null;
                
            }

            // node has one child
            else if (root.left == null || root.right == null){
                return root.left == null ? root.right : root.left;
            }


            // node has 2 children
            else {

                TreeNode ptr = root;    
                ptr = ptr.right;

                while(ptr.left != null){
                    ptr = ptr.left;
                }

                root.val = ptr.val;

                // Since ptr.val is assigned to root.val, we will have a node with duplicate value, hende proceed to delete
                root.right = deleteNode(root.right, ptr.val); 
                
                                
            }

        }           

        return root;        
    }   
}



// 103 : https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

class zigzagLevelOrderTraversal {

     // Time O(n), Space O(n)
     public List<List<Integer>> zigzagLevelOrderOptimized(TreeNode root) {
        
               
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
            
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        boolean isReverse = false;
        
        while(!queue.isEmpty()){
            
            List<Integer> levelResult = new LinkedList<>();
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                
                TreeNode node = queue.remove();

                if(isReverse) levelResult.addFirst(node.val);
                else levelResult.addLast(node.val);
                                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }                             

            isReverse = !isReverse; // flip
            
            result.add(levelResult);
            
        }
        
        return result;
        
    }


     public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
               
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
            
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        boolean isReverse = false;
        
        while(!queue.isEmpty()){
            
            List<Integer> levelResult = new ArrayList<>();
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                
                TreeNode node = queue.remove();
                
                levelResult.add(node.val);
                                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            
            if(isReverse) Collections.reverse(levelResult);           

            isReverse = !isReverse;
            
            result.add(levelResult);
            
        }
        
        return result;
        
    }
}


/*
LC 987. Vertical Order Traversal of a Binary Tree
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

  Using level order traversal.
  (colIndex computed by : root colINdex is 0, left child = colIndex - 1, rigth child = colIndex + 1)
   Collect nodes in each colIndex into a list. This will be the vertical order.
  *
 
  */

public class VerticalOrderTraversalOrdered {


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;

        // ColIndex,Level,TreeNode  - PQ order by level
        Queue<Triplet<Integer,Integer,TreeNode>> q = new LinkedListImplementation<>();

        // ColIndex, ColIndex,Level,TreeNode - TreeMap to collect all nodes in a column in order from left to right
        Map<Integer, List<Triplet<Integer,Integer,TreeNode>>> map =
                     new TreeMap<>(); 
        
        q.add(new Triplet(0,0,root));

        while(!q.isEmpty()){            

            Triplet<Integer,Integer,TreeNode> triplet = q.remove();
                
            int colIndex =  triplet.t1;
            int level = triplet.t2;
            TreeNode n = triplet.t3;

            map.putIfAbsent(colIndex, new ArrayList<>()); 
            map.get(colIndex).add(triplet);

            if(n.left != null) q.add(new Triplet(colIndex - 1, level + 1, n.left));
            if(n.right != null) q.add(new Triplet(colIndex + 1, level + 1, n.right));                                 
           
        } 


        for(int colIdx : map.keySet()){

            List<Triplet<Integer,Integer,TreeNode>> nodes = map.get(colIdx);

            // Sort as per what is given in description : 
            //multiple nodes in the same row and same column. In such a case, sort these nodes by their values.  
            Collections.sort(nodes, (x,y) -> x.t2 == y.t2 ? x.t3.val - y.t3.val : x.t2 - y.t2);


            // Process nodes to get result integer list 
            List<Integer> sortedNodes = new ArrayList<>();
            
            nodes.stream().forEach(n -> sortedNodes.add(n.t3.val));

            res.add(sortedNodes);
        }   

        return res;
    }


    // Generic class to simplify code 
    // Access is not private. TO improve redability     
    class Triplet<T1, T2, T3 > {

     T1 t1;
     T2 t2;
     T3 t3;
    

    public Triplet(T1 t1, T2 t2, T3 t3){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }


}
}



// LC 199 : https://leetcode.com/problems/binary-tree-right-side-view/

// Time O(N) Space O(D) in avg and O(N) in worst case

public class BinaryTreeRightSideView {

        
    // Level order traversal, BFS, but pick the rightmost node at each level. 

    public List<Integer> rightSideView(TreeNode root) {
    
      List<Integer> ll = new ArrayList<Integer>();
      Queue<TreeNode> q = new LinkedList<TreeNode>(); 
      
      if(root == null) return ll;
          q.add(root);
          
    
           while(!q.isEmpty()){
                
            int level = q.size();
               
                
                for(int i=0;i<level;i++){
                    TreeNode n = q.remove();
                    
                    // Pick the last element in each level -> i = level - 1
                    if(i==level-1){// to get the rightmost element in the level
                        ll.add(n.val);
                    }
                    
                    if(n.left!=null){
                     q.add(n.left);
                    }
                 
                    if(n.right!=null){
                     q.add(n.right);
                    }                     
                    
                }                     
         }
        
        return ll;
}

}

// No node can have more than 2 connections in path
// Time O(N), space O(H) and O(N) worst case
class BinaryTreeMaxPathSum {
    
    
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
         
         dfs(root);        
         return max;
    }
    
    
    private int dfs(TreeNode root){
        
        if(root == null) return 0;
                 
        
        int left = Math.max(dfs(root.left), 0); // Ignore -ve values
        int right = Math.max(dfs(root.right), 0);  // Ignore -ve values
        
        // Path sum thro current route 
        int pathSum = left + right + root.val;

        max = Math.max(max, pathSum);
        
        //System.out.println(left +"--"+ right);
                
        // Return max gain from this node. Next path can include both left and right clild of a node, got to pick one
        return Math.max(left, right) + root.val;
    }
}





class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        int max = 0;
        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {

            if (prices[i] < min) {
                min = prices[i];
            }

            else if (prices[i] - min > profit) {
                max = prices[i];
                profit = max - min;
            }

        }

        return profit;

    }
}

// v[2,1,2,1,0,1,2] // o/p expected 2

// LC 11 https://leetcode.com/problems/container-with-most-water/
/**
 * At each iteration calculate max area that can be computed with left and right pointer. Compare to current max area.
 */
class ContainerWithMaxWater {
    public int maxArea(int[] height) {

        // 2 pointer approach
        int maxArea = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length - 1;
       
        while(left <= right){            
        
            int width = right - left;            
            int curArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, curArea);

            if(height[left] <= height[right]) left++;

            else right--;       
        }                      
        
        return maxArea;
    }

    public static void main(String[] args){
        ContainerWithMaxWater obj = new ContainerWithMaxWater();

        int[] sample1 = new int[] {1,8,6,2,5,4,8,3,7};
        int[] sample2 = new int[] {1,1};

        System.out.println("Expected result : 49");
        System.out.println("Actual result : " + obj.maxArea(sample1));

        System.out.println("Expected result : 1");
        System.out.println("Actual result : " + obj.maxArea(sample2));

    }

}



public class GasStation {

    //LC 134 : https://leetcode.com/problems/gas-station/

    /*
        Approach : 
        
        Choose starting point as 0 and proceed to see if tank has enough gas to reach next station.
        If not, we have to reset and start from next station again.

        We also need another variable to keep track of gas remaining overall (Positive overall gas proves the circular path exists from start to end )       
     
     */

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int remainingGas = 0; // To find starting point 
        int totalGas = 0; // To check overall if the loop is possible
        int start = 0;

        for(int i = 0; i < cost.length; i++ ){

            remainingGas += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];

            if(remainingGas < 0){
                start = i + 1; // pick the next start point
                remainingGas = 0; // reset
            }            
        }   

        return (totalGas >= 0) ? start : -1;
    }

}


// https://leetcode.com/problems/number-of-visible-people-in-a-queue
class NumberOfPeopleVisible {

    public int[] canSeePersonsCount(int[] heights) {


        Stack<int[]> stack = new Stack<>();

        int[] res = new int[heights.length];

        for(int i = 0; i < heights.length; i++){


            while(!stack.isEmpty() && heights[i] > stack.peek()[0]){

                int[] pair = stack.pop();

                res[pair[1]]++;
            }

            if(!stack.isEmpty()) res[stack.peek()[1]]++;

            stack.push(new int[] {heights[i], i});
        }

        return res;
        
    }
}


// binary search 


public class SearchRotatedSortedArray {


    // Find min using minimization bin search template, then look for target in right half or left half.

    // Time limit exceeded.

    // LC 33 - https://leetcode.com/problems/search-in-rotated-sorted-array

    public int search(int[] nums, int target) {

        int minIndex = findMin(nums);

        //System.out.println("min idx : " + minIndex);
     
        if(target <= nums[nums.length - 1]) return binSearch(nums, target, minIndex, nums.length - 1);

        else return  binSearch(nums, target, 0, minIndex - 1);
        
    }


    private int findMin(int[] nums){

        int low = -1, high = nums.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(nums[mid] <= nums[nums.length - 1]) high = mid;

            else low = mid;
        }

        return high;
    }

    private int binSearch(int[] nums, int target, int low, int high){

        while(low <= high){

            int mid = low + (high - low)/2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] < target) low = mid - 1;

            else high = mid - 1;           

        }
        return -1;

    }

}

class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {


        // Binary search minimization

        int max = 0; // 1 banana per hour

        for(int num : piles){

            max = Math.max(max, num);
        }

        // Now we need to optimize num of banana per hour from 1 - max;
        // Can use for loop to test 1 .....max, but can use binary search to optimize 

        int low = 0, high = max;

        while(low + 1 < high){

            int mid = low + (high - low) / 2;

            boolean yes = canEat(mid, piles, h);

            if(yes) high = mid;

            else low = mid;
        }    

        return high;

    }


    private boolean canEat(int mid, int[] piles, int maxHours){

        int time = 0;

        for(int i = 0; i < piles.length; i++){

            time += Math.ceil((double)piles[i]/mid);
        }

        return time <= maxHours;

    }
}


/*
 Approach : 

 Use two heaps: maxHeap for lower half, minHeap for upper half. Balance heaps after each add.
 For odd total elements, median is maxHeap top. For even, average of both heap tops.

 Keep two heaps. One stores lower half of nums, other stores the upper half.
 Keep the heaps balanced. lower can have one element more than upper (to satisfy odd number of elements condition)
 Upper always can have only as much elements as lower.

 // Imagine a BST structure with median at root. Here left and right subtrees are strored in heaps, the root itself is stored in lower half. (Hence lower heap can have one extra element than upper)  

// LC 295 : https://leetcode.com/problems/find-median-from-data-stream


*/


class FindMedianFromStream {
        
    PriorityQueue<Integer> upperHalf;
    PriorityQueue<Integer> lowerHalf;

    public FindMedianFromStream() {

        upperHalf = new PriorityQueue<>();             
        lowerHalf = new PriorityQueue<>((x,y)-> y - x);
    }
    
    public void addNum(int num) {

        // Same as BST condition
        if(lowerHalf.isEmpty() || num < lowerHalf.peek()){
            lowerHalf.add(num);
        }

        else upperHalf.add(num);

        // Rebalance based on size        
        
        // lower can have one additional element than upper, but not more than 1.
        if((lowerHalf.size() - upperHalf.size()) > 1){
            upperHalf.add(lowerHalf.remove());
        }        

        // Can have same or lesser than lower
        else if(upperHalf.size() > lowerHalf.size()){
            lowerHalf.add(upperHalf.remove());
        }

    }
    
    public double findMedian() {
        
        // A case where there are odd num of input numbers
        if(lowerHalf.size() >  upperHalf.size()){
            return lowerHalf.peek();
        }

        else return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
  

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */






//https://leetcode.com/problems/decode-string/submissions/

class DecodeString {
   
    public String decodeString(String s) {
        
        
        Stack<Integer> countStack = new Stack<>();
        
        Stack<StringBuilder> stringStack = new Stack<>();
        
        StringBuilder current = new StringBuilder();
        
        int num = 0;

        for (char c : s.toCharArray()) {
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');  // Build multi-digit numbers
            }

            else if (c == '[') {
 
                // Push the current number and string onto stacks
                countStack.push(num);
                stringStack.push(current);
 
                // Reset for new substring
                current = new StringBuilder();
                num = 0;
 
            } 
            
            else if (c == ']') {
                
                // Pop number and previous string
                StringBuilder decodedString = stringStack.pop();
                int count = countStack.pop();
                
                // Repeat the current string `count` times and append to previous
                for (int i = 0; i < count; i++) {
                    decodedString.append(current);
                }

                current = decodedString;  // Now becomes the current string
            } 

            // Pay attention
            else {
                current.append(c);  // Normal character
            }
        }
        return current.toString();
    }
}


// LC 460. LFU Cache
// https://leetcode.com/problems/lfu-cache/

/*
 
    Approach : 
    
    Why simply moving to the front won't work for LFU:
    LFU prioritizes frequency, not recency: When an item is accessed, its frequency increases. If you simply move it to the front of a DLL based on access (like an LRU), the DLL will still be ordered by recency of access, not by frequency. A less frequently used item that was just accessed would move to the front, even if many other items have a higher overall frequency.


    Pitfall 1 : Have two maps, one for <key,value> and one for <key, freq>. : 
        How to get least frequently used ?  How about multiple entries with same frew ?
    
    Soln : Stick to 2 Maps, 
        1. Map <key,value> and 2. Map <freq, key>.
        2. Map<freq, set>, linked hash set to maintain order.(Helps in tie breaker situation) 

    Pitfall 2 : Now after populating both maps, how to update a key-value pair ? How to know what
         Map <frequency, set> entry they are stored at ??

    Soln : Use a Node (key, value, freq).
        1. Map<key, Node> 
        2. Map<freq, Set<Node>>        
     */

  

class LFUCache {
  
    int capacity;

    // key, nodes
    Map<Integer,Node> cache = new HashMap<>();
    
    // freq, nodes
    Map<Integer, Set<Node>> freqMap = new HashMap<>(); 

    int minFrequency; // pointer to lowest freq values in the freqMap. Makes it easier to evict

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {

        if(cache.containsKey(key)){

            Node node = cache.get(key);

            // Update frew
            updateFreqMap(node);

            return node.value;

        }   
         
         else return -1;
        
    }
    
    public void put(int key, int value) {
      
        if (capacity == 0) return;

        if(cache.containsKey(key)){
                       
            // Update the new value in cache and then update frequency in freq map
            Node node = cache.get(key);
            node.value = value; // updated value
            updateFreqMap(node);
        }

        else {

            // Capacity check
            if(cache.size() == capacity) {
                // remove least freq used
                evict();
            }   
            
            // Insert node into cache and freq map
            Node node = new Node(key, value, 1);
            cache.put(key, node);

            // Here frequency is the key    
            freqMap.putIfAbsent(1, new LinkedHashSet<>()); // LinkedHashSet to maintain order of insertion
            freqMap.get(1).add(node);

            minFrequency = 1; // Useful when evicting least frequently used 
        }
        
    }

    // Updates frequency count in freqMap
    private void updateFreqMap(Node n){
        
        int curFreq =  n.freq;  

        Set<Node> set = freqMap.get(curFreq);
        set.remove(n); // LinkedHashSet - hence just updating n.freq will be incorrect 

        //cleanup and update minimum freq
        if(set.isEmpty()){

            // delete empty set
            freqMap.remove(curFreq);

            // Current min freq happens to be current freq of node/empty set, needs to be incremented to next    
            if(minFrequency == curFreq) minFrequency++; // Next bigger freq

        } 

        // Increase freq and re-insert into freq map
        n.freq++;

        freqMap.putIfAbsent(n.freq, new LinkedHashSet<>());
        freqMap.get(n.freq).add(n);      
        
    }

    private void evict(){

        Set<Node> set = freqMap.get(minFrequency);
                
        // Remove first node
        Node firstNode = set.iterator().next();
        set.remove(firstNode);

        //cleanup empty set 
        if(set.isEmpty()) freqMap.remove(minFrequency);
        
        // Finally evict from cache as well
        cache.remove(firstNode.key);
    }


    class Node {

        int key;
        int value;
        int freq;
        
        public Node(int k, int v, int f){
            key = k;
            value = v;
            freq = f;
        }

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



public class LFUCacheOld {

    
    // Key, Node(key,value,freq)
    Map<Integer,Node> cache;
    // freq, Node(key,value,freq)
    Map<Integer, LinkedHashSet<Node>> freqMap;

    int capacity;
    int minFrequency;

    public LFUCache(int capacity) {
        
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();        
        this.capacity = capacity;
        this.minFrequency = 0;
    }
    
    public int get(int key) {

        if(!cache.containsKey(key)) return -1;
        
        Node node = cache.get(key);

        //node.frequency++; Can't read old freq from freqMap if freq is incremented here

        updateFreqMap(node);

        return node.value;

    }
    
    
    public void put(int key, int value) {
             
        // 1. Start with containsKey condition (easy to update existing node, 
        //dont have to deal with max capacity, <freq, set> not populated etc)
        // 2. Then deal with capacity full condition - Perform evict.
        // 3. Post eviction, add new node condition
        
        if(cache.containsKey(key)){

            Node node = cache.get(key);
            node.value = value;

            updateFreqMap(node); 
            return;  // Otherwise will continue to step 3- add new node.          
        } 

        if(isFull()) evict();  

        // Add new node 
        Node n = new Node(key, value,1);

        cache.put(key,n);            

        freqMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(n);

        minFrequency = 1; // Next easy canditate to be evicted             
        
    }

    private void updateFreqMap(Node node) {
       
        int curFreq = node.frequency;

        freqMap.get(curFreq).remove(node);

        // Remove empty list to avoid min frequency pointing to empty set
        if (freqMap.get(curFreq).isEmpty()) {

            freqMap.remove(curFreq);
            
            if (minFrequency == curFreq) {
                minFrequency++;
            }
        }
        
        // Finally update freq here
        node.frequency++;

        freqMap.computeIfAbsent(node.frequency, k -> new LinkedHashSet<>()).add(node);
    }



    private void evict(){

        // cache is full & doesn't contain Node to be inserted

        // Get least used nodes & delete the first one
        Set<Node> nodes = freqMap.get(minFrequency);

        Node firstNode = nodes.iterator().next();

        nodes.remove(firstNode);

        // To handle empty set issue.
        if(nodes.isEmpty()) freqMap.remove(minFrequency);    
     
        cache.remove(firstNode.key);            

    }
    

    private boolean isFull(){
        
        return cache.size() >= capacity;
        
    }

    class Node {

        int key;
        int value;
        int frequency;

        public Node(int key, int value, int frequency){
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

}


/**
 * 
 * ip 1 : ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
 * op 1 : [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
 *
 * ip2 : ["LFUCache","get","put","get","put","put","get","get"]
 * op2 : [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
 *  
 */

 /**

    Intution : 

    Pitfall 1 : Have two maps, one for <key,value> and one for <key, freq>. : 
        How to get least frequently used ?  How about multiple entries with same frew ?
    
    Soln : Stick to 2 Maps, 
        1. Map <key,value> and 2. Map <freq, key>.
        2. Map<freq, set>, linked hash set to maintain order.(Helps in tie breaker situation) 

    Pitfall 2 : Now after populating both maps, how to update a key-value pair ? How to know what
         Map <frequency, set> entry they are stored at ??

    Soln : Use a Node (key, value, freq).
        1. Map<key, Node> 
        2. Map<freq, Set<Node>>        
     */

    // Start with put method, then work on get. 




    // LC : https://leetcode.com/problems/pour-water

class PourWater {
    public int[] pourWater(int[] heights, int volume, int k) {
        
        /* Just follow the question
         * k is the pivot index 
         * move to left step by step and find smallest value than k
         * if not, move to right and do the same thing
         * if still not found, the smallest idx = k.
         * Increment heights[smallest index]
         */ 

        for(int i = 0; i < volume; i++){

            int idx = k;    

            for(int left = k - 1; left >= 0; left --){

                if(heights[left] < heights[idx]){

                    idx = left;               
                } 
                else if(heights[left] >  heights[idx]){
                     break;
                }     
            }

            // No smaller index found on left, hence check and find on right
            if(idx == k) {

                for(int right = k + 1; right < heights.length; right++){
        
                    if(heights[right] < heights[idx]){
                
                        idx = right;
                    } 
                    else if(heights[right] > heights[idx]){
                        break; 
                    }
                }
            }

            heights[idx]++; // Smallest on right or by default use k as smallest idx
        }      

        return heights;
    }

    



}



// LC 1209. Remove All Adjacent Duplicates in String II
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

public class RemoveDuplicatesII {

     // O(n) time and O(n) space

     public String removeDuplicates(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
       
        int count[] = new int[sb.length()];
       
        for(int i = 0; i < sb.length(); i++){ // Note : Do not use a variable 'len' to store sb.length() 

            if(i == 0 || sb.charAt(i - 1) != sb.charAt(i)){                
                count[i] = 1;
            }           

            else {
                count[i] = count[i - 1] + 1;              
                
            }  

            if(count[i] == k){

                    sb.delete(i - k + 1, i  + 1 );
                    
                    i = i - k;
            }   

             
        }

        return sb.toString();
    }

}

class ValidAnagram {
   
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;

        int[] alp = new int[26];

        for(int i = 0; i < s.length(); i++){
            
            alp[s.charAt(i) - 'a']++;
            alp[t.charAt(i) - 'a']--;    
        }        

        for(int i = 0; i < 26; i++){
            
            if(alp[i] != 0) return false;
        }
        
        return true;
    }
}



class ValidParanthesis {
    
    
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<>();
        char[] ch = s.toCharArray();

        for(char c : ch){            
            if(c == '{') stack.push('}');            
            else if(c=='(') stack.push(')');            
            else if(c=='[') stack.push(']');            
            else if(stack.isEmpty() || stack.pop() != c) return false;         
        }     
           
        return stack.isEmpty();    
        
    }
}


class Roman2Integer {

    
    public int romanToInt(String s) {
        
        Map<Character,Integer> map = new HashMap<>();

        map.put('I',1);
         map.put('V',5);
          map.put('X',10);
           map.put('L',50);
            map.put('C',100);
             map.put('D',500);
              map.put('M',1000);


        int res = 0;

        int cur = 0;    
        int prev = 0;

        for(int i = s.length() - 1; i >= 0; i--){

            char c = s.charAt(i);

            cur = map.get(c);

            if(cur < prev) res -= cur; 
            
            // IV from right to left. Initially we process 'V' = 5, then later "I", so subtract 1 from result to compensate 

            else res += cur;

            prev = cur;
            
        }      

        return res;

    }

    
}

// Split path by '/'. Use stack to track directories. Skip empty and '.' entries. For '..' pop from stack if not empty. 
//Finally, build path from stack elements with '/' separator.

// LC - 71. Simplify Path
// https://leetcode.com/problems/simplify-path

public class SimplifyPath {

    public String simplifyPath(String path) {

        String[] str = path.split("/");

        List<String> components = new LinkedList<>();

        StringBuilder sb = new StringBuilder();    

        for(int i = 0; i < str.length; i++){

            String s = str[i];

            if(s.equals("") || s.equals(".") ){
                continue;
            }
            
            else if(s.equals("..")){

                if(components.size() > 0)  components.removeLast();                 

            }
    

            else {                  
                  components.add(s);
            }

                     
        }

        for(String component : components){
            sb.append("/");
            sb.append(component);
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }

}

// LC 647 : https://leetcode.com/problems/palindromic-substrings/

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        
        int count = 0;


       // Expand around center index 
       for(int i = 0; i < s.length(); i++){


            // Odd length - left and right are same index 
            count += countPalindrome(s,i,i);
            
            // Even length
            count += countPalindrome(s,i,i+1);

       }

       return count;
    }

    public int countPalindrome(String s, int i, int j){
        
        int count = 0;

        // Boundary check 
        while(i >= 0 && j < s.length()){

            if(s.charAt(i) != s.charAt(j)){
                
                break;                        
            }

            count++;           

            // Expand from center

            i--;
            j++;
        }
        return count;
    }

}


// LC 415 https://leetcode.com/problems/add-strings/?

public class AddStrings {

    public String addStrings(String num1, String num2) {
 
        StringBuilder sb = new StringBuilder();
    
        int remainder = 0;    
    
        int len1 = num1.length(), len2 = num2.length();
    
        int len  =  len1 > len2 ? len1 : len2;    
    
        for(int i = 0; i < len; i++){
    
            int l1 =  i >= len1  ? 0 : num1.charAt(len1 - 1 - i) - '0';
            int l2 =  i >= len2 ? 0 : num2.charAt(len2 - 1 - i) - '0';
    
            //System.out.println("l1 : " + l1 + " l2 : " + l2);
    
            int res = l1 + l2 + remainder;
    
            //System.out.println("res : " + res);
    
            sb.append(res % 10); 
            remainder = res / 10;           
            
        }
    
        if(remainder > 0) sb.append(remainder);
        return sb.reverse().toString();
    
        }
}



class StringCompresion {
    
    public int compress(char[] chars) {

       int i = 1; 

       int count = 1; 

       int ptr = 0; 

       while(i <= chars.length){
               
            if(i < chars.length &&  chars[i] == chars[i - 1]){
                count++;                
            }         

            else {

                chars[ptr++] = chars[i - 1]; //copy first ocurence of character to correct pos
                
                if(count > 1){

                    String cnt = String.valueOf(count);

                    for(char c : cnt.toCharArray()){

                        chars[ptr++] = c;        
                    }

                    // reset
                    count = 1;
                }             
                 
                
            }

            i++;   
        }

        return ptr;
        
    }
}


// backtracking

class CombinationSum2 {

    int count = 0;/* to count the print statement */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> MainList = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(MainList, new ArrayList<>(), candidates, target, 0);
        return MainList;

    }

    public void backtracking(List<List<Integer>> MainList, List<Integer> tempList, int[] candidates, int rem,
            int start) {

        // System.out.println(count++ + "tempList:" + tempList);

        if (rem < 0)
            return; /* non positive integers */

        if (rem == 0)
            MainList.add(new ArrayList(tempList)); /* target achieved, hence add tempList to MainList */

        for (int i = start; i < candidates.length; i++) {

            if (i > start && candidates[i] == candidates[i - 1])
                continue; /* i > start to prevent out of bounds exception */

            else {

                tempList.add(candidates[i]);
                backtracking(MainList, tempList, candidates, rem - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

    }

}

class N_Queens {

    /*
        Diagonal - Difference in row and col remains the same. (0,0), (1,1), (2,2) 
        row - col -> (0 - 0) = (1 - 1) = (2 - 2) = 0;
        Anti-Diagonal (left diag) - Sum of row and col remain the same : (0,2),(1,1),(2,0) - top right to bottom left.
        row + col -> (0 + 2) = (1 + 1) = (2 + 0) -> 2

    */

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        Set<Integer> usedCols = new HashSet<>(); 
        Set<Integer> usedDiag = new HashSet<>(); // Holds row - col values
        Set<Integer> usedAntiDiag = new HashSet<>(); //Holds row + col
        char[][] matrix = new char[n][n];

        backtracking(0, usedCols, usedDiag, usedAntiDiag, matrix);

        return res;
    }


    private void backtracking(int row,
                             Set<Integer> usedCols,
                             Set<Integer> usedDiag,
                             Set<Integer> usedAntiDiag, 
                             char[][] matrix ){

        if(row == matrix.length){
            addToResult(matrix);
            return;
        }

        // Each column in row 
        for(int col = 0; col < matrix.length; col++){

            int column = col;
            int diag = row - col;
            int antiDiag = row + col;  

            // 
            if(usedCols.contains(column) || usedDiag.contains(diag) || usedAntiDiag.contains(antiDiag)){
                continue;
            }

            // Add queen to board
            usedCols.add(column);
            usedDiag.add(diag);
            usedAntiDiag.add(antiDiag);
            matrix[row][col] = 'Q';

            backtracking(row + 1, usedCols, usedDiag, usedAntiDiag, matrix);

            usedCols.remove(column);
            usedDiag.remove(diag);
            usedAntiDiag.remove(antiDiag);
             matrix[row][col] = '.';
            
        }        
    }

    
    private void addToResult(char[][] matrix){

        List<String> sol = new ArrayList<>();
        
        for(char[] row : matrix){

            StringBuilder sb = new StringBuilder();

            for(char c : row){
                
                if(c == 'Q') sb.append(c);
                else sb.append('.');
            }         

            sol.add(sb.toString());    
        }
        
        res.add(sol);
       

        matrix = new char[matrix.length][matrix[0].length];
    }
    
}


/*
    Approach : 

    Split the ip address string into 4 segments.
    Use backtracking to simulate different possibilities.
    Validate the correct ip address and return a list

    Example : 25525511135
    ignored sample : 4 segments but incomplete & invalid string: 2.5.5.25
    valid sample : 4 segments, complete & valid 255.255.11.135
 */

// LC 93 : https://leetcode.com/problems/restore-ip-addresses/


class RestoreIpAddress {

    public List<String> restoreIpAddresses(String s) {

          List<String> result = new ArrayList<>();  

          backtrack(s, 0, new ArrayList<>(), result); 

          return result;
    }


    private void backtrack(String s, int start, List<String> path,  List<String> result){

        // Termination condition
        if(path.size() == 4) {
                    

            // reached end of string
            if(start == s.length()){
                result.add(String.join(".", path));  
                
                System.out.println("valid : " + String.join(".", path));                
            }

            //Ignored segments
            else System.out.println("ignored : " + String.join(".", path));    

            return;
        } 
                 

        // Which ever ends first
        int newLen = Math.min(start + 3, s.length());

        for(int end = start + 1; end <= newLen; end++){

            String segment = s.substring(start,end);

            if(segmentValidityCheck(segment)){

                path.add(segment);

                System.out.println(segment);
                
                backtrack(s, end, path, result);

                path.remove(path.size() - 1);

            }
        }   

    }


    private boolean segmentValidityCheck(String segment){

        // Segment og length > 1 but starts with 0 -> 065 
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }

        // Range check 0 - 255
        int num = Integer.parseInt(segment);
        return num >= 0 && num <= 255;        
    }


}



// LC 206 https://leetcode.com/problems/reverse-linked-list/description/

class ReverseLinkedList {


  public ListNode reverseList(ListNode head) {

    if(head == null || head.next == null) return head;

    ListNode cur = head;
    ListNode prev = null;
  
    while(cur!= null){

        ListNode temp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;
    }

    return prev;        
}



 public ListNode reverseListRecursive(ListNode head) {
       
    if(head == null || head.next == null) return head;
     
    ListNode newHead = reverseList(head.next);
    ListNode curr = head.next;
    curr.next = head; // point to prev node
    head.next = null;
    return newHead; // new head must contain the head , ie last node..not the current head.
      
}

  public static void main(String[] args){

    ReverseLinkedList obj = new ReverseLinkedList();

    ListNode l1 = ListUtil.createNode(Arrays.asList("1","2","3","4","5"));
    System.out.println("Expected : 5 4 3 2 1");
    System.out.print("Actual :" );
    ListUtil.printList(obj.reverseList(l1));
  }


}


//dp

class LongestIncreasingSubSequence {
    public int lengthOfLIS(int[] nums) {
        
      /*DP Solution, easy to understand O(n^2)*/  
        int n = nums.length;
        
        int[] dp = new int[n];
         
        /*fill array with 1s---- minumum length of subsequence is 1 */        
        
        Arrays.fill(dp,1);
        
        
        /*i - starting from index 1, if value at index j is smaller, we have an increasing subsequence*/
        for(int i = 1; i < n; i++){
            
            for(int j = 0; j < i; j++){               
                
                if(nums[j] < nums[i]){
                    
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    
                }        
                
            }
            
        }
        
        
        int res = 0;
        
        /*Iterate through the new array and find the largest number*/
        for(int c = 0; c < dp.length; c++){
            
            res = Math.max(res, dp[c]);
            
            //System.out.println(dp[c]);          
            
        }
        
        
       return res;       
        
    }
}


class MaximalSquare {
     
   
        // LC 221
        // https://leetcode.com/problems/maximal-square
        
        /*DP non optimized-- https://www.youtube.com/watch?v=_Lf1looyJMU Thushar Roy

        
         Runtime O(m * n) :
         Space : O(m * n)
         */      
        
         public int maximalSquare(char[][] matrix) {             
        
            int max = 0;
    
            int[][] dp = new int[matrix.length][matrix[0].length];
    
            for(int row = 0; row < matrix.length; row++){
                for(int col = 0; col < matrix[0].length; col++){
    
                    if(matrix[row][col] == '0') continue; // skip           
                     
                    // Cells that have left, right and diagonal cell
                    if(row > 0 && col > 0){
                           
                        // 1 + min(diagonal, min(left and top))    
                        dp[row][col] = 1 + Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));       
    
                    }
    
                    // Cells at left or top wall
                    else if(row == 0 || col == 0){
                        dp[row][col] = 1;
                    }
    
                    max = Math.max(max, dp[row][col]);
                }
            }      
    
    
            return max * max;  
            
        }  
   
}



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


// LC 9 https://leetcode.com/problems/palindrome-number

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
                        
        int t = x;     
        
        int newNumber = 0, rem = 0;
        
        while(x > 0){
            
            rem = x % 10;            
            newNumber = newNumber * 10 + rem;           
            x = x/10;    
        } 
        
        return (t - newNumber == 0)? true : false;
        
    }    

}



//https://leetcode.com/problems/implement-trie-prefix-tree/
// 208. Implement Trie (Prefix Tree)

// Simplified TrieNode using HashMap

class Trie {

    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() { 
        root = new TrieNode();        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        
        TrieNode cur = root;
        
        for(char ch : word.toCharArray()){
            
            cur.subNode.putIfAbsent(ch, new TrieNode());
            
            cur = cur.subNode.get(ch); // set new trie node as cur         
        }      
        
        cur.isComplete = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
                
        TrieNode cur = root;
        
        for(char c : word.toCharArray()){
            
            if(cur.subNode.get(c) == null) return false;            
            cur = cur.subNode.get(c); // go one level deeper            
        }        
               
        return cur.isComplete;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        
        TrieNode cur = root;
        
        for(char c : prefix.toCharArray()){
            
            if(cur.subNode.get(c) == null) return false;            
            cur = cur.subNode.get(c); // go one level deeper            
        }  
              
        return true;
    }
    
    
    
    static class TrieNode {
        
        Map<Character,TrieNode> subNode = new TreeMap<>();
        boolean isComplete;        
    }
    
        
    
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */