package com.app.matrix;

class UniquePathsII {

/* Intution : For ceach cell, check how many unique paths are possible.

    Algo - Initialize fist cell in dp[][] to 1 if first cell  obstacleGrid[0][0] is NOT an obstacle.

    For each cell, calculate unique paths - Each cell can have a path coming from top of it and another from left of it.
    
    */


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) return 0;

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];  

        dp[0][0] = 1;  

        int top  = 0;
       

        for(int row = 0; row < obstacleGrid.length; row++){

            for(int col = 0; col < obstacleGrid[0].length; col++){

                if(row > 0 && col > 0 && obstacleGrid[row][col] == 0){

                   dp[row][col] += dp[row - 1][col] + dp[row][col - 1];
                }

                else if(col > 0 && obstacleGrid[row][col] == 0){

                    dp[row][col] += dp[row][col - 1]; 

                }

                else if(row > 0 && obstacleGrid[row][col] == 0){
                    dp[row][col] += dp[row - 1][col];
                }                 


            }

        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
        
    }


}
