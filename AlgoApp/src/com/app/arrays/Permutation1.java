package com.app.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Permutation1 {
    public List<List<Integer>> permute(int[] nums) {

        /* return list */
        List<List<Integer>> MainList = new ArrayList<>();

        Arrays.sort(nums);

        backtracking(MainList, new ArrayList<>(), nums);

        return MainList;

    }

    /* backtracking helper function */

    public void backtracking(List<List<Integer>> MainList, List<Integer> tempList, int[] nums) {

        /*
         * make sure the temp list has equal elements that of original array (coz
         * permutations)
         */

        if (tempList.size() == nums.length) {

            MainList.add(new ArrayList<>(tempList));

        }

        else {

            for (int i = 0; i < nums.length; i++) {

                if (tempList.contains(nums[i]))
                    continue; /*
                               * skip element if already present, possibility arises
                               * due to fact that tempList is sent as a parameter in backtracking
                               */

                tempList.add(nums[i]);
                backtracking(MainList, tempList, nums);
                tempList.remove(tempList.size() - 1);

            }

        }

    }

}