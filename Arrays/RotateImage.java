class RotateImage {
    public void rotate(int[][] matrix) {
        
        // perform a transpose
        
        for(int i = 0; i < matrix.length; i++){ //row loop
            
            for(int j = i; j < matrix[0].length; j++){  //col loop
                
                //swap matrix[i][j] with [j][i]
                
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
                
                
            }           
            
            
        }
        
        
        // Horizontal flip
        
        for(int i = 0; i < matrix.length; i++){ //row loop
            
             for(int j = 0; j < matrix[0].length/2; j++){  //col loop ..note: j=0 and j<length/2 
                
                //swap matrix[i][j] with [j][i]
                
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
                
                
               }           
                    
          }    
        
    }
}