package com.app.binarytree;

// LC 1372. Longest ZigZag Path in a Binary Tree

//https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/

/*
    Similar to Path sum III.

    Logic - Note : Path can start at ANY node,. so need to consider all nodes as root at some point.
    Zigzag traversal - Use a boolean to choose direction. 
    from previous iteration, figure out you need to go left or right. Also assume new node starts at the current node as well.    

 */


 // Time - 
public class LongestZigZagPath {

    int max = 0;

    public int longestZigZag(TreeNode root) {       

       zigzag(root, 0 , true);
       return max;        
    }

    private void zigzag(TreeNode root, int sum, boolean left){

        if(root == null) return;

        max = Math.max(max, sum);

        if(left) {

            zigzag(root.right,sum + 1,false);
            zigzag(root.left,1,true); // Assume new root starts here 

        } 

        else {
            zigzag(root.left,sum + 1,true); 
            zigzag(root.right,1,false);  // Assume new root starts here 

        }        
    }
}

/**
 * Time Complexity Analysis
    The time complexity of the provided solution is O(n), where n is the number of nodes in the binary tree.
    Reasoning:
        The zigzag function recursively traverses each node in the tree exactly once.
        Each recursive call performs a constant amount of work (i.e., updating max and making two recursive calls).
        The recursion tree has a height equal to the height of the binary tree, and each node in the recursion tree has at most two children.

    Space Complexity Analysis
        The space complexity of the provided solution is O(h), where h is the height of the binary tree.
    Reasoning:
        The maximum recursion depth is equal to the height of the binary tree.
        The recursive call stack uses O(h) space to store the function call stack frames.
        The max variable uses O(1) space.
Notes:
In the worst case, the binary tree is skewed, and the height (h) is equal to the number of nodes (n). In this case, the space complexity becomes O(n).
 * 
 * 
 */