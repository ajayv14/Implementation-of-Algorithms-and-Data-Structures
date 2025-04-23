public class CountNodesEqualToAverageOfSubtree {



    // LC : 2265 https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/



    int count = 0;

    public int averageOfSubtree(TreeNode root) {

        postOrderTraversal(root);

        return count;
                   
    }


    private int[] postOrderTraversal(TreeNode root){

        if(root == null) return new int[] {0,0};

        int[] left = new int[] {0,0};
        int[] right = new int[] {0,0};

        if(root.left != null){
            left = postOrderTraversal(root.left);            
        }

        if(root.right != null){
             right = postOrderTraversal(root.right);            
        }

        int nodeValSum = left[0] + right[0] + root.val;
        int numOfNodes = left[1] + right[1] + 1;

        if(root.val == (nodeValSum / numOfNodes)) count++;

        return new int[] {nodeValSum, numOfNodes};

    }

}
