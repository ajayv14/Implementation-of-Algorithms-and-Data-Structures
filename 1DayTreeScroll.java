


/**
 * 
    class Pair<K,V> {
        
        K key;
        V value;

        public Pair(){                     
        }

        public Pair(K k, V v){
            this.key = k;
            this.value = v;            
        }
    }
 * 
 * 
 * 
 */
/**
 * Approach :Find max length path(max depth) on root.left, find max length path on root.right, add both
 * Root is not included in max left and right depth = dia 
 * <p>
 * 1                     longest on left - 1-2-4 or 1-2-5  = 2
 * / \                    longest on right - 1 - 3  = 1
 * 2   3
 * / \
 * 4   5
 */
//Return the longer one of leftPath and rightPath. Remember to add 1 as the edge connecting it with its parent.


//LC 543 https://leetcode.com/problems/diameter-of-binary-tree/  

// Time - O(N), Space O(H)

public class DiameterOfTree {

    int maxDiameter = 0; // maxDiameter at each node - max dia is usually between two leaves via root.

    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);
        return maxDiameter;
    }

    public int dfs(TreeNode root) {

        if (root == null) return 0;


        int maxDepthLeft = dfs(root.left);
        int maxDepthRight = dfs(root.right);

        maxDiameter = Math.max(maxDiameter, (maxDepthLeft + maxDepthRight));

        return 1 + Math.max(maxDepthLeft, maxDepthRight);
    }
}



// LC 314. Binary Tree Vertical Order Traversal
// https://leetcode.com/problems/binary-tree-vertical-order-traversal/

class VerticalOrderTraversal {


    // Time O(n log n ) Space O(n)
    // n log n as we maintain sorted order in tree map

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;

        // Level order traversal - colIndex, Node. : 
        //(colIndex computed by : root colINdex is 0, left child = colIndex - 1, rigth child = colIndex + 1)

        Queue<Pair<Integer, TreeNode>> q = new LinkedList<>();

        // TreeMap <Column Index, node values> // TreeMap tohave column index sorted
        Map<Integer, List<Integer>> colMap = new TreeMap<>();

        q.add(new Pair(0, root));

        while (!q.isEmpty()) {

            Pair<Integer, TreeNode> p = q.remove();

            int col = p.getKey();
            TreeNode n = p.getValue();

            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).add(n.val);

            if (n.left != null) q.add(new Pair(col - 1, n.left));

            if (n.right != null) q.add(new Pair(col + 1, n.right));
        }

        // Now convert TreeMap to result 
        for (Integer col : colMap.keySet()) {

            res.add(colMap.get(col));
        }

        return res;

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
        Queue<Triplet<Integer,Integer,TreeNode>> q = new LinkedList<>();

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



// Time O(n)
// Space O(n)
// Approach : If we find both p & q in same subtree, the parent is the LCA. Until then dfs.
class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q)
            return root; // if root is either p or q, by default it is common ancestor

        TreeNode left = lowestCommonAncestor(root.left, p, q); // find either p or q in root.left
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root; // both not null, then common ancestor is found, else return non null val to parent

        return left != null ? left : right;

    }


}

// Time O(n)
// Space O(n)

// If either node p or q does not exist in the tree, return null
class LowestCommonAncestor2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // TO get LCA    
        TreeNode node = LCA(root,p,q);

        // Verify if other node exists when LCA is the node itself        
    
        if(node == p) return dfs(node, q) ? node : null;

        else if(node == q) return dfs(node, p) ? node : null;

        return node;

    }



    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
       
        if(root == null || root == p || root == q) return root;

        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);

        if(left!= null & right != null) return root;    

        return left != null ? left : right;   
        
    }

    private boolean dfs(TreeNode root, TreeNode target){

        if(root == null) return false;

        else if(root == target) return true;

        return dfs(root.left, target) ||  dfs(root.right, target);        
    }
}



// LC 1650. Lowest Common Ancestor of a Binary Tree III
// use dummy pointers to move up 

// Time : O(H), height of the tree.
// Space : O(1)
// Root not provided

// Node -> parent pointer is provided
public class LowestCommonAncestor3 {

    // Silimar to finding linked list intersection. Intutive algo
    // We don't have root node, we got to find LCA of two nodes
    public Node lowestCommonAncestor(Node p, Node q) {

        Node n = p;
        Node m = q;

        while (n != m) {

            // Move node to parent, else we have reached root, so let it stay the same;
            n = n.parent == null ? p : n.parent;
            m = m.parent == null ? q : m.parent;

        }
        return n;

    }

}


