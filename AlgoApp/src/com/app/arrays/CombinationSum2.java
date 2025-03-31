package com.app.array;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class CombinationSum2 {

    int count = 0;/* to count the print statement */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> MainList = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(MainList, new ArrayList<>(), candidates, target, 0);
        return MainList;

    }

    public void backtracking(List<List<Integer>> MainList, List<Integer> tempList, int[] candidates, int rem,
            int start) {

        // System.out.println(count++ + "tempList:" + tempList);

        if (rem < 0)
            return; /* non positive integers */

        if (rem == 0)
            MainList.add(new ArrayList(tempList)); /* target achieved, hence add tempList to MainList */

        for (int i = start; i < candidates.length; i++) {

            if (i > start && candidates[i] == candidates[i - 1])
                continue; /* i > start to prevent out of bounds exception */

            else {

                tempList.add(candidates[i]);
                backtracking(MainList, tempList, candidates, rem - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

    }

}