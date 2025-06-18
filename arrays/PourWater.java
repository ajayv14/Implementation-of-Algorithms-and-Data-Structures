// LC : https://leetcode.com/problems/pour-water

class PourWater {
    public int[] pourWater(int[] heights, int volume, int k) {
        
        /* Just follow the question
         * k is the pivot index 
         * move to left step by step and find smallest value than k
         * if not, move to right and do the same thing
         * if still not found, the smallest idx = k.
         * Increment heights[smallest index]
         */ 

        for(int i = 0; i < volume; i++){

            int idx = k;    

            for(int left = k - 1; left >= 0; left --){

                if(heights[left] < heights[idx]){

                    idx = left;               
                } 
                else if(heights[left] >  heights[idx]){
                     break;
                }     
            }

            // No smaller index found on left, hence check and find on right
            if(idx == k) {

                for(int right = k + 1; right < heights.length; right++){
        
                    if(heights[right] < heights[idx]){
                
                        idx = right;
                    } 
                    else if(heights[right] > heights[idx]){
                        break; 
                    }
                }
            }

            heights[idx]++; // Smallest on right or by default use k as smallest idx
        }      

        return heights;
    }

    



}