

import java.util.Stack;
//LC 98
//https://leetcode.com/problems/validate-binary-search-tree/

import com.app.models.TreeNode;

 /** Approach : Not only we have to check if root.left.val < root.val and root.right.val > root.val, but also make sure all nodes under root.left are lesser than main root and all nodes under root.right is greater than main root. 
 Example: root.right.left.val < root.right.val, but fails the condition "all nodes under root.right should be greater than main root" 
 */

class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

        if(root == null) return true;      
        
        return isBSTHelper(root, null, null);
        
    }

    // Using Integer, so that we can pass null values also. 
    private boolean isBSTHelper(TreeNode root, Integer low, Integer high){

         if(root == null) return true;   

         // Note : we compare root.val and not root.left.val and so on.   
         if((low != null && root.val <= low) || (high != null && root.val >= high)) return false;         

         return isBSTHelper(root.left,low,root.val) && isBSTHelper(root.right,root.val, high);

    }


    public static void main(String[] args) {
        
        // [5,4,6,null,null,3,7] false

        // [5,1,4,null,null,3,6] false

        //[2,1,3] true

    }

    // Iterative approach
    public boolean isValidBSTIterative(TreeNode root) {
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