// LC 1676 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
// Almost same as LCA 1
// Time O(V x N) space O(H)
// Use a set - Time and space O (N + K)
public class LowestCommonAncestor4 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {

        if (root == null) return root;

        // Same as LCA 1, but check for all nodes in array instead of just p & q
        for (TreeNode node : nodes) {

            if (node == root) return node;
        }

        // recursive call
        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);

        if (left != null && right != null) return root;

        return (left != null) ? left : right;
    }

}




class LowestCommonAncestorBST {

    
    /*
        Using BST property, we can ignore left or right subtree based on p and q values;

        if p and q are > root, then consider only right , p and q < root : consider left subtree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
   
        if(root == null || root == p || root == q) return root;
       
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor( root.left,  p,  q); 
        }

        else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor( root.right,  p,  q); 
        }          

       // p and q are on different sides of root, or one of them is root 
       else return root; // Found LCA
               
    }

}


// LC 199 : https://leetcode.com/problems/binary-tree-right-side-view/

// Time O(N) Space O(D) in avg and O(N) in worst case

public class BinaryTreeRightSideView {


    // Level order traversal, BFS, but pick the rightmost node at each level. 

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> ll = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();

        if (root == null) return ll;
        q.add(root);


        while (!q.isEmpty()) {

            int level = q.size();


            for (int i = 0; i < level; i++) {
                TreeNode n = q.remove();

                // Pick the last element in each level -> i = level - 1
                if (i == level - 1) {// to get the rightmost element in the level
                    ll.add(n.val);
                }

                if (n.left != null) {
                    q.add(n.left);
                }

                if (n.right != null) {
                    q.add(n.right);
                }

            }
        }

        return ll;
    }

}


/** Do a pre order traversal from root to leaf. Each iteration, add to a temp string
     When leaf node is reached, convert binary string into decimal and add to the sum.
    
 **/


 // https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

 // sum of all paths of binary nums root to leaf
 class SumRootToLeafBinaryNumbers {      
         
    int sum = 0;
    
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;
        dfs(root,"");  
        return sum;
    }
    
    
    private void dfs(TreeNode root, String temp){
        
        if(root == null) return;
        
        temp += root.val;
        
        if(root.left == null && root.right == null){
            // leaf node reached, convert temp binary string to int
            //sum += binaryToDecimal(temp); 
            sum += Integer.parseInt(temp,2);
        } 
        
        helper(root.left, temp);
        helper(root.right, temp);    
    }
    
    /* private int binaryToDecimal(String binary){
        
        int decimal = 0;
        int factor = 1;        
                       
        for(int i = binary.length() - 1; i >= 0; i--){
            decimal += factor * Integer.parseInt(String.valueOf(binary.charAt(i))); 
            factor *= 2;    
        }
        
        return decimal;
    }*/
        
}


// https://leetcode.com/problems/range-sum-of-bst/

// Time O(N), space dfs O(N)
class RangeSumOfBST {

    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {

        sumDFSOptimized(root, low, high);
        return sum;
    }

    // Non-optimized - checks all nodes and compares against low and high    
    private void sumDFS(TreeNode root, int low, int high) {

        if (root == null) return;

        if (root.val >= low && root.val <= high) {

            System.out.println("sum : " + sum);
            sum += root.val;
        }

        sumDFS(root.left, low, high);
        sumDFS(root.right, low, high);
    }

    // Optimized uisng BST property
    private void sumDFSOptimized(TreeNode root, int low, int high) {

        if (root == null) return;

        if (root.val >= low && root.val <= high) {

            //System.out.println("sum : " + sum);
            sum += root.val;
        }

        // Using BST property 
        if (root.val > low) {
            sumDFS(root.left, low, high);
        }

        if (root.val < high) {
            sumDFS(root.right, low, high);
        }
    }


    // using level order -
    public int rangeSumBSTLevelOrder(TreeNode root, int low, int high) {


        int sum = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);


        while (!q.isEmpty()) {

            int len = q.size();

            for (int i = 0; i < len; i++) {

                TreeNode node = q.poll();

                if (node.val >= low && node.val <= high) sum += node.val;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);

            }

        }

        return sum;

    }
}



//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
// Similar to BFS in matrix in all directions
// Time O(N), Space O(N)
class AllNodesDistanceKBinaryTree {
    
