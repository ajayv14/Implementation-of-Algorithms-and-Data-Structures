
package com.app.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// credits: Back To Back SWE : https://www.youtube.com/watch?v=nPtARJ2cYrg
// 2) https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

class AllNodesDistanceKBinaryTree {
    
    /*Logic : Something like level order traversal, but we need to include all nodes at distance from 0 - K, one level at a time.
               Including the parents. So we need a Map to store parent of each node and also a Set to store "seen" nodes to prevent overlap in                     counting nodes. 
    */    
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
       
        // to store & return result
        List<Integer> res = new ArrayList<>();
         
        // bfs queue
        Queue<TreeNode> queue = new LinkedList<>();
        
        Map<TreeNode, TreeNode> parentMap = new HashMap<>(); // parent of each node
        Set<TreeNode> set = new HashSet<>(); // seen nodes      
        
        traverse(parentMap, root); // to find and store <k,v> -> <node, parent node>
                
        // begin adding target node to the queue
        queue.offer(target);               
        
        /*similar to level order traversal*/
        while(K > 0 && !queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++ ){
                
                TreeNode node = queue.poll();
                                                
                if(!set.contains(node)){
                  set.add(node); // mark as seen  
                } 
                
                // add children and parent to queue
                if(node.left != null && !set.contains(node.left)) queue.offer(node.left);
                
                if(node.right != null && !set.contains(node.right)) queue.offer(node.right);
                
                if(parentMap.containsKey(node) && !set.contains(parentMap.get(node))) queue.offer(parentMap.get(node));                
            }       
            
            K--;
        }        
                                   
        while(!queue.isEmpty()){           // nodes at distance k
            res.add(queue.poll().val);
        }
        
        return res;
    }
    
    
    private void traverse(Map<TreeNode,TreeNode> map, TreeNode root){
        
        if(root == null) return;
        
        if(root.left!= null){
            map.put(root.left,root);
            traverse(map, root.left);            
        }
        
        if(root.right != null){
            map.put(root.right, root);
            traverse(map, root.right);            
        }        
        
    }
}