class TrappingRainWater {
    public int trap(int[] height) {
        
        int left=0;
        int right=height.length-1;
        int leftMax=0;
        int rightMax=0;
        int capacity=0;
        
       /* while(left<=right){
            
            if(height[left]<height[right]){ // left tower is shorter and right is taller
              
                // this case only update left max, as the tallest tower cannot hold any unit of water on top
                 
                if(height[left]>leftMax) leftMax=height[left]; //update leftMax 
                else capacity+= leftMax - height[left];                
                left++; 
                
            }
            
           // else if(height[right]<height[left]){
            else{ 
            
                if(height[right]>rightMax) rightMax=height[right];
                
                else capacity += rightMax - height[right];
                right--;
            
            }
               
            
        }
        /*Correct Solution*/
        
        while(left<=right){
            
            // calculate left and right max values at each step
            leftMax = Math.max(leftMax,height[left]);
            rightMax= Math.max(rightMax,height[right]);
            
            //calculate capacity based on max values
            
            if(leftMax < rightMax){                
                capacity+= leftMax - height[left];
                left++;
                
            }
            
           // else if(rightMax < leftMax){ // for sake of information
             
            else{
                capacity += rightMax - height[right];               
                right--;
            }    
            
        }        
        
        return capacity;
        
        
    }
}