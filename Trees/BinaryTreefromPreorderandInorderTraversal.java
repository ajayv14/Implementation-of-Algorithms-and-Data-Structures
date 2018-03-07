
/**
 * - preorder[0] is the root, find this in inorder.
 * - then items to left of this in inorder are elements of left subtree and items on right belong to right subtree
 * - construct tree recursively 
 * - hashmap to store inorder array values improves performance
 */


class BinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
         //suggested by yavinci - leetcode
         Map<Integer,Integer> map = new HashMap<>();
        
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i],i);
        }
        
         return toBinaryTree(0,0,inorder.length-1,preorder,inorder,map);
    }
    
    /*form new node(root), then recursively root.left and right */
    
    public TreeNode toBinaryTree(int preStart,int inStart, int inEnd,int[] preOrder,int[] inOrder, Map<Integer,Integer> map){
            
        if(preStart > preOrder.length - 1 || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(preOrder[preStart]); 
        
        //int index = 0;
        /* move pointer to the index in in-order */
        
        /* optimized by using HashMap, fetching index value thro map
            
           for(int i = inStart; i <= inEnd; i++){
            
            if(inOrder[i] == root.val){
                index = i;
            }
        }*/
        
        int index = map.get(root.val);       
         
        root.left =  toBinaryTree(preStart+1,inStart, index-1 ,preOrder,inOrder,map);
        root.right =  toBinaryTree(preStart +(index - inStart + 1) ,index+1,inEnd,preOrder,inOrder,map);
        //right = starts from preStart + offsetr + 1 = 
        
        return root;
        
    }
    
}