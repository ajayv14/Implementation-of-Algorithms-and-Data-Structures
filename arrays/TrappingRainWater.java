package com.app.arrays;

// LC 42.  
public class TrappingRainWater {

    public int trap(int[] height) {

        int leftMax = 0;
        int rightMax = 0;
        int capacity = 0;
        int left = 0;
        int right = height.length - 1;

        while (left <= right) {

            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if (leftMax < rightMax) {
                capacity += leftMax - height[left];
                left++;
            }

            else {
                capacity += rightMax - height[right];
                right--;
            }

        }

        return capacity;

    }

    //Approach 2 : Find left max and right max at each index and store in two
    // arrays
    // O(N) Time and O(N) space
    public int trapBruteForce(int[] height) {

        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int capacity = 0;

        leftMax[0] = height[0];

        for (int i = 1; i < leftMax.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[rightMax.length - 1] = height[height.length - 1];

        for (int j = height.length - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }

        for (int k = 0; k < height.length; k++) {
            capacity += Math.min(leftMax[k], rightMax[k]) - height[k];
        }
        return capacity;
    }


    public static void main(String[] args) {
        
        TrappingRainWater obj = new TrappingRainWater();

        int[] height1 = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Expected : 6");
        System.out.println("Actual : " + obj.trap(height1));
        
        int[] height2 = new int[]{4,2,0,3,2,5};
        System.out.println("Expected : 9");
        System.out.println("Actual : " + obj.trap(height2));
    }

}
