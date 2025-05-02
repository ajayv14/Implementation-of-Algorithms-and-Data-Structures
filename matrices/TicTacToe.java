class TicTacToe {

    // LC 348 https://leetcode.com/problems/design-tic-tac-toe/

    /**
        Approach : 

        Brute force. Check horizontal for each row, vertical for each co. left and right diagonal

        Optimized :  Player 1 and 2. length -> length of n x n array.
         Basically keep a variable to track left and right diagonal. 
        In the end, it should be + length or - length, otherwise, both players have placed bets on some of cells in diagonal,   hence cant win.

        Same approach goes to each row and column, but instead of separate variables, we perform increment in the 2D array itself to compute winner.

        We cant use 2D matrix to track diagoinals as it will overwrite the row - col calculations
     
     */

    int rightDiagonal = 0, leftDiagonal = 0; 

    int[] rowArr;
    int[] colArr;

    int len;

    public TicTacToe(int n) {
        
        rowArr = new int[n];
        colArr = new int[n];
        len = n;
    }
    
    public int move(int row, int col, int player) {
        
        int p = (player == 1) ? 1 : -1;         

        rowArr[row] += p;
        colArr[col] += p;

        if(row == col){
            rightDiagonal += p;
        }

        // leftDiagonal
        if(col == (colArr.length - row - 1)){
            leftDiagonal += p;
        }

       
        if(Math.abs(rowArr[row]) == len || Math.abs(colArr[col]) == len 
        || Math.abs(rightDiagonal) == len || Math.abs(leftDiagonal) == len){

            return player; // Not p
        }


        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */


class TicTacToeBrute {

    // For ref - temproarily from editorial

    private int[][] board;
    private int n;

    public TicTacToe(int n) {
        board = new int[n][n];
        this.n = n;
    }

    public int move(int row, int col, int player) {
        board[row][col] = player;
        // check if the player wins
        if ((checkRow(row, player)) ||
            (checkColumn(col, player)) ||
            (row == col && checkDiagonal(player)) ||
            (col == n - row - 1 && checkAntiDiagonal(player))) {
            return player;
        }
        // No one wins
        return 0;
    }

    private boolean checkDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][row] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][n - row - 1] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][col] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int row, int player) {
        for (int col = 0; col < n; col++) {
            if (board[row][col] != player) {
                return false;
            }
        }
        return true;
    }



} 