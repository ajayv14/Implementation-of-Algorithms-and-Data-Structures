package com.app.matrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalTraverse {

    // LC 498 : https://leetcode.com/problems/diagonal-traverse/description/

    /*
        approach - Number belongs to a diagonal computed by sum of i + j;
    */
    public int[] findDiagonalOrder(int[][] mat) {
        
        if(mat == null || mat.length == 0) return new int[]{0};

        int n = mat.length;
        int m = mat[0].length;

        int res[] = new int[m * n];
        int r = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();    

        for(int i = 0; i < n; i++){

            for(int j = 0; j < m; j++){
                
                int sum = i + j;

                map.putIfAbsent(sum, new ArrayList<>());
                map.get(sum).add(mat[i][j]);                
            }
        }

        for(int key : map.keySet()){

            List<Integer> tempList = map.get(key);

            if(key % 2 == 0) Collections.reverse(tempList);
            
            for(int num : tempList) res[r++] = num;
        }

        return res;

    }
}


