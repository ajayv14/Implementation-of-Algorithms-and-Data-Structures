package com.app.bst;

import java.util.Stack;

public class BSTToDoublyLinkedList {

    // LC 426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/        
    // O(N) time 
    // O(N) space in worst case & O(log n) - best case balanced tree

     public Node treeToDoublyList(Node root) {

        // Perform in-order traversal
        // keep adding elements in place to tail and head appropriately
       

        if(root == null) return root;

        Node head = null;
        Node tail = null;

        Stack<Node> stack = new Stack<>(); 

        while(!stack.isEmpty() || root != null ){

            while(root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // In a  DLL, add to end of list - before tail
                       
            if(tail != null){
                tail.right = root;
                root.left = tail;
                tail = root; // Move pointer to next
            }
            else {
                head = tail = root;                
            }

            root = root.right;        
        }

        // Connect head and tail
        tail.right = head;
        head.left = tail;

        return head;
    }

    // Dfs in-order soln
    class Solution {
    
        Node head = null;
        Node tail = null;
       
       public Node treeToDoublyList(Node root) {
   
           // Perform in-order traversal
           // keep adding elements in place to tail and head appropriately
          
           if(root == null) return root;
         
           dfs(root);
   
           // Connect head and tail
           tail.right = head;
           head.left = tail;
   
           return head;
   
       }
   
       private void dfs(Node root){
   
           if(root == null) return;
   
           dfs(root.left);
   
           if(tail != null){
               tail.right = root;
               root.left = tail;
               tail = root;
           }
   
           else head = tail = root;
   
           dfs(root.right);
       }   
   
   }


    class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    }



}
