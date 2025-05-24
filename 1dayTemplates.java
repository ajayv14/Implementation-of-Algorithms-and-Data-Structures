


// Time and space O(M x n)
class DFS {

    int[][] dirs = new int[][]{{-1, 0}, // up
                                {0, 1}, // right
                                 {-1, 0}, // down
                                  {0, -1}}; //left

    public int numIslands(char[][] grid) {

        int count = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                // Found an island    
                if (grid[i][j] == '1') {

                    count++;

                    // merge & expand
                    dfs(i, j, grid);


                }
            }
        }

        return count;
    }


    // Merge islands and replace the 1s to 0s to stop double counting
    public void dfs(int row, int col, char[][] grid) {

        // Boundary check + water
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0'; // mark it visited

        /*dfs(row + dirs[0][0], col + dirs[0][1], grid); // up

        dfs(row + dirs[1][0], col + dirs[1][1], grid); // right     

        dfs(row + dirs[2][0], col + dirs[2][1], grid); // down     

        dfs(row + dirs[3][0], col + dirs[3][1], grid); // left   */

        // or 

        for (int i = 0; i < dirs.length; i++) {

            dfs(row + dirs[i][0], col + dirs[i][1], grid);
        }

    }
}


class BFS {

    /* Approach - BFS - Guranteed shortest path in undirected graph
                
     DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
    */

    static final int[][] dirs = new int[][]{

            {-1, -1}, {-1, 0}, {-1, 1}, // Top row
            {0, -1}, {0, 1}, // Same row
            {1, -1}, {1, 0}, {1, 1} // Row below
    };

    public int shortestPathBinaryMatrix(int[][] grid) {


        //base - start and end cell should contain 0 
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;


        Queue<int[]> q = new LinkedList<>(); // bfs queue
        q.add(new int[]{0, 0});


        grid[0][0] = 1; // to calculate distance starting from 1 unit

        while (!q.isEmpty()) {

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];


            //Check if last right exit cell 
            // This check can also be done below the dirs loop, but will fail for input [[0]]
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return grid[row][col];
            }

            for (int[] dir : dirs) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Why not replace walls 1 with -1 ?? 
                //-> We will never go back to cell 0,0 which is '1' but not a wall, so can skip this modification. 

                // Why ignore grid[newRow][newCol] > 0 ??  
                // -> 1 - wall. Any other positive number is already a shorter path in progress

                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                        || grid[newRow][newCol] > 0) {

                    continue;
                }

                q.add(new int[]{newRow, newCol});

                grid[newRow][newCol] = dist + 1;



                //System.out.println(dist + 1);               

            }
        }

        return -1;
    }


}


class UnionFindExample {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // credits : ems/accounts-merge/solutions/1601980/java-solution-using-unionfind-beats-99-87-of-submissions/
    
            /*    
            ["John","johnsmith@mail.com","john_newyork@mail.com"],
            ["John","johnsmith@mail.com","john00@mail.com"],
            ["Mary","mary@mail.com"]
            ["John","johnnybravo@mail.com"]]
            */


        // result
        List<List<String>> merged = new ArrayList<>();

        UnionFind uf = new UnionFind(accounts.size());

        // Build emailId, index map to utilize union find to merge accounts
        // ajay@leetcode.com - 0

        // email,email id index
        Map<String, Integer> emailMap = new HashMap<>();


        for (int idx = 0; idx < accounts.size(); idx++) {

            List<String> emailList = accounts.get(idx);


            // Skip i = 0, the name without email 
            for (int i = 1; i < emailList.size(); i++) {

                String email = emailList.get(i);

                if (emailMap.containsKey(email)) {

                    // merge email accounts
                    uf.union(idx, emailMap.get(email));

                } else emailMap.put(email, idx); // Add row id as value for each email               

            }
        }

        // Case : 2 lists have same email and their idx have been merged above, but what about other emails in list 2  not present in list 1 ??
        // Merge them below
        // 0 - ajay@leet.com,ajay@gmail.com,vijay@gmail.com,sanjay@gmail.com
        Map<Integer, List<String>> idxToEmails = new HashMap<>();