    /*Logic : Something like level order traversal, but we need to include all nodes at distance from 0 - K, one level at a time.
               Including the parents. So we need a Map to store parent of each node and also a Set to store "seen" nodes to prevent overlap in                     counting nodes. 
    */    
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
       
        // to store & return result
        List<Integer> res = new ArrayList<>();
         
        // bfs queue
        Queue<TreeNode> queue = new LinkedList<>();
        
        //<child, parent>
        Map<TreeNode, TreeNode> parentMap = new HashMap<>(); // parent of each node // pay attention
        
        Set<TreeNode> visited = new HashSet<>(); // seen nodes        // Pay attention
        
        // populate parents fopr each node
        traverse(parentMap, root); // to find and store <k,v> -> <node, parent node>
                
        // begin adding target node to the queue
        queue.offer(target);               
        
        /*similar to level order traversal*/
        while(K > 0 && !queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++ ){
                
                TreeNode node = queue.poll();
                                                
                if(!set.contains(node)){
                  set.add(node); // mark as seen  
                } 
                
                // add children and parent to queue
                if(node.left != null && !visited.contains(node.left)) queue.offer(node.left);
                
                if(node.right != null && !visited.contains(node.right)) queue.offer(node.right);
                
                // upload parent in queue, if node-parent maping exists and parent is not visited
                if(parentMap.containsKey(node) && !visited.contains(parentMap.get(node))) queue.offer(parentMap.get(node));                
            }       
            
            // Adding neighbore in concentric circles around a node at dist + 1 each time
            K--;
        }        
                                   
        while(!queue.isEmpty()){           // nodes at distance k
            res.add(queue.poll().val); // pay attention
        }
        
        return res;
    }
    
    
    private void traverse(Map<TreeNode,TreeNode> map, TreeNode root){
        
        if(root == null) return;
        
        if(root.left!= null){
            map.put(root.left,root);
            traverse(map, root.left);            
        }
        
        if(root.right != null){
            map.put(root.right, root);
            traverse(map, root.right);            
        }        
        
    }
}



/*
Approach 
- If the node has a right child, and its successor is somewhere lower in the tree. Go to the right once and then as many times to the left as you can.
 Return the node you end up with.

- Node has no right child, and hence its successor is somewhere upper in the tree. Go up till the node that is left child of its parent. The answer is the parent.
The successor is an ancestor of root that is a left child of its parent

Look at editorial for example

 */

 //  Direct access to the node but not to the root of the tree
 class InOrderSuccessorBST2 {

    // Find next largest in sorted nodes ?
    public Node inorderSuccessor(Node node) {

        if(node.right != null){

            node = node.right;
            
            while(node.left != null){
                node = node.left;
            }
            return node;
        }

        // no right child
        // the successor must be an ancestor.
        else {  

            //  Continue moving up until you find a node that is the left child of its parent
            // Confirm if root has  a parent and current root is right child of parent
            // Return the parent who's left child is this current root; ()
            while(node.parent != null && node == node.parent.right){
                node = node.parent;
            }
            return node.parent;// Loop breaks and node is the left child of a parent, so return parent  

        }                

        
    }
}




// LC 270 : https://leetcode.com/problems/closest-binary-search-tree-value


/*
Simple approach:
From tree root, build inorder traversaval arr - > sorted order
-> 1 2 3 4 5. Now target is 3.7. 
Is it closer to node 3 or node 4 -> abs(3 - 3.7) = 0.7 and abs (3.7 - 4) 0.3, hence closer to 4. 

* */




//Apply binary search to arrive at target node.

/**
 * 
 * Time complexity:
Best O(log n) - Balanced tree
Skewed tree - O(n)

Space complexity:
Best O(log n) - Balanced tree
Skewed tree - O(n)
 */
 
public class ClosestBSTValue {


    public int closestValue(TreeNode root, double target) {
                 

        // closest is same as root.va, but updated to left closer
        return dfs(root, target, root.val);
        
    }


    private int dfs(TreeNode root, double target, int closest){
        
        if(root == null) return closest;

        // Lets say we have node & closest node 3 and 4 and target is 3.2, 3.70 or 3.5.
        // 3.78 - closer to 4, 3.2 - closer to 3, 3.5 - in between, then pick 3.

        double distFromRoot = Math.abs(root.val - target);
        double distFromClosest = Math.abs(closest - target);

        // distFromRoot <  distFromClosest - Chose target as closest node
        // distFromClosest ==  distFromClosest, pick the smaller btw current node and closest so far.

        // Update closest so far
        if(distFromRoot <  distFromClosest || 
        distFromRoot == distFromClosest && root.val < closest) { // tie breaker

            closest = root.val;
        }

        // else closest remains closest so far
        
        // Binary search property
        if(target < root.val) return dfs(root.left, target, closest);

        else return dfs(root.right, target, closest);         

    }


