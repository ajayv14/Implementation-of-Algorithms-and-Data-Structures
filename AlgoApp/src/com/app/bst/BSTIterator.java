import com.app.binarytree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 Methods required for iterator : hasNext()--returns boolean 
                                  next() -- returns next smallest value.
 */

class BSTIterator {

    private Stack<TreeNode> stack;
        
    public BSTIterator(TreeNode root) {
        
        stack = new Stack<>();
        pushAll(root);        
               
    }
    
    /*custom method to push subtree*/
    private void pushAll(TreeNode root){
        
        while(root != null){
            stack.push(root);
            root = root.left;            
        }        
    }
    
    
    /** @return the next smallest number */
    public int next() {
        
       //if(hasNext()){
            TreeNode node = stack.pop();
            pushAll(node.right);
            return node.val;
        //}        
        //return -1;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {           
       return !stack.isEmpty();       
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
