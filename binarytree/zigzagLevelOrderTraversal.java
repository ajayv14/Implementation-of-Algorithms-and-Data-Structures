import com.app.models.TreeNode;
import com.app.stack.LinkedList;

// 103 : https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

class zigzagLevelOrderTraversal {

     // Time O(n), Space O(n)
     public List<List<Integer>> zigzagLevelOrderOptimized(TreeNode root) {
        
               
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
            
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        boolean isReverse = false;
        
        while(!queue.isEmpty()){
            
            List<Integer> levelResult = new LinkedList<>();
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                
                TreeNode node = queue.remove();

                if(isReverse) levelResult.addFirst(node.val);
                else levelResult.addLast(node.val);
                                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }                             

            isReverse = !isReverse; // flip
            
            result.add(levelResult);
            
        }
        
        return result;
        
    }


     public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
               
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null) return result;
        
            
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        boolean isReverse = false;
        
        while(!queue.isEmpty()){
            
            List<Integer> levelResult = new ArrayList<>();
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                
                TreeNode node = queue.remove();
                
                levelResult.add(node.val);
                                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            
            if(isReverse) Collections.reverse(levelResult);           

            isReverse = !isReverse;
            
            result.add(levelResult);
            
        }
        
        return result;
        
    }
}