package com.app.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//credits: https://leetcode.com/problems/binary-tree-inorder-traversal/

class InOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> list = new ArrayList<>();
        
        if(root == null) return list;
        
        /*Stack<TreeNode> stack = new Stack<>();
        
        while(root != null || !stack.isEmpty()){
            
            while(root != null){
                stack.push(root);
                root = root.left;                
            }
            
            root = stack.pop();
            list.add(root.val);
            root = root.right;            
        } */       
        
        inOrder(root,list);
        
        return list; 
 
    } 
    
    // without explicitly using stack
    
    
    private void inOrder(TreeNode root,List<Integer> list){
        
        //if(root == null) return;
        
        if(root.left != null){
            inOrder(root.left, list);    
        }
        
        list.add(root.val);
        
        if(root.right != null){
            inOrder(root.right, list);   
        }        
        
    }
    
    
}