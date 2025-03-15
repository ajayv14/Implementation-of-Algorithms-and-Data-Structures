

import java.util.LinkedList;
import java.util.Queue;
import com.app.models.TreeNode;

class BottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {
        
        //dummy node to return result
        TreeNode res = null;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
            
         while(!q.isEmpty()){
          
             int size = q.size();
             res = q.peek(); // left most element in each level
             
             for(int i = 0; i < size; i++){
                 
                 TreeNode cur = q.poll();
                 
                 if(cur.left != null) q.offer(cur.left);
                 if(cur.right != null) q.offer(cur.right);
             }             
         }  
        return res.val;
    }
}