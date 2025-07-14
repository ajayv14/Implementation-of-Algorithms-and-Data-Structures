
// LC 931 : https://leetcode.com/problems/minimum-falling-path-sum



class MinimumFallingPathSum {
    
    public int minFallingPathSum(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        // Last row - bottom is same as original array
        for(int k = 0; k < n; k++){
            dp[m - 1][k] = matrix[m - 1][k];
        }

        // Bottom up to first row, not 0th row
        for(int i = m - 2; i >= 0; i--){

            for(int j = 0; j < n; j++){

                 if(j == 0){

                    int down = dp[i + 1][j];
                    int downDiag = dp[i + 1][j + 1];

                    dp[i][j] =  matrix[i][j] + Math.min(down, downDiag);
                 }

                 else if (j == n - 1) {

                    int down = dp[i + 1][j];
                    int downAntiDiag = dp[i + 1][j - 1];
                    
                    dp[i][j] =  matrix[i][j] + Math.min(down, downAntiDiag);
                 }

                 else {

                     int down = dp[i + 1][j];
                     int downDiag = dp[i + 1][j + 1];
                     int downAntiDiag = dp[i + 1][j - 1];

                     int tempMin = Math.min(down, downAntiDiag);

                     dp[i][j] =  matrix[i][j] +  Math.min(tempMin, downDiag);
                 }
            }
        }


        int minPath = Integer.MAX_VALUE;

        for(int k = 0; k < n; k++){
            
            minPath = Math.min(minPath, dp[0][k]); 
        }

        return minPath;
        
    }
}