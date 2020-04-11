/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 root-to-leaf path where values along the path equals the given sum.
 
 */
public class PathSum {
 
 public boolean hasPathSum(TreeNode root, int sum) {
        
    if(root==null) return false;
    
    if(root.left==null && root.right==null && sum==root.val) return true; // root - leaf path is complete -- left & right are null
        
    return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val); 
    }
    
    
    
    
    
}
