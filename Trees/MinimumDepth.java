
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/* find if a left or right child exists,
 *  calculate and return min of left and right depth at
 *  each node recursively  */
public class MinimumDepth {
    
    public int minDepth(TreeNode root) {
        
    //ckeck  null, min(left , right)        
      int leftMin = 0, rightMin = 0;
      
      if(root == null) return 0;
       
      leftMin = minDepth(root.left);  
      rightMin = minDepth(root.right);
       
      //Special case when root has just one child tree--[1,2]
      if(leftMin == 0 || rightMin == 0){ 
            return 1+leftMin+rightMin;
      }        
      return (Math.min(leftMin,rightMin)+1);        
    }
}