

import com.app.models.TreeNode;

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


/**
  * credits: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
  * logic - Store root.right in a temp node. Replace root.right with root.left. Recursively flatten the tree.
  * Now need to attach temp to leaf of the tree. So traverse all the way down. Attach and then recurse.   *

**/

class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        
       if(root == null) return;
        
       TreeNode temp = null;
       
       if(root.right != null) temp = root.right;
        
       if(root.left != null){
           root.right = root.left;
           root.left = null;
       } else temp = null; // important - else if root.left is null, this will go into infinite loop by attaching temp again to root.right 
        
       flatten(root.right);        
      
       // now move the pointer to the leaf node
       TreeNode node = root;
       while(node.right != null){
           node = node.right;
       }
        
       node.right = temp;
       flatten(node.right);
        
    }
}