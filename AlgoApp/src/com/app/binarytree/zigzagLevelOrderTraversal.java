import com.app.stack.LinkedList;

class zigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
      
        /*usual level order traversal*/
      
        boolean odd = true; /*to check if level is odd or even*/
        
        
        /*return result list*/
        
        List<List<Integer>> result = new ArrayList<>();
        
        
       if(root == null) return result; 
        
       Queue<TreeNode>  queue = new LinkedList<>();
       
       queue.add(root); 
        
       while(!queue.isEmpty()){
           
           List<Integer> list = new ArrayList<>(); /*temp list to hold all values in a level*/
           
           int level = queue.size();
                      
           for(int i = 0; i < level; i++){
               
               TreeNode node = queue.poll();
            
               
               /*zig-zag, reverse add the elements to list in case of level is even*/
               
               if(odd){
                   list.add(node.val);
               }
               
               else{
                   list.add(0,node.val);
               }
              
               
               if(node.left != null) queue.add(node.left);
           
               if(node.right!= null) queue.add(node.right);
           
           
           }
           
           odd = odd?false:true; /*flip the odd value*/
           
           result.add(list); 
                 
           
       } 
        
        
        
      return result;  
        
    }
}