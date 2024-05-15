package com.app.binarytree;


import java.util.LinkedList;
import java.util.Queue;

class MaxLevelSum {
    public int maxLevelSum(TreeNode root) {
        
        //level order traversal
        
        if(root == null) return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int largestLevelSum = Integer.MIN_VALUE;
        int largestCorrespondingLevel = 0;
        int level = 0;
       
        while(!queue.isEmpty()){
                        
            level++;
            int size = queue.size();
            int tempSum = 0;
            
            for(int i = 0; i < size; i++){                
                
                TreeNode node = queue.poll();
                tempSum += node.val;
                
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            
           // System.out.println(tempSum);
            
            if(tempSum > largestLevelSum){
                
                largestLevelSum = tempSum;
                largestCorrespondingLevel = level;
                
            } 
        }
        
        return largestCorrespondingLevel;
    }
}