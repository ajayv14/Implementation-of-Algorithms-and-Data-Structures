/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class SearchBST {
    
    /* Recursive soln.
    public TreeNode searchBST(TreeNode root, int val) {
        
        if(root==null) return root;
        
        if (root.val == val) return root;
                  
        if(root.val < val) return searchBST(root.right,val);
        if(root.val > val) return searchBST(root.left,val);
        
        return null;
        
    }*/
    
    // iterative
     public TreeNode searchBST(TreeNode root, int val) {
                         
        while(root!=null && root.val != val){
            
            if(root.val < val){
                root = root.right;
            } 
            else if(root.val > val){
                root = root.left;  
            }            
        }        
        
        return root;        
    }
    
    
    
}