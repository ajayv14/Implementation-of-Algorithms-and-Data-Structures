
/*  credits: AlgoExpert : https://www.youtube.com/watch?v=Pw6lrYANjz4
     
    Leetcode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
               // with slight mod : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/


    Logic : No so easy to explain.. DP setup : we draw a table/2D array, Transactions t - row and days d as columns. 
            
            We have a choice between selling(completing a transaction) or just hold(skip today). We take the max profit value btw selling vs skipping.

            skipping : just choose value of previous day : dp[t][d - 1]
            Transaction : Here we have a number of cases, as we can make k transactions. Lets assume k = 2 and we are at transaction 2. 
            We can check for every day before today and see when we cud have made the transaction 1 to make most profit.
            we have a loop running from element at index 0 to element at previous day index.
              at each step we calculate:  
                          Today's value of stock + ( - value of element at index + value of element at transaction 1)          



*/

class BuyAndSellStock2Transactions {

    public int maxProfit(int[] prices) {
        
        if(prices == null || prices.length <= 0) return 0;
        
        int transactions = 2; //number of transactions
        
        int[][] dp = new int[transactions + 1][prices.length]; // t + 1 to account for 0 transactions
        
        
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        
        for(int j = 0; j < dp[0].length; j++){
            dp[0][j] = 0;
        }
        
        // t - transactions, d = days
        for(int t = 1; t < dp.length; t++){
            
            for(int d = 1; d < dp[0].length; d++){
                
                
                int noTransaction = dp[t][d - 1]; // use the last day's value, coz no transaction is performed
                
                int maxSellProfit = 0;
                
                for(int i = 0; i < d; i++){
                    
                    int curProfit = prices[d] + ( - prices[i] + dp[t - 1][i]  );
                    
                    maxSellProfit = Math.max(maxSellProfit, curProfit);
                    
                }
                
                dp[t][d] = Math.max(noTransaction, maxSellProfit);
                
            }            
            
        }
          
        return dp[dp.length - 1][dp[0].length - 1];       
        
    }
}