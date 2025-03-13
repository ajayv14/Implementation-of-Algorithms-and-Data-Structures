package com.app.matrix;

/*Rotate 2D Array by 90 degree

1 2 3          7 4 1
4 5 6   -->    8 5 2 
7 8 9          9 6 3

*/
/* logic -  transpose the array, then do a horozontal flip*/

public class RotateImage {
    
    public void rotate(int[][] matrix) {
        
        // Perform a Transpose (convert row to col)
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
                
                // Optimization - skip same values
                if(matrix[i][j] == matrix[i][matrix.length-1-j]) continue;              
                

                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;                
                
               }           
                    
          }           
    }



    public static void main(String[] args) {
        RotateImage obj = new RotateImage();

        int[][] arr1 = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
    
        obj.rotate(arr1);
        System.out.println("Expected  : [[7,4,1],[8,5,2],[9,6,3]]");
        System.out.println("Actual  : ");
        Array2DUtil.printArray(arr1);
    
        int[][] arr2 = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        obj.rotate(arr2);
        System.out.println("Expected output : [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]");
        System.out.println("Actual output : ");
        Array2DUtil.printArray(arr2);
      } 

}