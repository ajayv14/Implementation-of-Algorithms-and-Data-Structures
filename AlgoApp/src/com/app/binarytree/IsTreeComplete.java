import com.app.stack.LinkedList;

class IsTreeComplete {
    public boolean isCompleteTree(TreeNode root) {
        
        // using BFS 
        //https://leetcode.com/problems/check-completeness-of-a-binary-tree/
        /*Do a level order traversal , keep a boolen to track if previous node(left child) is null, if prevNode is null and the right node is not null, the tree is said to be incomplete */
        
        if(root == null) return  true;
        
        boolean isPrevNodeNull = false; // keep track of prev nul node to the left
        Queue<TreeNode> q = new LinkedList<TreeNode>();
                
            q.add(root);        
        
        while(!q.isEmpty()){
            
            TreeNode n = q.remove();
            
            if(n.left != null){
                
                //here check if prev Node is null
                
                if(isPrevNodeNull == true) return false;
                
                q.add(n.left); 
            }
            
            else isPrevNodeNull = true;
            
            if(n.right != null){
                
                if(isPrevNodeNull == true) return false;
                
                q.add(n.right);
                
            }
            
            else isPrevNodeNull = true;            
            
        }
        
        return true;        
    }   
    
}