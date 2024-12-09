package com.app.array;

/*39. Combination Sum*/

import java.util.List;
import java.util.ArrayList;

class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> MainList = new ArrayList<>();
        // Arrays.sort(candidates);
        backtracking(MainList, new ArrayList<Integer>(), candidates, target, 0);
        return MainList;

    }

    public void backtracking(List<List<Integer>> MainList, List<Integer> tempList, int[] candidates, int rem,
            int start) {

        if (rem == 0)
            MainList.add(new ArrayList<Integer>(tempList)); /* sum is equal to target */

        if (rem < 0)
            return;

        else {

            for (int i = start; i < candidates.length; i++) {

                tempList.add(candidates[i]);
                // System.out.println(tempList); /*Please try this*/

                backtracking(MainList, tempList, candidates, rem - candidates[i], i);
                tempList.remove(tempList.size() - 1);

            }

        }

    }

    public static void main(String[] args){

        CombinationSum obj = new CombinationSum();

        System.out.println("Expected Result :  [[2,2,3],[7]] ");
        System.out.println("Actual Result : " + obj.combinationSum(new int[]{2,3,6,7},7));
        
    }

}



/*
 * inputs: [2,3,6,7]
 * target = 7
 * 
 * 
 * combinations: in tempList
 * [2]
 * [2, 2]
 * [2, 2, 2]
 * [2, 2, 2, 2]
 * [2, 2, 2, 3]
 * [2, 2, 2, 6]
 * [2, 2, 2, 7]
 * [2, 2, 3]
 * [2, 2, 3, 3]
 * [2, 2, 3, 6]
 * [2, 2, 3, 7]
 * [2, 2, 6]
 * [2, 2, 7]
 * [2, 3]
 * [2, 3, 3]
 * [2, 3, 6]
 * [2, 3, 7]
 * [2, 6]
 * [2, 7]
 * [3]
 * [3, 3]
 * [3, 3, 3]
 * [3, 3, 6]
 * [3, 3, 7]
 * [3, 6]
 * [3, 7]
 * [6]
 * [6, 6]
 * [6, 7]
 * [7]
 * [7, 7]
 * 
 * 
 * outputs: [[2,2,3],[7]]
 * 
 * 
 */