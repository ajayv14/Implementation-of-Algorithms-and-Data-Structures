


public class UniquePaths2 {

    // LC 63

    // https://leetcode.com/problems/unique-paths-ii/

    /* Intution : For ceach cell, check how many unique paths are possible.

    Algo - Initialize fist cell in dp[][] to 1 if first cell  obstacleGrid[0][0] is NOT an obstacle.

    For each cell, calculate unique paths - Each cell can have a path coming from top of it and another from left of it.

    Runtime - O(m * n)

    Space : O(m * n)
    
    */

   public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(m == 0 || n == 0 || obstacleGrid[0][0] == 1) return 0;

        int[][] dp = new int[m][n];  

        dp[0][0] = 1;                

        for(int i = 0; i < m; i++){

            for(int j = 0; j < n; j++){

                // Since we haven't prefilled first row and col with '1'. As it may be blocked with '1'.    
                
                if(i > 0 && j > 0 && obstacleGrid[i][j] == 0){

                   dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
                }

                // 0th row processing (no path from top)
                else if(j > 0 && obstacleGrid[i][j] == 0){

                    dp[i][j] += dp[i][j - 1]; 

                }

                // 0th col processing (no path from left)
                else if(i > 0 && obstacleGrid[i][j] == 0){
                    dp[i][j] += dp[i - 1][j];
                }                 


            }

        }
        return dp[m - 1][n - 1];
        
    }


}
