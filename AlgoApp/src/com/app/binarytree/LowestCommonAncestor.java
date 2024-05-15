package com.app.binarytree;

class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root==null || root == p || root == q) return root; // if root is either p or q, by default it is common ancestor
        
        TreeNode left = lowestCommonAncestor(root.left,p,q); // find either p or q in root.left
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        
        if(left != null && right != null) return root; // both not null, then common ancestor is found, else return non null val to parent
        
        return left!=null?left:right;
        
    }
}