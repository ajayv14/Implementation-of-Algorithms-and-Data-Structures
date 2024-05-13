package com.app.binarytree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {

    int count = 0;
    int k;
    Map<Long, Integer> prefixSumMap = new HashMap<>();   


    public int pathSum(TreeNode root, int targetSum) {
        
                  
        k = targetSum;

        dfs(root, 0);
        
        return count;
    }

    public void dfs(TreeNode node, long prefixSum) {

        if (node == null) return;

        prefixSum += node.val;

        if(prefixSum == k) count++;

        count += prefixSumMap.getOrDefault(prefixSum - k, 0);

        prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum,0) + 1);

        dfs(node.left, prefixSum);
        dfs(node.right, prefixSum);

        prefixSumMap.put(prefixSum, prefixSumMap.get(prefixSum) - 1);
    }

}
