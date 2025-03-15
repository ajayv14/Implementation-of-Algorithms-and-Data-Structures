import com.app.models.TreeNode;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTree {
	
    public boolean isBalanced(TreeNode root) {
        
        return dfsDepth(root)!=-1;
        
    }
    
    
    int dfsDepth(TreeNode root){
        
        if(root==null) return 0;
        
          int leftDepth =  dfsDepth(root.left);
          
          if(leftDepth==-1) return -1;
    
         
          int rightDepth =  dfsDepth(root.right);
          
          if(rightDepth==-1) return -1;
          
          // diff btw left and right height > 1
          if(Math.abs (leftDepth-rightDepth)>1) return -1;
          
          return Math.max(leftDepth,rightDepth)+1;
          

    }
    
    
    
}