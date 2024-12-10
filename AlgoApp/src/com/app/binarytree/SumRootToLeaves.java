import com.app.binarytree.TreeNode;

/**
  * credits : https://leetcode.com/problems/sum-root-to-leaf-numbers
  *
**/

class SumRootToLeaves {
    
    int totalSum = 0;
    
    public int sumNumbers(TreeNode root) {
                      
       dfs(root,"");    
       return totalSum; 
    }
    
    private void dfs(TreeNode root, String number){
        
        if(root == null) return;
        
        number += root.val + "";
        
        //if(root.left != null){
            dfs(root.left, number);
        //}
        
        //if(root.right != null){
            dfs(root.right, number);
        //}
        
        if(root.left == null && root.right == null){
            totalSum += Integer.parseInt(number);
        }
    }
}