        for (String emailKey : emailMap.keySet()) {

            int index = emailMap.get(emailKey);

            int root = uf.find(index);

            idxToEmails.putIfAbsent(root, new ArrayList<>());
            idxToEmails.get(root).add(emailKey);

            // Say sanjay@gmail.com has an index 2, ajay@gmail.com had an index 2 and also index 1 but got merged with index 1.
            // Now at this step, we find parent of index 2, which is 1, hence merge sanjay@gmail.com also to list 1.
        }


        // Build final result

        for (int indexKey : idxToEmails.keySet()) {


            // Get name 
            String name = accounts.get(indexKey).get(0);


            // Add name top each of mergedList
            List<String> mergedEmailList = idxToEmails.get(indexKey);

            // Need to be sorted - requirement           
            Collections.sort(mergedEmailList);
            mergedEmailList.addFirst(name);

            merged.add(mergedEmailList);
        }

        return merged;
    }


    class UnionFind {

        int parent[];
        int rank[];


        public UnionFind(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {

                parent[i] = i;
            }

        }

        public boolean union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);

            // can't merge. These two are already merged
            if (rootX == rootY) return false;

            else if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;


            else if (rank[rootY] < rank[rootX]) parent[rootY] = rootX;


            else if (rank[rootY] == rank[rootX]) {

                parent[rootY] = rootX;
                rank[rootY]++;
            }
            return true; //Confirm merge
        }

        public int find(int x) {

            if (parent[x] != x) parent[x] = find(parent[x]);

            return parent[x];
        }

    }
}


class MergeSort {

    public int[] sortArray(int[] nums) {
        return mergeSort(nums);
    }

    // Time O(n log n ) all cases, space O(n)
    private int[] mergeSort(int[] nums) {

        int n = nums.length;

        if (n < 2) return nums;

        int mid = n / 2;

        // Split the array
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        // populate arrays
        for (int i = 0; i < mid; i++) {

            left[i] = nums[i];
        }

        for (int i = mid; i < n; i++) {

            right[i - mid] = nums[i];
        }

        // Recursively split arrays till it reaches single cell value

        mergeSort(left);
        mergeSort(right);

        // Start merging in sorted order

        merge(left, right, nums);

        return nums;
    }


