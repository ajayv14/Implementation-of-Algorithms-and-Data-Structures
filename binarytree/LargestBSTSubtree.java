


// LC : https://leetcode.com/problems/largest-bst-subtree/
// 333. Largest BST Subtree

public class LargestBSTSubtree {

    public int largestBSTSubtree(TreeNode root) {

        if(root == null) return 0;        

        if(isValidBST(root, null, null)) return countNodes(root);
       

        return Math.max(largestBSTSubtree(root.left),largestBSTSubtree(root.right));        

    }


    public boolean isValidBST(TreeNode root, Integer low, Integer high){

        if(root == null) return true;
           
        if((low != null && root.val <= low ) || ( high != null && root.val >= high)){
           
           return false;
        } 

        else {
             
             return isValidBST(root.left,low,root.val) && isValidBST(root.right, root.val, high);  

        }       
    }

    private int countNodes(TreeNode root){

        if(root == null) return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}
