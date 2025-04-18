

import com.app.models.TreeNode;

// Simple dfs

// LC 112

// https://leetcode.com/problems/path-sum/

public class PathSum {
 
    public boolean hasPathSum(TreeNode root, int targetSum) {

        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int sum){

        if(root == null) return false;    

        if(root.left == null && root.right == null && root.val == sum) return true;

        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
        
    }    
    
}
