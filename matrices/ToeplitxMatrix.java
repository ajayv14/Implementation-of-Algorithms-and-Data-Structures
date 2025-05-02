package com.app.matrices;


// LC 766. Toeplitz Matrix - https://leetcode.com/problems/toeplitz-matrix

public class ToeplitxMatrix {
   
    // For each cell - row,col, check if diagonal cell is equal.(row + 1, col + 1)
    // Time O(M X N) Space : O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {

        
        for(int row = 0; row < matrix.length; row++){
            
            for(int col = 0; col < matrix[0].length; col++){

                int newRow = row + 1;
                int newCol = col + 1;
                
                if(newRow < matrix.length && newCol < matrix[0].length){

                    if(matrix[row][col] != matrix[newRow][newCol]) return false;
                }

            }
        }

        return true;
        
    }
}
