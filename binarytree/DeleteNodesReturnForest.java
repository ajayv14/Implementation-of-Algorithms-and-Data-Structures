public class DeleteNodesReturnForest {

    // LC 1110 https://leetcode.com/problems/delete-nodes-and-return-forest/

    /* Approach : 
      Can't delete a node and then find its children.  
      Use postorder traversal to visit children all way till leaf nodes.
      Move up the stack, adding child nodes to result list.
      Delete the marked nodes.
    */ 

    Set<Integer> set;
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        List<TreeNode> result = new ArrayList<>();

        set = new HashSet<>();

        for(int d : to_delete){
            set.add(d);
        }

        TreeNode node = postOrderDfs(root,result);

        if(node != null) result.add(node);

        return result;
                
    }

    private TreeNode postOrderDfs(TreeNode root, List<TreeNode> remainingRoots){

        if(root == null) return null;


        // assi
        root.left = postOrderDfs(root.left, remainingRoots);
        root.right = postOrderDfs(root.right, remainingRoots);
            
        
        
        if(set.contains(root.val)){
          
            if(root.left != null) remainingRoots.add(root.left);

            if(root.right != null) remainingRoots.add(root.right);

            return null; 
        }

       // Note :  if(root != null) remainingRoots.add(root);
       // Will lead to adding child nodes and also sometimes nodes that are also child nodes 

        return root;
       
    }

}
