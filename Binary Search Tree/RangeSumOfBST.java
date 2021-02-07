
//credit : https://leetcode.com/problems/range-sum-of-bst/

// easy




class RangeSumOfBST {
    
    int sum = 0;
    
    public int rangeSumBST(TreeNode root, int low, int high) {
       //dfs        
                
        dfs(root, low, high);  
        
        return sum;
        
    }
    
    
    
    public void dfs(TreeNode root, int low, int high){
        
        if(root == null) return;
        
        if(root.val >= low && root.val <= high) {
            
            sum += root.val;            
                 
        } 
        
        
        if(root.val > low) {                  
            dfs(root.left, low, high);            
        }
        
        
        
        if(root.val < high) {         
                 
            dfs(root.right, low, high);            
        }
        
        
        
    }
    
    // using level order -
    /*public int rangeSumBST(TreeNode root, int low, int high) {
        
        
        int sum = 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        
        while(!q.isEmpty()){
            
            int len = q.size();
            
            for(int i = 0; i < len; i++){
                
                TreeNode node = q.poll();       
                
                if(node.val >= low && node.val <= high) sum += node.val;
                
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                
            }
            
            
        }       
        
        return sum;     
        
    }*/
}