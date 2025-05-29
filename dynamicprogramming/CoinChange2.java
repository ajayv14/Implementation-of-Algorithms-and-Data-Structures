import java.util.Arrays;


// LC 518 : https://leetcode.com/problems/coin-change-ii

public class CoinChange2 {


    // TLE
    // Recursion     
    public int change(int amount, int[] coins) {

      return  numOfWays(amount, coins,0, "");    
      
        
    }

    private int numOfWays(int amount, int[] coins, int idx, String str){

        if(amount < 0) return 0;

        if(amount == 0) {
            
            System.out.println(str);

            return 1;
        } 

        int count = 0;

        //  [1,2] and [2,1] shouldn't be counted as different, hence only use coins from current index forward 
        for(int i = idx; i < coins.length; i++){

            str += coins[i];
                      
            count += numOfWays(amount - coins[i], coins, i , str );
        }  

        return count;      

    }

}



class CoinChange2Memoization {

    // Memoization     
    public int change(int amount, int[] coins) {

      int[][] dp = new int[coins.length + 1][amount + 1];  

      for(int[] row : dp){
        Arrays.fill(row, -1);  
      }  
      
      return  numOfWays(amount, coins,0, dp);    
       
    }

    private int numOfWays(int amount, int[] coins, int idx, int[][] dp){

        if(idx >= coins.length || amount < 0) return 0;

        if(amount == 0) return 1;       

        if(dp[idx][amount] != -1) return dp[idx][amount];
                   
                      
        int skipCoin = numOfWays(amount,coins, idx + 1, dp);
        int useCoin = numOfWays(amount - coins[idx],coins, idx, dp);
         
        dp[idx][amount] = skipCoin + useCoin;       

        return dp[idx][amount];
    }
}




class CoinChange2BottomUp {

    // Bottom up
    // credits : https://www.youtube.com/watch?v=DJ4a7cmjZY0

    /*  amount
         0 1 2 3 4 5 
c      0 1 0 0 0 0 0 - base case
o      1 1 
i      2 1 
n      5 1 
s
    */

    // At each step, make a decision to skip coin or use coin to compute new way
    public int change(int amount, int[] coins) {

      int[][] dp = new int[coins.length + 1][amount + 1];  
      
      
      // 1s in first col of all rows (num of ways to make 0 amount - base case)
      for(int r = 0; r < dp.length; r++){
        dp[r][0] = 1;  
      }
      
      for(int i = 1; i < dp.length; i++){

        for(int j = 1; j < dp[0].length; j++){

            // To skip coin or add new way

            if(coins[i - 1] > j) {
                 // skip coin
                 dp[i][j] = dp[i - 1][j];
            }
           
           // Ways without current coin + ways including current coin
           else dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
        
            // dp[i][j - coins[i - 1]]; ->

            /*

            - At row = 2, we have couin 2.

            - chose not to skip coin and calculate for amount 4 ??

            - So 4 - 2(coin) = 2, so pick what is in col -> 2 in the same row -> i and add 1 -> new way.

            */        
        }
      }

      return dp[dp.length - 1][dp[0].length - 1];  

    }

    
}