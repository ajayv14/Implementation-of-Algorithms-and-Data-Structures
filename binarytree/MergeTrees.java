import com.app.models.TreeNode;

class MergeTrees {



    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        
        if(t1==null && t2==null) return null;
        
        int newValue = (t1==null?0:t1.val ) + (t2==null?0:t2.val);
        
        TreeNode newNode = new TreeNode(newValue);
        
        // check for null to avoid NullPointerExeption
        newNode.left = mergeTrees(t1==null?null:t1.left,t2==null?null:t2.left);
        newNode.right = mergeTrees(t1==null?null:t1.right,t2==null?null:t2.right);
        
        
        return newNode;
        
    }
}