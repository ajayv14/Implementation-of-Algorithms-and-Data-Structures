package com.app.arrays;

class RemoveSpecificElement {
    public int removeElement(int[] nums, int val) {

        int ref = 0; // use this as pointer, when val is spotted, skip turn , else set arr[ref] =
                     // arr[i]and move pointer

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                nums[ref] = nums[i];
                ref++;
            }

            // else{
            // when val is spotted ..do nothing
            // }

        }

        return ref; // length of the filtered part of array
    }
}