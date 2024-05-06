
class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        /* perform in-order traversal--will give a sorted list, hence every value in list is greater than previous, if violated , return             false*/
        
        Stack<TreeNode> stack = new Stack<>(); // holds all the Node.left members of a node obtained recursively
        TreeNode previousNode = null; // to hold previous value to compare
        
        if(root == null) return true;
        
        while(root!=null || !stack.empty()){
            
            while(root!=null){ // add all left subchild of node
                stack.add(root);
                root = root.left;
            }            
            
            // when null is reached            
            root = stack.pop();            
            
            if(previousNode != null && root.val <= previousNode.val) return false; // current val lesser tha previous in sorted list
            
            previousNode = root;
            root = root.right;
            
            
        }
        
        return true;
        
        
       
        
    }
}