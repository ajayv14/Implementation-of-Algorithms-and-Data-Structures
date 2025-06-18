public class KSmallestInBST {

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || root != null){

            while(root != null){

                stack.push(root);
                root = root.left;
            }

            k--;

            root = stack.pop();   

            if(k == 0) return root.val;

            root = root.right;
        }
        
        return -1;
    }
}
