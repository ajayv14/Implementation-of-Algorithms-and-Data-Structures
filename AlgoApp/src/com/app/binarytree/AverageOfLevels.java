class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        
        List<Double> res = new ArrayList<>();
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            
            int size = q.size();
            double avg = 0;
                
            for(int i = 0; i < size; i++){
                //nodes on level
                TreeNode node = q.remove();
                avg += node.val;
                
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }           
            
            res.add(avg/size);
            avg = 0; //reset            
        }         
        return res;        
    }
}