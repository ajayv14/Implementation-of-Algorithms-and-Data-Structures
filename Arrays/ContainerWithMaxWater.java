

/**
 * At each iteration calculate max area that can be computed with left and right pointer. Compare to current max area.
 */
class ContainerWithMaxWater {
    public int maxArea(int[] height) {

        // 2 pointer approach
        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;

        while (left < right) {

            if (height[left] < height[right]) {
                maxArea = Math.max(maxArea, height[left] * (right - left)); // Length * Breadth
                left++;
            }

            else { // right < left
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }

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