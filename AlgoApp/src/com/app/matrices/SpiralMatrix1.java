/*54. Spiral Matrix*/
package com.app.matrices;

import java.util.List;
import java.util.ArrayList;

public class SpiralMatrix1 {

    public List<Integer> spiralOrder(int[][] matrix) {
               
        // to returnj the result
        List<Integer> res = new ArrayList<>();
        
        if(matrix == null || matrix.length <= 0) return res;
        
        int rowStart = 0;
        int rowEnd = matrix.length - 1;  // length - 1 will prove to be useful later
        
        int colStart = 0;
        int colEnd = matrix[0].length - 1;       
        
    // boundary check
    while(rowStart <= rowEnd && colStart <= colEnd){
        
        for(int i = colStart; i <= colEnd ; i++){
            
            res.add(matrix[rowStart][i]);         
        }        
        rowStart++;
        
        
        for(int j = rowStart; j <= rowEnd ; j++ ){
            
            res.add(matrix[j][colEnd]);       
        }        
        colEnd--;
        
        
        for(int k = colEnd; k >= colStart; k-- ){
            
           if(rowStart <= rowEnd){ /*note- else the inner spiral will continue to malfunction*/
            
               res.add(matrix[rowEnd][k]);               
           }
        }        
        rowEnd--;
        
        for(int l = rowEnd; l >= rowStart ; l--){
            
            if(colStart <= colEnd){  /*note- else the inner spiral will continue to malfunction*/
            
                res.add(matrix[l][colStart]);               
            }
        }
        
        colStart++;
        
        /*for inner spiral like in case of 5 X 5*/
        
       // rowStart++; /*wrong approach- will lead to revisit of elements*/
       // rowEnd--;
       // colStart++;
       // colStart--;
        
    }
    return res;
        
    }
    
    
    
    
    /*Test*/
    public static void main(String[] args){
      
         int[][] arr = {{1,2,3},     // expected o/p --> 1,2,3,6,9,8,7,4,5
                    {4,5,6},
                    {7,8,9}};
                    
                    
         SpiralMatrix1 obj = new SpiralMatrix1();
         
         List<Integer> res = obj.spiralOrder(arr);            
         
         System.out.println(res);           
      
    }
    
}