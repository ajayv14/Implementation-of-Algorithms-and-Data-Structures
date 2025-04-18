
import com.app.models.TreeNode;
import com.app.common.CommonUtil;

/**
 * Approach :Find max length path(max depth) on root.left, find max length path on root.right, add both
            
            1                     longest on left - 1-2-4 or 1-2-5  = 2                
           / \                    longest on right - 1 - 3  = 1
          2   3
         / \
        4   5
    */ 
 
 //LC 543   
public class DiameterOfTree {

    int maxDiameter = 0; // maxDiameter at each node - max dia is usually between two leaves via root.

    public int diameterOfBinaryTree(TreeNode root) {

        dfs(root);
        return maxDiameter;
    }

    public int dfs(TreeNode root) {

        if (root == null)
            return 0;

            
        int maxDepthLeft = dfs(root.left);
        int maxDepthRight = dfs(root.right);

        maxDiameter = Math.max(maxDiameter, (maxDepthLeft + maxDepthRight));

        return 1 + Math.max(maxDepthLeft, maxDepthRight);
    }


    public static void main(String[] args) {
        
        DiameterOfTree obj = new DiameterOfTree();

        String[] tree1 = {"1","2","3","4","5"};
        TreeNode tree1Root = TreeUtil.createTree(tree1);
        CommonUtil.runExample("[1,2,3,4,5]", "3", obj.diameterOfBinaryTree(tree1Root) + "");

        String[] tree2 = {"1","2"};
        TreeNode tree2Root = TreeUtil.createTree(tree2);
        CommonUtil.runExample("[1,2]", "1", obj.diameterOfBinaryTree(tree2Root) + "");

        String[] tree3 = {"4", "-7", "-3", null, null, "-9", "-3", "9", "-7", "-4", null, "6", null, "-6", "-6", null, null, "0", "6", "5", null, "9", null, null, "-1", "-4", null, null, null, "-2"};
        TreeNode tree3Root = TreeUtil.createTree(tree3);
        CommonUtil.runExample("[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]", "8", obj.diameterOfBinaryTree(tree3Root) + "");
       
    }

}
