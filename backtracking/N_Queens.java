import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class N_Queens {

    /*
        Diagonal - Difference in row and col remains the same. (0,0), (1,1), (2,2) 
        row - col -> (0 - 0) = (1 - 1) = (2 - 2) = 0;
        Anti-Diagonal (left diag) - Sum of row and col remain the same : (0,2),(1,1),(2,0) - top right to bottom left.
        row + col -> (0 + 2) = (1 + 1) = (2 + 0) -> 2

    */

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        Set<Integer> usedCols = new HashSet<>(); 
        Set<Integer> usedDiag = new HashSet<>(); // Holds row - col values
        Set<Integer> usedAntiDiag = new HashSet<>(); //Holds row + col
        char[][] matrix = new char[n][n];

        backtracking(0, usedCols, usedDiag, usedAntiDiag, matrix);

        return res;
    }


    private void backtracking(int row,
                             Set<Integer> usedCols,
                             Set<Integer> usedDiag,
                             Set<Integer> usedAntiDiag, 
                             char[][] matrix ){

        if(row == matrix.length){
            addToResult(matrix);
            return;
        }

        // Each column in row 
        for(int col = 0; col < matrix.length; col++){

            int column = col;
            int diag = row - col;
            int antiDiag = row + col;  

            // 
            if(usedCols.contains(column) || usedDiag.contains(diag) || usedAntiDiag.contains(antiDiag)){
                continue;
            }

            // Add queen to board
            usedCols.add(column);
            usedDiag.add(diag);
            usedAntiDiag.add(antiDiag);
            matrix[row][col] = 'Q';

            backtracking(row + 1, usedCols, usedDiag, usedAntiDiag, matrix);

            usedCols.remove(column);
            usedDiag.remove(diag);
            usedAntiDiag.remove(antiDiag);
             matrix[row][col] = '.';
            
        }        
    }

    
    private void addToResult(char[][] matrix){

        List<String> sol = new ArrayList<>();
        
        for(char[] row : matrix){

            StringBuilder sb = new StringBuilder();

            for(char c : row){
                
                if(c == 'Q') sb.append(c);
                else sb.append('.');
            }         

            sol.add(sb.toString());    
        }
        
        res.add(sol);
       

        matrix = new char[matrix.length][matrix[0].length];
    }
    
}