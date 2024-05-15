package com.app.binarytree;

import java.util.LinkedList;
import java.util.Queue;

//credits : https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/588384/O(n)-solution-for-Seralizing-Deserializing-Binary-tree
/*
    logic - modified Level order traversal - build a string and append "null" when node is null

*/
public class SerializeAndDeSerializeBinaryTree  {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
       if(root == null) return "";
        
        // thro level order traversal
        StringBuilder sb = new StringBuilder();
                
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            
            TreeNode node = q.poll();
            
            String v = (node == null)?"null":node.val + "";
            sb.append(v + ",");
            
            if(node != null){
                q.offer(node.left);
                q.offer(node.right);
            }            
        }
        //  return sb.substring(0,sb.length() - 1).toString();// skip the comma or use the one below
        return sb.delete(sb.length() - 1, sb.length()).toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        if(data.length() <= 0) return null;
            
        String[] s = data.split(",");
        int index = 1; // skipping root
        
        //System.out.println(data);
        
        // form root
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
            
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
       
        while(!q.isEmpty()){
            
            TreeNode node = q.poll();
            
            String left  = s[index++];
            String right = s[index++];
            
            if(left.equals("null")) node.left = null; // end of subtree in left
            
            else { // create new subtree on left
                
                node.left = new TreeNode(Integer.parseInt(left));
                q.add(node.left);
            }
            
            
            if(right.equals("null")) node.right = null;
            
            else{ // construct right subtree
                
                node.right = new TreeNode(Integer.parseInt(right));
                q.offer(node.right);
            }
            
        }
        
        return root;        
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));