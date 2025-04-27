


// LC : 398 https://leetcode.com/problems/random-pick-index/

public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = (int) (Math.random() * numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
    }

}


// O(n)^2 time
// O(1) space
class SubArraySumEqualsKNonOptimized {

    public int subarraySum(int[] nums, int k) {
        
       int count = 0;
        
        for(int i = 0; i < nums.length; i++){
            
            int sum = 0;
            
            
            for(int j = i; j < nums.length; j++){
                
                sum += nums[j];
                
                if(sum == k){
                    //System.out.println(sum);
                    count++;
                    
                }             
            }
            
        }

        return count;
    }
}



// https://leetcode.com/problems/subarray-sum-equals-k/description/
// LC 560
class SubArraySumEqualsK {
    

    /*
    Logic - Store prefix sum at each index. 
    Now find if current prefixSum - K is present in the stored prefixSums.
    
    sum(nums[i,j] = prefixSum[j] -  prefixSum[i - 1])
    prefixSum[i - 1] = prefixSum[j] - k; 
  */

// Time and space : O(N)
  public int subarraySum(int[] nums, int k) {

    // PrefixSum - frequency
    Map<Integer, Integer> map = new HashMap<>();

    int prefixSum = 0, ans = 0;

    // imagine a prefix sum array. Starts with 0 outside.
    map.put(0,1); 

    for(int num : nums){

        prefixSum += num;

        // Since we have prefix sum, find if a number prefixSum - k exists, so it satisifies equation or find missing complementing the equation.

        ans += map.getOrDefault(prefixSum - k, 0);

        // update prefix sum count
        map.put(prefixSum, map.getOrDefault(prefixSum,0) + 1);
    }

    return ans;

  }

}





public class BasicCalculator2 {


    // LC 227 : https://leetcode.com/problems/basic-calculator-ii/

    public int calculate(String s) {
                
        int res = 0;

        int previousNum = 0;
        
        int number = 0;

        char operator = '+'; // prev operator

        
        for(int i = 0; i < s.length(); i++){
            
            Character ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
                number = (number * 10) + Character.getNumericValue(ch); 
            }           
           
            
            if(!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1){

               if(operator == '+') {
                       
                    res += previousNum;
                    previousNum = number;
                }

                else if(operator == '-') {
                    res += previousNum;
                    previousNum = - number;
                }         
                else if(operator == '/') {
                    previousNum /= number; 
                }

                else if(operator == '*'){
                    previousNum *= number;
                }                
                
                operator = ch; // Update 
                number = 0; // Reset               
            }
        }

        res += previousNum;

        return res;       

        
    }

}






/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();

        int result=0;
        int level = 1;

        for(NestedInteger i : nestedList){
           q.add(i);
        }

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
              NestedInteger item = q.poll();
              
              if(item.isInteger()){
                result+=(item.getInteger()*level);
              }   

              else{
                for(NestedInteger nei : item.getList()){
                    q.add(nei);
                }
              }

            }
            level++;
        }

        return result;
    }
}


// https://leetcode.com/problems/subarray-sum-equals-k/description/
// LC 560
class SubArraySumEqualsK {
    /*
        [1 2 3] k = 3
    
        Create map of prefix Sum at each index and its occurence. 
        Startign from 0,1 ->  Sum of 0 occurs one time
        // Why ? , When we check if (prefixSum - k) exists, example 3 - 3 exists, then we check if 0 exists
     
        Now similar to 2 sum, find if current prefixSum - K is present in the stored prefixSums.
        
        sum(nums[i,j] = prefixSum[j] -  prefixSum[i - 1])
        prefixSum[i - 1] = prefixSum[j] - k; 
    
       
      */
    
