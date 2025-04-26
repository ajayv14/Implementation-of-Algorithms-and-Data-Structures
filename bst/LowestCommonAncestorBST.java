public class LowestCommonAncestorBST {

    // LC : 235 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree    

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
       else return root;
               
    }
}