    private int[] merge(int[] left, int[] right, int[] nums) {

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {

            if (left[i] <= right[j]) {

                nums[k] = left[i];
                i++;
            } else {
                nums[k] = right[j];
                j++;
            }
            k++;
        }

        // Some elements remainint in left
        while (i < left.length) {

            nums[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {

            nums[k] = right[j];
            j++;
            k++;
        }

        return nums;
    }
}
    

class Heapify {
    
    /*logic : 1) Use heapify to build a max-heap--> max heap does not give sorted output, just satisfies property-- root(parent) > left & right child.
    
    2) To overcome this, we swap last element and first element in array (largest element at index 0 goes to end). Now we need to exclude this from the heap, hence we use the end variable to limit the length of array. Finally we heapify again from 0 - end
    (excluding the last element iteratively)
    
    exceptional cases: 
    [5,1,1,2,0,0]
    */
    
    
        public int[] sortArray(int[] nums) {
    
    
            int mid = nums.length / 2;
    
            // to build heap
            for (int i = mid; i >= 0; i--) {
                heapify(nums, i, nums.length);
            }
    
            // to sort data in ascending order
    
            for (int i = nums.length - 1; i >= 0; i--) {
    
                swap(nums, 0, i);
                heapify(nums, 0, i);
            }
    
            return nums;
        }
    
        private void heapify(int[] arr, int i, int end) {
    
            int max = i;
    
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
    
            if (leftChild < end && arr[leftChild] > arr[max]) {
                max = leftChild;
            }
    
            if (rightChild < end && arr[rightChild] > arr[max]) {
                max = rightChild;
            }
    
            if (max != i) {
                swap(arr, max, i);
                heapify(arr, max, end);
            }
    
        }
    
    
        private void swap(int[] arr, int i, int j) {
    
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
    
        }
    
    }


class QuickSort {

    public int[] sortArray(int[] nums) {
        return quicksort(nums, 0, nums.length - 1);
    }


    // Quick sort 

    // Partition the array at pivot such than left of pivot is lesser than pivot and right of pivot is greater tha pivot.
    // pivot index is in perfectly sorted and in place, so 
    // Do this recursively to remaining left and right part of array.

    private int[] quicksort(int[] nums, int left, int right) {

        if (left < right) {

            int partitionIdx = partition(nums, left, right);

            // Now since partition idx is sorted and in correct place
            // recursively partiton rest of array before and after partition index
            quicksort(nums, left, partitionIdx - 1); // before p idx
            quicksort(nums, partitionIdx + 1, right);
        }

        return nums;

    }

    private int partition(int[] nums, int left, int right) {

        int pivotValue = nums[right];

        int i = left - 1; // one step behind left
        int j = left; // Just for understanding purpose

        //partitionVal acts as a reference point
        for (j = left; j < right; j++) {

            // Find a num lesser than pivot, swap with i    
            if (nums[j] < pivotValue) {

                i++; // 

                swap(nums, i, j);
            }
        }

        // Swap i + 1 with pivot
        swap(nums, i + 1, right);

        return i + 1;
    }

    private void swap(int nums[], int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
        

class QuickSelect {

    /*
      Approach - Use quick select - modified quick sort algo    
    */

    Map<Integer, Integer> freq;

    // Using quick sort pivot approach 
    public int[] topKFrequent(int[] nums, int k) {

        // Store frequencies : <number, freq Of occurence>              
        freq = new HashMap<>();

        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);


        // Basically each unique number will be in the unique array. Corresponding freq in map
        // We can use freq to partially sort them in place                 
        int[] unique = new int[freq.size()];
        int n = 0;
        for (int key : freq.keySet()) {
            unique[n++] = key;
        }

        // Perform quick select till we match pivot index with kth largest from right end 
        quickselect(unique, 0, unique.length - 1, unique.length - k);

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
    private void quickselect(int[] nums, int left, int right, int topK) {

        if (left < right) {

            // Perform partition and update new  pivot position.
            int pivotIndex = partition(nums, left, right);

            // Adjust pivot index appropriately
            if (topK == pivotIndex) return;

            else if (topK < pivotIndex) quickselect(nums, left, pivotIndex - 1, topK);

            else quickselect(nums, pivotIndex + 1, right, topK);

        }

    }

    // Apply quicksort partition logic 
    //  nums = [4 1 4 2 1 3 4 3 5] -> freq map 4:3 1:2 2:1 3:2 5:1    
    private int partition(int[] nums, int left, int right) {

        // pivot value    
        int pivot = freq.get(nums[right]);

        int i = left - 1; // A step lagging left
        int j = left;

        // Rearrange array by comparing with pivot value -  freq of occurence
        for (j = left; j < right; j++) {

            if (freq.get(nums[j]) < pivot) {

                i++; // Note !
                swap(i, j, nums);

            }
        }

        swap(i + 1, right, nums);

        return i + 1;
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}


class TopologicalSort {


    // Kahn's Topological sorting

    public int[] findOrder(int numCourses, int[][] prerequisites) {


        int[] result = new int[numCourses];
        int idx = 0; // track result

        int[] indegree = new int[numCourses];  // number of incoming edges

        //<vertex, neighbors>
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Construct graph
        for (int[] pre : prerequisites) {

            graph.putIfAbsent(pre[1], new ArrayList<>());
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        //System.out.println(graph);

        Queue<Integer> q = new LinkedList<>();

        // Pick the ones that have zero indegree and add to queue

        for (int i = 0; i < indegree.length; i++) {

            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {

            int node = q.remove();
            result[idx++] = node; // 0 indegree

            // get neighbors
            List<Integer> neighbors = graph.get(node);

            // Skip nodes with no neighbors
            if (neighbors == null) continue;

            for (int neighbor : neighbors) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) q.add(neighbor);
            }

        }

        // Check cyclical dependency 
        return numCourses == idx ? result : new int[0];


    }


}


class BinarySearch {


    public int search(int[] arr, int key) {

        int start = 0, end = arr.length - 1;

        while (start <= end) {

            int midIndex = start + (end - start) / 2; // to prevent overflow


            // search on left half
            if (key < arr[midIndex]) {
                end = midIndex - 1;
            }

            // search in right half
            else if (key > arr[midIndex]) {
                start = midIndex + 1;
            } else if (key == arr[midIndex]) return midIndex;

        }

        return -1;
    }
}



// LC 153 Find Minimum in Rotated Sorted Array
//BinarySearch2
public class FindMInRotatedSortedArr {

    // Using minimization template


    public int findMin(int[] nums) {


        int low = -1, high = nums.length - 1;

        while (low + 1 < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] <= nums[nums.length - 1]) high = mid;

            else low = mid;

        }

        return nums[high];

    }


}


//BinarySearch3
public class CuttingRibbons {

    // LC 

    // Binary search optimized 
    public int maxLength(int[] ribbons, int k) {

        // k is not the length, but number of ribbons   

        // min len is 1, so left boundary is 1, right boundary ?? below
        int max = 0;

        for (int i = 0; i < ribbons.length; i++) {

            max = Math.max(max, ribbons[i]);
        }

        // Now length of ribbon is btw 1 and max. We optimize this value by binary search.

        // Binary search
        // Maximization problem
        // min length = 1 and max len = max
        // invalid range to begin with 
        // in maximization problem return lo       
        int low = 0, high = max + 1;

        while (low + 1 < high) {

            int mid = low + (high - low) / 2;

            boolean isPossible = isMaxLenPossible(ribbons, mid, k);

            if (isPossible) low = mid;

            else high = mid;
        }

        return low;
    }

    private boolean isMaxLenPossible(int[] ribbons, int len, int k) {

        int sum = 0;

        for (int r = 0; r < ribbons.length; r++) {

            sum += Math.floor(ribbons[r] / len);
        }

        return sum >= k ? true : false;

    }


    // Brute force  
    public int maxLength2(int[] ribbons, int k) {

        // k is not the length, byt number of ribbons   


        // min len is 1, so left boundary is 1, right boundary ?? below
        int max = 0;

        for (int i = 0; i < ribbons.length; i++) {

            max = Math.max(max, ribbons[i]);
        }

        // length of ribbons possible
        for (int l = max; l > 0; l--) {

            int sum = 0;

            for (int j = 0; j < ribbons.length; j++) {

                sum += Math.floor(ribbons[j] / l);
            }

            if (sum >= k) return l;

        }

        return 0;

    }
}


// LC 34 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// BinarySearch4
class FindFirstAndLast {

    /**
     * Use binary search minimization and maximization template.
     */
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];

        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target);

        return res;

    }

