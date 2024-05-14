package com.app.binarytree;

import java.util.HashMap;
import java.util.Map;

import com.app.common.CommonUtil;


// LC 75
public class PathSumIII {


    /**
     * Using prefix-sum algo
     */

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


    public static void main(String[] args) {
        PathSumIII obj = new PathSumIII();

        TreeNode root1 = TreeUtil.createTree(new String[] {"5","4","8","11",null,"13","4","7","2",null,null,"5","1"});
        int targetSum1 = 22;    
       
        CommonUtil.runExample("input1 : [5,4,8,11,null,13,4,7,2,null,null,5,1]", "Expected output : 3", obj.pathSum(root1, targetSum1) + "");
    }

}
