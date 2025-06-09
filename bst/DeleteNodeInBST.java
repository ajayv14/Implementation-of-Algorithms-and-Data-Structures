

// LC 450. Delete Node in a BST https://leetcode.com/problems/delete-node-in-a-bst/


//Time complexity - O(log n)
// Space O(h) -> for the recursive stack
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return null;

        // Node not found yet
        if(key < root.val) {

            root.left = deleteNode(root.left, key);
        }

        // Why map return value of deleteNode to root.left above ??
        // Handles cases where key is not found in tree


        // Node not found yet
        else if(key > root.val){

             root.right = deleteNode(root.right, key);
        }

        // Found node to be deleted
        else {

            // is it a leaf node ?? No left and rigth child
            if(root.left == null && root.right == null) {
                return root = null;
                
            }

            // node has one child
            else if (root.left == null || root.right == null){
                return root.left == null ? root.right : root.left;
            }


            // node has 2 children
            else {

                TreeNode ptr = root;    
                ptr = ptr.right;

                while(ptr.left != null){
                    ptr = ptr.left;
                }

                root.val = ptr.val;

                // Since ptr.val is assigned to root.val, we will have a node with duplicate value, hende proceed to delete
                root.right = deleteNode(root.right, ptr.val); 
                
                                
            }

        }           

        return root;        
    }   
}