    private int findFirst(int[] nums, int target) {

        int low = -1;
        int high = nums.length;

        while (low + 1 < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) high = mid;

            else low = mid;
        }

        if (high == nums.length || nums[high] != target) return -1;

        return high;
    }

    private int findLast(int[] nums, int target) {

        int low = -1;
        int high = nums.length;

        while (low + 1 < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] <= target) low = mid;

            else high = mid;
        }

        if (low == -1 || nums[low] != target) return -1;

        return low;

    }

}



class MonotonicStack1 {

    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {

            boolean destroyed = false;

            // +ve spin meets negative spin
            while (!stack.isEmpty() && (stack.peek() > 0 && asteroids[i] < 0)) {

                // Note : After exploding the smaller one, Surviving asteroid doesn't lose weight

                // stack top is smaller, hence will explode and put the next asterioid in line

                if (Math.abs(stack.peek()) < Math.abs(asteroids[i])) {
                    stack.pop();
                    continue;
                }

                // stack top is equal to aster. Both will explods
                else if (Math.abs(stack.peek()) == Math.abs(asteroids[i])) {
                    stack.pop();
                }

                destroyed = true;
                break;


            }

            if (!destroyed) stack.push(asteroids[i]);
        }


        // Build result from stack
        int res[] = new int[stack.size()];
        int k = res.length - 1;
        while (!stack.isEmpty()) {

            res[k--] = stack.pop();
        }

        return res;
    }
}


public class MonotonicStack2 {
  


    // Monotonic stack 
    public int[] dailyTemperatures(int[] temperatures) {

        int[] res = new int[temperatures.length];

        Stack<Integer> stack = new Stack<>();


        for (int i = 0; i < temperatures.length; i++) {

            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {

                int idx = stack.pop();

                res[idx] = i - idx;
            }

            stack.push(i);

        }

        return res;

    }

}



//LC 102

//Binary Tree In order traversal
class KthSmallestElementInBST {


    //in-order traversal recursion

    public int kthSmallest(TreeNode root, int k) {

        if (root == null) return 0;

        List<Integer> list = new ArrayList<>();

        inOrder(root, list);

        return list.get(k - 1);
    }