    // Non optimized
    // Solve using in-order traversal 
    // Time O(N), Space O(N)
    class ClosestBSTValue {

        // Try in order traversal for fun 
    
        public int closestValue(TreeNode root, double target) {
    
             List<Integer> inorder = new ArrayList<>();       
            
            Stack<TreeNode> stack = new Stack<>();
    
          
            while(!stack.isEmpty() || root != null){
    
    
                while(root != null){
    
                    stack.push(root);
                    root = root.left;
                } 
    
                root = stack.pop();
    
                inorder.add(root.val);
    
                root = root.right;
    
            }
    
            return Collections.min(inorder, 
            (x,y) -> Double.compare(Math.abs((double)x - target),Math.abs((double)y - target)));               
            
        }   
    
        
    }

}




public class CountNodesEqualToAverageOfSubtree {



    // LC : 2265 https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/

// The number of nodes where the value of the node is equal to the average of the values in its subtree.

    int count = 0;

    public int averageOfSubtree(TreeNode root) {

        postOrderTraversal(root);

        return count;
                   
    }


    private int[] postOrderTraversal(TreeNode root){

        if(root == null) return new int[] {0,0};

        int[] left = new int[] {0,0}; // zeros as leaf nodes will return this insreado of null
        int[] right = new int[] {0,0};

        if(root.left != null){
            left = postOrderTraversal(root.left);            
        }

        if(root.right != null){
             right = postOrderTraversal(root.right);            
        }

        int nodeValSum = left[0] + right[0] + root.val;
        int numOfNodes = left[1] + right[1] + 1;

        if(root.val == (nodeValSum / numOfNodes)) count++;

        return new int[] {nodeValSum, numOfNodes};

    }

}





/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 Methods required for iterator : hasNext()--returns boolean 
                                  next() -- returns next smallest value.
 */

 class BSTIterator {

    private Stack<TreeNode> stack;
        
    public BSTIterator(TreeNode root) {
        // Basically build partial in-order stack
        stack = new Stack<>();
        pushAll(root);        
               
    }
    
    /*custom method to push subtree*/
    private void pushAll(TreeNode root){
        
        while(root != null){
            stack.push(root);
            root = root.left;            
        }        
    }
    
    
    /** @return the next smallest number */
    public int next() {

        // complete in-order traversal

        TreeNode node = stack.pop();
        pushAll(node.right);
        return node.val;
   
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {           
       return !stack.isEmpty();       
    }
}



// https://leetcode.com/problems/delete-nodes-and-return-forest/description/
class DeleteNodesReturnForest {
    
    Set<Integer> set;
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        List<TreeNode> result = new ArrayList<>();

        set = new HashSet<>();

        for(int d : to_delete){
            set.add(d);
        }

        TreeNode node = postOrderDfs(root,result);

        if(node != null) result.add(node);

        return result;
                
    }

    private TreeNode postOrderDfs(TreeNode root, List<TreeNode> remainingRoots){

        if(root == null) return null;


        // root.left and root.right are assigned teh returns as that completes pruning, if postDFS returns null,
        // If not we will still point to old node.
        root.left = postOrderDfs(root.left, remainingRoots);
        root.right = postOrderDfs(root.right, remainingRoots);
            
                
        if(set.contains(root.val)){
          
            if(root.left != null) remainingRoots.add(root.left);

            if(root.right != null) remainingRoots.add(root.right);

            return null; 
        }

       // Note :  if(root != null) remainingRoots.add(root);
       // Will lead to adding child nodes and also sometimes nodes that are also child nodes 

           // Root maybe remaining in end
        return root;
       
    }
}


// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

public class BSTToDoublyLinkedList {

    // LC 426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/        
    // O(N) time 
    // O(N) space in worst case & O(log n) - best case balanced tree

