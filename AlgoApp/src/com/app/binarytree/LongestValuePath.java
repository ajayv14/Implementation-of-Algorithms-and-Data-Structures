package com.app.binarytree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LongestValuePath {
    
    int len = 0;
    public int longestUnivaluePath(TreeNode root) {        
       if(root != null) traverse(root,-1);
        return len;
    }
    
    
    private int traverse(TreeNode root, int prevVal){
        
        if (root == null) return 0;
        
        int count = 0;
        int left = traverse(root.left, root.val);
        int right = traverse(root.right,root.val);
        
                
        len = Math.max(len,left+right);
        //System.out.println(len);
        if(root.val == prevVal)
            count = 1+Math.max(left,right);                
                
        return count;        
    }    
}