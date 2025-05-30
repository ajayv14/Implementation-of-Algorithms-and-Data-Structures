

// LC 62 : https://leetcode.com/problems/unique-paths/

import java.util.Arrays;

class UniquePaths {
    
    /*ref: https://www.youtube.com/watch?v=GO5QHC_BmvM -- Thushar Roy*/        
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];         
        
        Arrays.fill(dp[0],1);
        
        for(int r = 1; r < m; r++){
            dp[r][0] = 1;
        }


        for(int i = 1; i < m; i++){

            for(int j = 1; j < n; j++){

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
    
        return dp[m - 1][n - 1];
    }

}