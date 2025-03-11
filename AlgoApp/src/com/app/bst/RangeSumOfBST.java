
//credit : https://leetcode.com/problems/range-sum-of-bst/

// easy

import java.util.LinkedList;
import java.util.Queue;
import com.app.models.TreeNode;


class RangeSumOfBST {
    
    int sum = 0;  

    public int rangeSumBST(TreeNode root, int low, int high) {
                     
        sumDFSOptimized(root, low, high);  
        return sum;        
    }

    // Non-optimized - checks all nodes and compares against low and high    
    private void sumDFS(TreeNode root, int low, int high){

        if(root == null) return;

        if(root.val >= low && root.val <= high){
            
            System.out.println("sum : " + sum);
            sum += root.val;
        } 

         sumDFS(root.left,low,high);
         sumDFS(root.right,low,high);               
    }    
    
    // Optimized uisng BST property
    private void sumDFSOptimized(TreeNode root, int low, int high){

        if(root == null) return;

        if(root.val >= low && root.val <= high){
            
            //System.out.println("sum : " + sum);
            sum += root.val;
        }
        
        // Using BST property 
         if(root.val > low){
            sumDFS(root.left,low,high);
         }  

         if(root.val < high){
            sumDFS(root.right,low,high);
         }                
    }


    
    // using level order -
    public int rangeSumBSTLevelOrder(TreeNode root, int low, int high) {
        
        
        int sum = 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        
        while(!q.isEmpty()){
            
            int len = q.size();
            
            for(int i = 0; i < len; i++){
                
                TreeNode node = q.poll();       
                
                if(node.val >= low && node.val <= high) sum += node.val;
                
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                
            }            
            
        }       
        
        return sum;     
        
    }
}