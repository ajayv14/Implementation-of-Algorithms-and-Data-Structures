package com.app.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.app.binarytree.TreeNode;


//LC 102
public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> mainList = new ArrayList<List<Integer>>();

        if (root == null) return mainList;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root); // Make sure atleast one node is in queue

        while (!queue.isEmpty()) {

            int level = queue.size();

            List<Integer> levelNodes = new ArrayList<Integer>(); // To hold Nodes in each level

            for (int i = 0; i < level; i++) {

                TreeNode node = queue.remove(); // remove each node from Queue

                levelNodes.add(node.val);

                // Enqueue its children
                if (node.left != null)
                    queue.add(node.left);

                if (node.right != null)
                    queue.add(node.right);

            }

            mainList.add(levelNodes);
        }

        return mainList;

    }



    public static void main(String[] args) {
        
        LevelOrderTraversal obj = new LevelOrderTraversal();

        String[] tree1 = {"3","9","20",null,null,"15","7"};
        TreeNode tree1Root = TreeUtil.createTree(tree1);

        System.out.println("Expected : [[3],[9,20],[15,7]]");

        System.out.println("Actual : " + obj.levelOrder(tree1Root));
    }

}