     public Node treeToDoublyList(Node root) {

        // Perform in-order traversal - Because BST root
        // keep adding elements in place to tail and head appropriately
       

        if(root == null) return root;

        Node head = null;
        Node tail = null;

        Stack<Node> stack = new Stack<>(); 

        while(!stack.isEmpty() || root != null ){

            while(root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // In a  DLL, add to end of list - before tail
                       
            if(tail != null){
                tail.right = root;
                root.left = tail;
                tail = root; // Move pointer to next
            }
            else {
                head = tail = root;                
            }

            root = root.right;        
        }

        // Connect head and tail
        tail.right = head;
        head.left = tail;

        return head;
    }

    // Dfs in-order soln
    class Solution {
    
        Node head = null;
        Node tail = null;
       
       public Node treeToDoublyList(Node root) {
   
           // Perform in-order traversal
           // keep adding elements in place to tail and head appropriately
          
           if(root == null) return root;
         
           dfs(root);
   
           // Connect head and tail
           tail.right = head;
           head.left = tail;
   
           return head;
   
       }
   
       private void dfs(Node root){
   
           if(root == null) return;
   
           dfs(root.left);
   
           if(tail != null){
               tail.right = root;
               root.left = tail;
               tail = root;
           }
   
           else head = tail = root;
   
           dfs(root.right);
       }   
   
   }


    class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    }



}


//https://leetcode.com/problems/construct-binary-tree-from-string/
// Input: s = "4(2(3)(1))(6(5))"
// Output: [4,2,6,3,1,5]
public class BinaryTreeFromString {


    public TreeNode str2tree(String s) {

        if(s == null || s.length() == 0) return null;

        Pair<TreeNode, Integer> result = buildTree(s,0);        

        return result.key;
                
    }

    // Construct tree - returns TreeNode and index in a list - Useful for recursive call
    private Pair<TreeNode,Integer> buildTree(String s, int index){
     
        if(index == s.length()) return new Pair(null, index);

        // Process root
        // returns val for root and next index to be processed
        Pair<Integer,Integer> NumAndIndex =  getNumber(s, index);

        int num = NumAndIndex.key;
        index = NumAndIndex.value; // progress after processing -ve,single and multi-digit num


        // Build tree root node first
        TreeNode root = new TreeNode(num);

        // Check for left and rigth child
        // Next immediate value will be the left child        
        if(index < s.length() && s.charAt(index) == '('){

              // Make a recursive call here to construct another subtree  
              Pair<TreeNode,Integer> leftChildInfo =  buildTree(s,index + 1);
                           
              root.left = leftChildInfo.key;

              // Update/move index forward
              index = leftChildInfo.value;
        }

        // Now check if we can find '(' - Right child    

        if(root.left != null && index < s.length() && s.charAt(index) == '('){

            // Make a recursive call here to construct another subtree  
            Pair<TreeNode,Integer> rightChildInfo =  buildTree(s,index + 1);
            root.right = rightChildInfo.key;

            // Update/move index forward
            index = rightChildInfo.value;
        }    

        // Construct result
        // root node and index for this subtree 
        Pair<TreeNode, Integer> res = new Pair();    

        res.key = root; // root of tree

        // Skip ')' or just return index
        index = (index < s.length() && s.charAt(index) == ')' ? index + 1 : index);    
        
        res.value = index;

        return res;
    }




    // process numbers in string, return num and index
    private Pair<Integer,Integer> getNumber(String s, int index){

        boolean isNegative = false;

        if(s.charAt(index) == '-'){
            isNegative = true;
            index++;
        } 

        int num = 0;
        while(index < s.length() && Character.isDigit(s.charAt(index))){

            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }

        if(isNegative) num = -num;
        
        return new Pair(num, index); // send index also, to know how far index has moved    
    }




    class Pair<K,V> {
        
        K key;
        V value;

        public Pair(){                     
        }

        public Pair(K k, V v){
            this.key = k;
            this.value = v;            
        }
    }

}



class IsTreeComplete {

