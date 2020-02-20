/*Rotate 2D Array by 90 degree

1 2 3          7 4 1
4 5 6   -->    8 5 2 
7 8 9          9 6 3

*/
/* logic -  transpose the array, then do a horozontal flip*/

class Rotate2DArray {
    
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