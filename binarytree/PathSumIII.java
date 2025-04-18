

import java.util.HashMap;
import java.util.Map;

import com.app.models.TreeNode;
import com.app.common.CommonUtil;


// LC 437
//https://leetcode.com/problems/path-sum-iii/


public class PathSumIII {

     /*
    Three tier recursive call approach   - Note : Path to target sum from ANY node, not just root
     */

     int count = 0;

     public int pathSum(TreeNode root, int targetSum) {
 
         if(root == null) return 0;     
         
         dfs(root,(long)0,targetSum);    
 
         // Recursively check for both children as well    
         pathSum(root.left,targetSum);
         pathSum(root.right,targetSum);        
         
         return count;
     }
 
     public void dfs(TreeNode root, long sum, int target) {
 
         if (root == null) return;
 
         sum += root.val;
 
         if(sum == target) count++;
 
         dfs(root.left, sum, target);
         dfs(root.right, sum, target);     
         
     }
 
    /**
 * 
 *  Meta ai :

 Here are the time and space complexities for the modified solution:
    Time Complexity:
        O(N * logN) or O(N) in the average case, where N is the number of nodes in the tree.

        Explanation:
            We perform a depth-first search (DFS) from each node, which takes O(logN) time in a balanced binary tree (worst-case scenario) or O(1) time in an unbalanced tree (best-case scenario).
            We repeat this process for each node, resulting in O(N * logN) or O(N) time complexity.
    
    Space Complexity:
        O(H), where H is the height of the tree.

        Explanation:
            
            We use recursive calls to perform DFS, which requires O(H) space on the call stack.
            In the worst case (unbalanced tree), H = N. In the average case (balanced tree), H = logN.
Note:
The space complexity can be improved to O(1) by using an iterative approach instead of recursion. * 
 * 
 */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Using prefix-sum algo
     */

   /* int count = 0;
    int k;
    Map<Long, Integer> prefixSumMap = new HashMap<>();   


    public int pathSum(TreeNode root, int targetSum) {
        
                  
        k = targetSum;

        dfs(root, 0);
        
        return count;
    }

    public void dfs(TreeNode node, long prefixSum) {

        if (node == null) return;

        prefixSum += node.val;

        if(prefixSum == k) count++;

        count += prefixSumMap.getOrDefault(prefixSum - k, 0);

        prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum,0) + 1);

        dfs(node.left, prefixSum);
        dfs(node.right, prefixSum);

        prefixSumMap.put(prefixSum, prefixSumMap.get(prefixSum) - 1);
    }


    public static void main(String[] args) {
        PathSumIII obj = new PathSumIII();

        TreeNode root1 = TreeUtil.createTree(new String[] {"5","4","8","11",null,"13","4","7","2",null,null,"5","1"});
        int targetSum1 = 22;    
       
        CommonUtil.runExample("input1 : [5,4,8,11,null,13,4,7,2,null,null,5,1]", "Expected output : 3", obj.pathSum(root1, targetSum1) + "");
    }*/

}
