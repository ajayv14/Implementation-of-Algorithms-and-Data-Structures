/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        
   if(root==null) return true;
   
   return isSymmetric(root.left,root.right);

}    
   
private boolean isSymmetric(TreeNode NodeL, TreeNode NodeR){
    
    //both left and right child are null,leaft nodes and symetric
    if(NodeL==null && NodeR == null) return true;
    
    // one of it is null, symetric property is lost
    if(NodeL==null || NodeR==null) return false;
    
    // if a node is symmetric
    if(NodeL.val == NodeR.val){
    
        return isSymmetric(NodeL.left,NodeR.right) && isSymmetric(NodeR.left,NodeL.right);
    }
  
	return false;
    

    
    
}    
    
}