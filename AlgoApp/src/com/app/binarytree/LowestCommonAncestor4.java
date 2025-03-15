


// LC 1676 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
// Almost same as LCA 1

public class LowestCommonAncestor4 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {

        if(root == null) return root;

        // Same as LCA 1, but check for all nodes in array instead of just p & q
        for(TreeNode node : nodes){

            if(node == root) return node;
        } 

        // recursive call
        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);       
        
        if(left != null && right != null) return root;

        return (left != null) ? left : right;
    }

}
