
import java.util.Arrays;

//LC 322 : https://leetcode.com/problems/coin-change/


class CoinChange{
    
    /*Logic : break down into subproblems, find how many ways to make amount 0, then 1, then 2.....
            Say we have coins {1,2,5}
            So to make 10: dp[10]: we can use dp[9] + 1$ or dp[5] + 5$ or dp[8] + 2$.
             --> whether we pick, 1, 2 or 5, we add one to coin sum.(if we pick)
            so , we use a variable min to keep track of which count is min between all coin combinations  
    */
    
    public int coinChange(int[] coins, int amount) {

        // Cointains min num of coins to create the amount
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);    
        
        dp[0] = 0; // 0 ways of making zero amount

        // Each amount 
        for(int i = 1; i < dp.length; i++){

            int min = Integer.MAX_VALUE; // to pick min out of three coin combinations

            // For each coin 
            for(int j = 0; j < coins.length; j++){

                if(i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE){

                    min = Math.min(min, dp[i - coins[j]] + 1); // + 1 coz we are choosing to pick this coin, which adds 1 to count 

                }                

            }

            dp[i] = min;

        }

        return (dp[amount] != Integer.MAX_VALUE ) ? dp[amount] : -1;
    }
    
    
    public static void main(String[] args){
      
      int[] coins = {1,2};  int amount = 3; // op : 2
      //int[] coins = {2}; int amount = 3; //op -1
      //int[] coins = {1}; int amount = 0; // op 0
      
      CoinChange obj = new CoinChange();  
      int result = obj.coinChange(coins,amount);
      System.out.println(result);
    }
    
}