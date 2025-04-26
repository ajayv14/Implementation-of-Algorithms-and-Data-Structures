public class InOrderSuccessorBST2 {

// LC 510 : https://leetcode.com/problems/inorder-successor-in-bst-ii/

    /*

- If the node has a right child, and its successor is somewhere lower in the tree. Go to the right once and then as many times to the left as you can. Return the node you end up with.

- Node has no right child, and hence its successor is somewhere upper in the tree. Go up till the node that is left child of its parent. The answer is the parent.
The successor is an ancestor of root that is a left child of its parent

Look at editorial for example

 */


    // Find next largest in sorted nodes ?
    public Node inorderSuccessor(Node root) {

        if(root.right != null){

            root = root.right;
            
            while(root.left != null){
                root = root.left;
            }
            return root;
        }

        // no right child
        else {

            // Confirm if root has  a parent and current root is right child of parent
            // Return the parent who's left child is this current root; ()
            while(root.parent != null && root == root.parent.right){
                root = root.parent;
            }
            return root.parent;

        }                

        
    }

}
