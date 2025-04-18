/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
import java.util.Queue;

import com.app.models.TreeNode;
import com.app.stack.LinkedList;

import java.util.*;
public class InvertBinaryTree {
    
    /*easy to understand*/
    
    public TreeNode invertTree1(TreeNode root) {
        
        if(root == null) return root;
        TreeNode tempLeft = root.left;
        root.left = root.right;
        root.right = tempLeft;
                 
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
        
    } 
    
    
    
    public TreeNode invertTree2(TreeNode root) {
        
        if(root == null) return root;
        TreeNode tempLeft = root.left;
        root.left = invertTree2(root.right);
        root.right = invertTree2(tempLeft);
        return root;
        
    }   
    
    
    public TreeNode invertTree3(TreeNode root) {
        
        if(root == null) return root;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
          TreeNode cur = queue.poll();
          TreeNode tempLeft = cur.left;
          cur.left = cur.right;
          cur.right = tempLeft;
          
          if(cur.left != null) queue.offer(cur.left);
          if(cur.right != null) queue.offer(cur.right);
                   
        }
        
        return root;        
    }
    
    
    
    /*Test*/
    public static void main(String[] args){
      
      TreeNode root = new TreeNode(1);
      root.left = new TreeNode(2);
      root.right = new TreeNode(3);
      root.left.left = new TreeNode(4);
      root.left.right = new TreeNode(5);
      
      
      InvertBinaryTree obj = new InvertBinaryTree();
      obj.invertTree3(root);  
      obj.printTree(root);          
    }
    
    
    private void printTree(TreeNode root){
      
      if(root == null) return;
      System.out.print(root.val+",");
      if(root.left != null) printTree(root.left);
      if(root.right != null) printTree(root.right);           
          
    }
         
    
}