        // Time and space : O(N)
        public int subarraySum(int[] nums, int k) {
    
            int prefixSum = 0, count = 0;
    
            Map<Integer, Integer> prefixSumOccurence = new HashMap<>();
            prefixSumOccurence.put(0, 1); // Sum of 0 occurs 1 time
    
            for (int i = 0; i < nums.length; i++) {
    
                prefixSum += nums[i];
    
                System.out.println("prefix Sum : " + prefixSum + " map : " + prefixSumOccurence);
    
                if (prefixSumOccurence.containsKey(prefixSum - k)) {
    
                    count += prefixSumOccurence.get(prefixSum - k);
                }
    
                // Update map
                prefixSumOccurence.put(prefixSum, prefixSumOccurence.getOrDefault(prefixSum, 0) + 1);
            }
    
            return count;
    
        }
    
    
        // Time O(n)^2 Costant space
        public int subarraySumBrute(int[] nums, int k) {
    
            int count = 0;
    
            // Subarray starting from each number
            for (int i = 0; i < nums.length; i++) {
    
                int sum = 0;
    
                for (int j = i; j < nums.length; j++) {
    
                    sum += nums[j];
    
                    if (sum == k) count++;
                }
            }
    
            return count;
    
        }
    
    }
    
    public class NextPermutationLexi {
    
    
        /*
        Example : 1 3 5 4 2
        We have ones, tenth, hundreds and so on based on weights.
        
        1. To get next greater permutation, we need to identify an index from right (ones) to satisfy
        a[i] < a[i + 1] -> 3 < 5 . Why ? -> Replacing smaller number with higher weight with higher number with currently smaller weight.
        But, we also need the next greater permutation, not just any greater permutation, so need
        
        2 To find next greater number than num at index i from the right end that can effectively replace smaller num identified in step 1..
        a[j] > a[i]. -> 4 > 3
        So we swap and get 1 4 5 3 2.
        
        3. We can also observer, after index i, the numbers are in decreasing order -> 5 3 2.
        reverse them and we get 1 4 2 3 5.    
    
    */
    
        // LC 31. Next Permutation
    // LC https://leetcode.com/problems/next-permutation/
        public void nextPermutation(int[] nums) {
    
    
            // Find a number that satisfies a[i] < a[i + 1] - Basically find a smaller digit that can be  replaced with higher weight
            int i = 0;
    
            for (i = nums.length - 2; i >= 0; i--) {
    
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
    
    
            // Find a digit that is greater than at index i, to effectively replace smaller digit with larger digit
    
            if (i >= 0) {
    
                for (int j = nums.length - 1; j > i; j--) {
    
                    if (nums[j] > nums[i]) {
    
                        swap(i, j, nums);
                        break;
                    }
                }
    
    
            }
    
    
            // Swap nums after index i as it is in decreasing order
    
            int start = i + 1;
            int end = nums.length - 1;
    
            while (start < end) {
                swap(start, end, nums);
                start++;
                end--;
            }
    
        }
    
        private void swap(int i, int j, int[] nums) {
    
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
    
        }
    
    }
    








// LC  1570 : https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

// Optimized version.
class SparseVector {
    
    List<int[]> vectors;
    
    SparseVector(int[] nums) {
        vectors = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) vectors.add(new int[] {i, nums[i]});
        
        }
    
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        
       // two pointers
       int i = 0, j = 0, product = 0;

       while(i < vectors.size() && j < vec.vectors.size()){

            int[] vectors1 = vectors.get(i);
            int[] vectors2 = vec.vectors.get(j);


            // if index is the same
            if(vectors1[0] == vectors2[0]){

                product += vectors1[1] * vectors2[1];

                i++;
                j++;
            }

            else if (vectors1[0] < vectors2[0]){
                i++;
            }

            else j++;
       }

       return product;
                
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);



// Nopn optimized - Can lead to collision and other issues with hashmap when a large dataset is used.

class SparseVector2 {
    
    private Map<Integer, Integer> map;
    
    SparseVector2(int[] nums) {
        
        map = new HashMap<>();       
        
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) map.put(i, nums[i]);
            
        }
        
        
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector2 vec) {
        
        int dotProduct = 0;
        
        for(int key : map.keySet()){
            
            dotProduct += map.get(key) * vec.map.getOrDefault(key,0);            
            
        }
        
        
        return dotProduct;
    }
}






