public class BinaryTreeMaxPathSum {


    int max = 0;
    
    public int maxPathSum(TreeNode root) {
          max = root.val;
          dfs(root);        
          return max;
    }
    
    
    private int dfs(TreeNode root){
        
        if(root == null) return 0;
                       
        int left = 0, right = 0;
        
        left = dfs(root.left);
        right = dfs(root.right);
        
        max = Math.max(max, (left + right + root.val));
        
        //System.out.println(left +"--"+ right);
        
        int curMax = Math.max(left, right) + root.val; 
         
        max = Math.max(max, curMax);
        
        //System.out.println();
        return (curMax > 0)?curMax : 0;
    }

}