    public boolean isCompleteTree(TreeNode root) {
        
        // using BFS 
        //https://leetcode.com/problems/check-completeness-of-a-binary-tree/
        /*Do a level order traversal , keep a boolen to track if previous node(left child) is null,
        // if prevNode is null and the right node is not null, the tree is said to be incomplete */
        
        if(root == null) return  true;
        
        boolean prevNode = false; // keep track of prev nul node to the left
        
        Queue<TreeNode> q = new LinkedListImplementation<TreeNode>();
                
        q.add(root);        
        
        while(!q.isEmpty()){
            
            TreeNode n = q.remove();
            
            if(n.left != null){
                
                //here check if prev Node is null
                
                if(prevNode) return false;
                
                q.add(n.left); 
            }
            
            // n.left is null
            else prevNode = true;
            
            if(n.right != null){
                
                if(prevNode) return false;
                
                q.add(n.right);
                
            }
            
            // n.right is null 
            else prevNode = true;            
            
        }
        
        return true;        
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



class MintTimetoColectApples {


    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
          
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
          
        for(int[] edge : edges){
              
          List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
          list.add(edge[1]);
        
        }       
        
        return dfs(adjacency, hasApple, 0);        
    }
      
    private int dfs(Map<Integer, List<Integer>> adjacency, List<Boolean> hasApple, int cur){
     
      List<Integer> neighbors = adjacency.get(cur);
          
      int cost = 0;
        
      if(neighbors != null){
                   
        for(int neighbor : neighbors){
          cost += dfs(adjacency, hasApple, neighbor);        
        }      
                    
      }    
        
      /*if (cur vertex is an apple or the children of the subtree of this vertex is an apple) and this vertex is not root vertex  */ 
      if((hasApple.get(cur) || cost > 0) && cur != 0){
          return cost += 2;
      }
        
      return cost;
          
    }   
  }


// https://leetcode.com/problems/populating-next-right-pointers-in-each-node
public class PopulatingNextRightPointersInEachNode {

    public Node connect(Node root) {
       
        if (root == null) return root;
        
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        // Outer while loop which iterates over
        // each level
        while (!q.isEmpty()) {

         
            int size = q.size();
           
            for (int i = 0; i < size; i++) {
                
                Node node = q.remove();
                
                // Important
                if (i < size - 1) {
                    node.next = q.peek();
                }

               
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }

        return root;
    }
}



    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BinaryTreeCameras {

    /**
     * 
        Approach : 
     *  
        placing a camera at a parent node only when its children (or the parent itself if it's the root) are not yet covered
        -> parent is not covered/not present and left and right child are not covered, place camera.
        Basically 
     * 
     */

    Set<TreeNode> visited = new HashSet<>();

    int count = 0;

    public int minCameraCover(TreeNode root) {

         
            dfs(root, null);

            return count;       

    }

    private void dfs(TreeNode root, TreeNode parent){

        // post order

        if(root == null) return;

        dfs(root.left, root);
        dfs(root.right, root);

        
        if((parent == null && !visited.contains(root)) 
                || !visited.contains(root.left) 
                || !visited.contains(root.right)){

                    // All participants covered    
                    visited.add(parent);
                    visited.add(root);

                    if (root.left != null) visited.add(root.left);
                    if (root.right != null) visited.add(root.right);
                   
                    count++;

        }
    }
}


//https://leetcode.com/problems/graph-valid-tree/

class GraphValidTree {

        
    Map<Integer,List<Integer>> graph = new HashMap<>();

    Set<Integer> visited = new HashSet<>();
    
    public boolean validTree(int n, int[][] edges) {

        if(edges.length != n - 1) return false;
       
        for(int[] edge : edges ){

            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0);

        return (visited.size() == n);
    }

    // Mark visited
    private void dfs(int node){

        if(visited.contains(node)) return;

        visited.add(node);

        List<Integer> neighbors = graph.get(node);

        
        if(neighbors == null) return;
        
        for(Integer neighbor : graph.get(node)){

            dfs(neighbor);
        }
    }


    /*
    A graph is a tree if it has exactly n - 1 edges for n nodes.

    Simple dfs and adjacencey list base graph representation.

    Check if a node has been visited, if not mark it visited. 
    */
    
}




// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree

/*find mid of list and make it the root, left, right child can be recursively got by splitting the list--> providing start and end to helper*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


class LLToBST {

    public TreeNode sortedListToBST(ListNode head) {
        
            if(head == null) return null;
            
            // start and end of linked list
            return helper(head,null);        
    }
        
    private TreeNode helper(ListNode head, ListNode tail){
        
        if (head == tail) return null; // important, termination condition : when there is no more mid element to be discovered

        ListNode slow = head;
        ListNode fast = head;
                        
        while(fast != tail && fast.next != tail){
            
            slow = slow.next;
            fast = fast.next.next;          
                                    
        } 
        
        // slow == mid    

        TreeNode n = new TreeNode(slow.val);
        
        n.left = helper(head,slow);
        n.right = helper(slow.next,tail);
        
        return n;
        
    }
    
}