import com.app.binarytree.TreeNode;

class SecondMinimumInTree {
    
/*DfS solution*/
    
    /*TreeSet maintains unique values in ascending order*/
    
    Set<Integer> set = new TreeSet<>();
    
    public int findSecondMinimumValue(TreeNode root) {
        
        dfs(root, set);
      
        Iterator<Integer> iterator = set.iterator();
        
        
        int counter = 0; /*for second min, counter should reach 2*/
        while(iterator.hasNext()){
            
           int result = iterator.next();
           counter++ ;
            
            
           if(counter == 2) return result;
            
            
            
        } 
       
        
        return -1;
        
    }
    
    
    public void dfs(TreeNode root, Set<Integer> set){
        
        if(root == null) return;
        
        set.add(root.val);
        
        dfs(root.left, set);
        dfs(root.right, set);
                
    }
    
}