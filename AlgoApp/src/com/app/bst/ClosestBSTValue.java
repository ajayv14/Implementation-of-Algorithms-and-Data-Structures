package com.app.bst;

import com.app.models.TreeNode;


// LC 270 : https://leetcode.com/problems/closest-binary-search-tree-value

//Apply binary search to arrive at target node.

/**
 * 
 * Time complexity:
Best O(log n) - Balanced tree
Skewed tree - O(n)

Space complexity:
Best O(log n) - Balanced tree
Skewed tree - O(n)
 */

public class ClosestBSTValue {


    public int closestValue(TreeNode root, double target) {
                 
        return dfs(root, target, root.val);
        
    }


    private int dfs(TreeNode root, double target, int closest){
        
        if(root == null) return closest;

        // Lets say we have node & closest node 3 and 4 and target is 3.2, 3.70 or 3.5.
        // 3.78 - closer to 4, 3.2 - closer to 3, 3.5 - in between, then pick 3.

        double distFromTarget = Math.abs(root.val - target);
        double distFromClosest = Math.abs(closest - target);

        // distFromTarget <  distFromClosest - Chose target as closest node
        // distFromTarget ==  distFromClosest, pick the smaller btw current node and closest so far.

        if(distFromTarget <  distFromClosest || 
             distFromTarget == distFromClosest && root.val < closest) closest = root.val;

        if(target < root.val) return dfs(root.left, target, closest);

        else return dfs(root.right, target, closest);         

    }

}
