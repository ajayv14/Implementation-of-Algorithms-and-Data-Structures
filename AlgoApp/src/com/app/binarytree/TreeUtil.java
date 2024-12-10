package com.app.binarytree;

import java.util.LinkedList;
import java.util.Queue;

import com.app.binarytree.TreeNode;

public class TreeUtil {

    public static TreeNode createTree(String[] treeArray) {

        if (treeArray == null || treeArray.length == 0) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(treeArray[0]));
        queue.add(root);
      
        int index = 1;
        while (index < treeArray.length) {

            TreeNode cur = queue.remove();

            if (treeArray[index] != null) {
                cur.left = new TreeNode(Integer.valueOf(treeArray[index]));
                queue.add(cur.left);
            }
            index++;

            if (index < treeArray.length && treeArray[index] != null ) {
                cur.right = new TreeNode(Integer.valueOf(treeArray[index]));
                queue.add(cur.right);
            }
            index++;   
        }
        return root;
    }

}
