package com.app.binarysearch;

import java.util.Arrays;
/**
 * LC 2300
 * You are given two positive integer arrays spells and potions, of length n and m respectively,also given an integer success.
 * return an  integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 * / */
public class SuccessfulPotionPairs {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        Arrays.sort(potions);

        int[] res = new int[spells.length];

        int successCount = 0;
        int ptr = 0;

        for (int spell : spells) {

            int optimalPotionIndex = findOptimalPotion(spell, potions, success);

            if (optimalPotionIndex >= 0)
                successCount = potions.length - optimalPotionIndex;

            res[ptr] = successCount;

            ptr++;
        }

        return res;
    }

    // Apply modified binary search
    // Returns optimal potion index >= success num or -1 when none leads to success
    private int findOptimalPotion(int spell, int[] potions, long success) {

        int left = 0;
        int right = potions.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            long num = (long) potions[mid] * (long) spell;

            if (num >= success)
                right = mid - 1;

            // smaller
            else
                left = mid + 1;
        }
        return left;

    }


    public static void main(String[] args) {
        
        SuccessfulPotionPairs obj = new SuccessfulPotionPairs();
        
        int[] s1 = new int[]{5,1,3};
        int[] p1 = new int[]{1,2,3,4,5}; 
        int success1 = 7;

        System.out.println("Expected : [4,0,3]");
        System.out.println("Actual : " + obj.findOptimalPotion(success1, p1, success1));
    
    }


    /*
     * private int findOptimalPotion(int spell, int[] potions, long success){
     * 
     * // Time limit exceeded
     * /* for(int i = 0; i < potions.length; i++){
     * if((long)spell * (long)potions[i] >= success) return i;
     * }
     */

    // optimize

    // if(spell == 0 || success == 0 ) return -1;

    // find the number which when multiplied leads to success
    // long otherHalf = success / (long)spell;

    // wont work as 7/5 will be 1.4 , rounded off to 1.
    /*
     * for(int i = 0; i < potions.length; i++){
     * 
     * if((long)potions[i] >= otherHalf) return i;
     * }
     */

    

}
