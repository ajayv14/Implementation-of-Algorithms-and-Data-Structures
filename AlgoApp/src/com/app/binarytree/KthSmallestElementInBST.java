import com.app.binarytree.TreeNode;

class KthSmallestElementInBST {
	

	//in-order traversal recursion

	public int kthSmallest(TreeNode root, int k) {
        
        if(root == null) return 0;
        
        List<Integer> list = new ArrayList<>();
        
        inOrder(root, list);
        
        return list.get(k - 1);
    }
    
    
    public void inOrder(TreeNode root, List<Integer> list){
                
        if(root == null) return;
        
        inOrder(root.left, list);
        
        list.add(root.val);
        
        inOrder(root.right, list);
        
        
    }




    //in-order iterative

     public int kthSmallest(TreeNode root, int k) {
        
         if(root == null) return -1;     
         
         Stack<TreeNode> stack = new Stack<>();
         
        
        while(!stack.isEmpty() || root != null){
            
            while(root != null){
                
                stack.push(root);
                root = root.left;                
            }
            
            root = stack.pop();
            --k;
            if(k == 0) return root.val;            
            root = root.right;
            
        }
         return -1;        
    }


}