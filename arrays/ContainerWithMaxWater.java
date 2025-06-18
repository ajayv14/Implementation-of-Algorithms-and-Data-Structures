package com.app.array;


// LC 11 https://leetcode.com/problems/container-with-most-water/
/**
 * At each iteration calculate max area that can be computed with left and right pointer. Compare to current max area.
 */
class ContainerWithMaxWater {
    public int maxArea(int[] height) {

        // 2 pointer approach
        int maxArea = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length - 1;
       
        while(left <= right){            
        
            int width = right - left;            
            int curArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, curArea);

            if(height[left] <= height[right]) left++;

            else right--;       
        }                      
        
        return maxArea;
    }

    public static void main(String[] args){
        ContainerWithMaxWater obj = new ContainerWithMaxWater();

        int[] sample1 = new int[] {1,8,6,2,5,4,8,3,7};
        int[] sample2 = new int[] {1,1};

        System.out.println("Expected result : 49");
        System.out.println("Actual result : " + obj.maxArea(sample1));

        System.out.println("Expected result : 1");
        System.out.println("Actual result : " + obj.maxArea(sample2));

    }

}