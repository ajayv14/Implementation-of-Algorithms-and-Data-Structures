public class TrappingRainWaterHard {

    //LC : 42. Trapping Rain Water

    /** Water gets trapped between left and right wall.
    Water height in current position is calculated between max left height of wall and max right height of wall minus  current height of wall. (imagine a concrete bump in middle)          
    */


    // Two pointer soln : 
    public int trap(int[] height) {

        int leftMax = 0;
        int rightMax = 0;
        int capacity = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            
            if(height[left] < height[right]){

                leftMax = Math.max(leftMax, height[left]);
                capacity += leftMax - height[left];
                left++;
            }
            
            else {

                rightMax = Math.max(rightMax, height[right]);
                capacity += rightMax - height[right];
                right--;
            }               
            
        }

        return capacity;

    }

    //Approach 2 : Find left max and right max at each index and store in two
    // arrays
    // O(N) Time and O(N) space
    public int dynamic(int[] height) {

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
}
