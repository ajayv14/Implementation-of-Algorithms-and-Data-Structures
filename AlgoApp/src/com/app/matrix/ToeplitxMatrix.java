package com.app.matrix;


// LC 766. Toeplitz Matrix - https://leetcode.com/problems/toeplitz-matrix

public class ToeplitxMatrix {



    // For each cell - row,col, check if diagonal cell is equal.(row + 1, col + 1)
    // Time O(M X N) Space : O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {

        int n = matrix.length, m = matrix[0].length;

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){

                if((row + 1) < n && col + 1 < m){

                    if(matrix[row][col] != matrix[row + 1][col + 1]) return false;
                }

            }
        }

        return true;
        
    }
}
