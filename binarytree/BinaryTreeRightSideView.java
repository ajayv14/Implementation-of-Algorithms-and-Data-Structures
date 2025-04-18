package com.app.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.app.models.TreeNode;


// LC 199 : https://leetcode.com/problems/binary-tree-right-side-view/

// Time O(N) Space O(D) in avg and O(N) in worst case

public class BinaryTreeRightSideView {

        
    // Level order traversal, BFS, but pick the rightmost node at each level. 

    public List<Integer> rightSideView(TreeNode root) {
    
      List<Integer> ll = new ArrayList<Integer>();
      Queue<TreeNode> q = new LinkedList<TreeNode>(); 
      
      if(root == null) return ll;
          q.add(root);
          
    
           while(!q.isEmpty()){
                
            int level = q.size();
               
                
                for(int i=0;i<level;i++){
                    TreeNode n = q.remove();
                    
                    // Pick the last element in each level -> i = level - 1
                    if(i==level-1){// to get the rightmost element in the level
                        ll.add(n.val);
                    }
                    
                    if(n.left!=null){
                     q.add(n.left);
                    }
                 
                    if(n.right!=null){
                     q.add(n.right);
                    }                     
                    
                }                     
         }
        
        return ll;
}

}
