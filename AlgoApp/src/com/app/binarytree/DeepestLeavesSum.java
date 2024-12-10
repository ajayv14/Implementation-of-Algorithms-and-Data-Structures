package com.app.binarytree;


import java.util.LinkedList;
import java.util.Queue;

import com.app.binarytree.TreeNode;

class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
                        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int sum = 0;
        
        while(!q.isEmpty()){
            
            int size = q.size();
            sum = 0; // reset after each level
            
            /*at each level*/
            for(int i = 0; i < size; i++){
                    
                TreeNode cur = q.remove();
                sum += cur.val; 
                
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);                               
            }         
        }       
        return sum;
    }
}