    public void inOrder(TreeNode root, List<Integer> list) {

        if (root == null) return;

        inOrder(root.left, list);

        list.add(root.val);

        inOrder(root.right, list);


    }


    //in-order iterative

    public int kthSmallest(TreeNode root, int k) {

        if (root == null) return -1;

        Stack<TreeNode> stack = new Stack<>();


        while (!stack.isEmpty() || root != null) {

            while (root != null) {

                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            --k;
            if (k == 0) return root.val;
            root = root.right;

        }
        return -1;
    }


}


public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> mainList = new ArrayList<List<Integer>>();

        if (root == null) return mainList;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root); // Make sure atleast one node is in queue

        while (!queue.isEmpty()) {

            int level = queue.size();

            List<Integer> levelNodes = new ArrayList<Integer>(); // To hold Nodes in each level

            for (int i = 0; i < level; i++) {

                TreeNode node = queue.remove(); // remove each node from Queue

                levelNodes.add(node.val);

                // Enqueue its children
                if (node.left != null)
                    queue.add(node.left);

                if (node.right != null)
                    queue.add(node.right);

            }

            mainList.add(levelNodes);
        }

        return mainList;

    }
}


// Binary tree Post Order Traversal
public class CountNodesEqualToAvgOfSubtree {


    int count = 0;

    public int averageOfSubtree(TreeNode root) {

        postOrderTraversal(root);

        return count;

    }


    private int[] postOrderTraversal(TreeNode root) {

        if (root == null) return new int[]{0, 0};

        int[] left = new int[]{0, 0};
        int[] right = new int[]{0, 0};

        if (root.left != null) {
            left = postOrderTraversal(root.left);
        }

        if (root.right != null) {
            right = postOrderTraversal(root.right);
        }

        int nodeValSum = left[0] + right[0] + root.val;
        int numOfNodes = left[1] + right[1] + 1;

        if (root.val == (nodeValSum / numOfNodes)) count++;

        return new int[]{nodeValSum, numOfNodes};

    }

}

//A key difference between the two is that permutations consider the order of arrangement, while combinations do not.
//For example, if we have a set of three letters {A, B, C}, 
//the permutations would be ABC, ACB, BAC, BCA, CAB, and CBA,
// whereas the combinations would be AB, AC, and BC.

