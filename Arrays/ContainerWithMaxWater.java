class ContainerWithMaxWater {
    public int maxArea(int[] height) {
        
        // 2 pointer approach
        int left = 0;
        int right = height.length - 1;
        
        int maxArea = 0;
        
        while(left < right){
           
            if(height[left] < height[right]){                
                maxArea = Math.max(maxArea, height[left] * (right - left)); // Length * Breadth
                left++;                
            }
            
            else {   // right < left
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
            
            
            
        }   
        return maxArea;
    }
}