class Pow_x_n. {
   
    
    public double myPow(double x, int n) {
        
          
       if(n == 0) return 1.0;
        
       if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        return fastPower(x, n);    
    
    }
    
    
    public double fastPower(double x, int n){
        
       if(n == 0) return 1.0;
        
       double res = fastPower(x , n / 2); // calculate half the values
        
       if(n % 2 == 0){ // even
           
           res = res * res;
           
       } 
        
       else  {
           
           res = res * res * x;
       }
        
        return res;
        
    }
    
    
     /*public double myPow(double x, int n) {
        
        //brute force
         
       if(n == 0) return 1.0;
        
        if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        
        double res = 1;
        
        for(int i = 0; i < n; i++) {
            
            res = res * x;     
            
        }
        
        return res;
        
    }*/
    
    
    
        
        
}




public class FindBuildingsOceanView {

    // LC https://leetcode.com/problems/buildings-with-an-ocean-view       

   public int[] findBuildings(int[] heights) {

       List<Integer> res = new ArrayList<>();

       int maxSoFar = 0;

       if(heights == null || heights.length == 0) return new int[0];
       if(heights.length == 1) return new int[] {0};

       res.add(heights.length - 1);
       maxSoFar = heights[heights.length - 1];

       for(int i = heights.length - 2; i >= 0; i--){

           if(heights[i] > maxSoFar) {
               res.add(i);

               maxSoFar = Math.max(maxSoFar, heights[i]);
           }
       }

       // return results in list in reverse order
       int[] result = new int[res.size()];
       for(int i = 0; i < res.size(); i++) {
           result[i] = res.get(res.size() - 1 - i);
       }
       
       return result;
   }
   
}








public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        /*
           Straigntforward Merge from end of array in descending order.
         */
        
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;


        while(i >= 0 && j >= 0){
            
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }

            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        
        // while i >= 0 is not required as 

        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }        
       
    }
}





class CopyRandomPointer {
    public Node copyRandomList(Node head) {
        
        /*basically provide a deep copy, so 1) iterate and add nodes to map <node, new node > -- create a new list 
             using next pointer, */
        
        if(head == null) return null;
        
        Map<Node,Node> map = new HashMap<>();
        
        
        Node cur = head;
        
        /*iterate and copy the nodes into the map*/
        while(cur != null){            
            map.put(cur, new Node(cur.val));           
            cur = cur.next;            
        }
        
        /*iterate again and each (key)entry is a Node, which in turn contains next and random pointers, assign next and random pointers to the (value) Node*/
        
        cur = head; // re-using pointer
        
        while(cur != null){            
            
            Node n = map.get(cur);
            n.next = map.get(cur.next); // we cannot assign node.next directly as we need to link to cloned node, not original node
            n.random = map.get(cur.random);
                   
            cur = cur.next;
        }         
        
        return map.get(head);
    }
}


// LC : 398 https://leetcode.com/problems/random-pick-index/

public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = (int) (Math.random() * numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
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















public class ShortestPathBinaryMatrixAlt {


     static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;

        Queue<int[]> q = new LinkedListImplementation<>();
        q.add(new int[]{0,0});

        grid[0][0] = 1;

        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];

            //Check if last right cell
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return dist;
            }

            List<int[]> neighbors = getNeighborsin8D(n, grid);
                                   
            for(int[] neighbor : neighbors){

                int r = neighbor[0], c = neighbor[1];                
                grid[r][c] = dist + 1;
                q.add(neighbor);
            }
        }   

        return - 1;                          
    }

    private List<int[]> getNeighborsin8D(int[] node, int[][] grid){

            List<int[]> neighbors = new ArrayList<>();

            for(int[] dir : dirs){

                int[] newDir = new int[] { (dir[0] + node[0]), (dir[1] + node[1])};

                if(newDir[0] >= 0 && newDir[0] < grid.length && 
                        newDir[1] >= 0 && newDir[1] < grid[0].length &&
                        grid[newDir[0]][newDir[1]] == 0
                        ){

                    neighbors.add(newDir);            
                }
            }
            return neighbors;
    }


}







// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986


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
            */


public class IntervalListIntersections {

    // No priority queue needed as : description: Each list of intervals is pairwise disjoint and in sorted order

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

            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]);

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}



//

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
























