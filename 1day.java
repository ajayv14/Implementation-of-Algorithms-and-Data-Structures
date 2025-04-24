

class DFS {

    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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