class Permutations {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permutations(result, new ArrayList<>(), nums);
        return result;
    }

    /*backtracking helper function*/
    public void permutations(List<List<Integer>> result, List<Integer> list, int[] nums) {

        /*make sure the temp list has equal elements that of original array (coz permutations)*/

        if (list.size() == nums.length) result.add(new ArrayList<>(list));

        else {
            for (int i = 0; i < nums.length; i++) {

                /* skip element if already present */
                if (list.contains(nums[i])) continue;

                else {
                    list.add(nums[i]);
                    permutations(result, list, nums);
                    list.remove(list.size() - 1);
                }
            }
        }
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


class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combinations(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    public void combinations(List<List<Integer>> res, List<Integer> list, int start, int end, int k) {

        if (list.size() == k)
            res.add(new ArrayList<Integer>(list)); // limit the combinations to size of k, add the list to main list

        else {

            for (int i = start; i <= end; i++) {
                if (list.contains(i)) return;

                else {
                    list.add(i);
                    combinations(res, list, i + 1, end, k); //note its i + 1 and not start + 1
                    list.remove(list.size() - 1); // remove the last element of list after recursive call                    
                }
            }
        }
    }
}    


class SubSets {

    public List<List<Integer>> subsets(int[] nums) {

        /*Final List to be returned*/

        List<List<Integer>> MainList = new ArrayList<>();
        //Arrays.sort(nums); not necessary, if order of subsets is not important

        /*backtracking helper function*/

        backtracking(MainList, nums, new ArrayList<>(), 0); /*with start index 0*/

        return MainList;

    }


    public void backtracking(List<List<Integer>> MainList, int[] nums, List<Integer> tempList, int start) {

        MainList.add(new ArrayList<>(tempList)); /*initially add empty list*/

        /*run from start to nums.length - 1*/
        for (int i = start; i < nums.length; i++) {

            tempList.add(nums[i]);
            backtracking(MainList, nums, tempList, i + 1);
            tempList.remove(tempList.size() - 1); /*remove last element from list*/

        }
    }
}

public class MergeIntervals {

    /*Solve using Priority Queue*/

    public int[][] merge(int[][] intervals) {

        // To store intermediate resut as a list instead of int[][] array
        List<int[]> res = new ArrayList<>();

        // Sorted order based on start time - As opposed to sorting the array in place
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[0] - n2[0]);


        for (int[] interval : intervals) {
            pq.add(interval);
        }

        while (pq.size() > 1) {

            int[] n1 = pq.remove();
            int[] n2 = pq.remove();

            // Merge condition   
            if (n2[0] <= n1[1]) {

                int end = n1[1] > n2[1] ? n1[1] : n2[1];

                pq.add(new int[]{n1[0], end}); // Start is n1[0] as we know it is sorted by start time in PQ
            } else {
                res.add(n1);
                pq.add(n2);
            }
        }

        if (!pq.isEmpty()) {

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

    public int[][] mergeWithSort(int[][] intervals) {

        /*logic - sort the intervals with the start time value, then if end index of current >= start index of next, merge*/


        if (intervals.length <= 1) return intervals;


        // list of array to hold result (easy to manipulate)
        List<int[]> res = new ArrayList<>();


        /* sort based on the interval start value*/

        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));


        int[] prev = intervals[0]; // first set of value
        res.add(prev);

        for (int[] interval : intervals) {

            int prevStart = prev[0];
            int prevEnd = prev[1];

            int currentStart = interval[0];
            int currentEnd = interval[1];

            if (prevEnd >= currentStart) {   // condition to merge

                prev[1] = Math.max(prevEnd, currentEnd);  // end value of merged set

            } else {

                /*update prev set value*/
                prev = interval;  // set prev value to current
                res.add(prev); // to continue the cycle
            }

        }

        return res.toArray(new int[res.size()][]);
    }


    /*test*/
    public static void main(String args[]) {

        //input
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}}; // o/p [[1,6],[8,10],[15,18]]


        MergeIntervals obj = new MergeIntervals();
        int[][] result = obj.merge(intervals);

        /*print output*/
        for (int[] res : result) {
            System.out.println("[" + res[0] + ", " + res[1] + "]");
        }

    }

}


// https://leetcode.com/problems/car-pooling/
class Carpooling {


    /**
     * Problem is similar to meeting rooms/ merge interval.

     Instead of sorting by start time, here a 'ledger' approach is used which is almost similar but need to only return a boolean. 

    
     */
    public boolean carPooling(int[][] trips, int capacity) {

        // time, num of people 
        // Need to use treemap as we require map to be ordered by time 
        Map<Integer,Integer> timeStamp = new TreeMap<>(); 

        for(int[] trip : trips){

            // update passenger count at start and end

           timeStamp.put(trip[1], timeStamp.getOrDefault(trip[1], 0) + trip[0]);
            timeStamp.put(trip[2], timeStamp.getOrDefault(trip[2], 0) - trip[0]);  
        }

        System.out.println(timeStamp);

        int totalUsedCapacity = 0;

        for(int people : timeStamp.values()){

             totalUsedCapacity += people;

            if(totalUsedCapacity > capacity) return false;
        }
    
        return true;        
        
    }
}


class SpiralMatrix1 {
    
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();     

        int row = 0, col = 0, rowEnd = matrix.length - 1, colEnd = matrix[0].length - 1;

        
        while(row <= rowEnd && col <= colEnd){

            // Traverse from left to right
            for(int c = col ; c <= colEnd; c++){
                res.add(matrix[row][c]);            
            }

            row++; // Prevent reading the last cell in row while traversing downwards (top right)

            // Traverse from top to bottom
            for(int r = row; r <= rowEnd; r++){
                res.add(matrix[r][colEnd]);                
            } 

            colEnd--; // Prevent reading the last cell in col while traversing row (bottom right)          

            // Traverse from right to left
            
            if(row <= rowEnd){
                for(int c = colEnd; c >= col; c--){
                    res.add(matrix[rowEnd][c]);      
                }               
            }            
            rowEnd--;

            if(col <= colEnd){

                // Traverse from bottom to top    
                for(int r = rowEnd; r >= row; r--){
                    res.add(matrix[r][col]);    
                } 
            }             

            col++;
        }
        
        return res;
        
    }
}