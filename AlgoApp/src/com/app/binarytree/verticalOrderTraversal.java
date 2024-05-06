/*Level Order traversal + map */

class verticalOrderTraversal {
    
    /*Try other ways of solving this*/
    
    
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        
        /*return result list*/
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();/*for level order traversal*/
        Queue<Integer> column = new LinkedList<>(); /*for maintaining horizontal distance*/   
                
        
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();/*to store horizontal distance and corresponding list of elements*/
        
        queue.add(root);
        
        column.add(0);
        
        
        int hdMin = 0;
        int hdMax = 0;      
       
        
        while(!queue.isEmpty()){
            
            TreeNode node = queue.poll();
            int col = column.poll();
             
            if(!map.containsKey(col)){
                
                map.put(col, new ArrayList<>());
                
            }
            
            map.get(col).add(node.val);
            
            
            /*enqueue node.left, enqueue corresponding column value, update range hdMin*/
            if(node.left != null){
                
                queue.add(node.left);
                column.add(col - 1); 
                hdMin = Math.min(hdMin, col - 1);
                
            }
           
             /*enqueue node.right, enqueue corresponding column value, update range hdMax*/
            if(node.right != null){
                queue.add(node.right);
                column.add(col + 1);
                hdMax = Math.max(hdMax, col + 1);
            }
           
                                
        }
        
        
        for(int i = hdMin; i <= hdMax; i++){
            
            result.add(map.get(i));            
            
        }
        
        
        
        
        return result;
        
          
    }
    
    
    
    
    
    
}