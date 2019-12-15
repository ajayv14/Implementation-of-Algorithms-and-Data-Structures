/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        
        if(root == null) return root;
        TreeNode tempLeft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tempLeft);
        return root;
        
    }   
}