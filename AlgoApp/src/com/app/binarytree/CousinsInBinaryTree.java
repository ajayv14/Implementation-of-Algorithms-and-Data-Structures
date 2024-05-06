/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class CousinsInBinaryTree {
    
    /**
      * 2 nodes if they are cousins, won't share the same parent
      * keep a parent variable set to null, until we find either x or y. Update the parent
      * A levelNumber variable to keep track of levels
      * Modified Level order traversal. Enqueue parent p and child node for every node.
      * check if node is from same level & has a diff parent than the other node found. 
    **/
    
    
    public boolean isCousins(TreeNode root, int x, int y) {
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(null);
        q.offer(root);        
        
        TreeNode parent = null;
        int levelCount = 0;
        int levelNumber = 0;        
        
        while(!q.isEmpty()){
            
            int level = q.size()/2; // parent node is also enqueued for each node            
            ++levelCount;            
                                   
            for(int i = 0; i < level; i++){                
                                
                TreeNode p = q.poll();
                TreeNode n = q.poll();
                       
                if(n.val == x || n.val == y){
                    
                    /*persist the level number and parent of node that is found (x or y)*/
                    if(parent == null){
                       parent = p;   
                       levelNumber =  levelCount; 
                    }  
                                                            
                    else if(parent != null && p != parent && levelCount == levelNumber) return true;
                    
                    else return false;                   
                }                
                                
                if(n.left != null){
                  q.offer(n);  
                  q.offer(n.left);  
                }  
                
                if(n.right != null){
                   q.offer(n); 
                   q.offer(n.right);  
                } 
            }            
        }      
        return false;        
    }
}