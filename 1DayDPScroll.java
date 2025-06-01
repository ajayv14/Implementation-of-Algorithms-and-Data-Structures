public class 1DayDPScroll {



   

}


class Fibonacci{
//0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144........
// Fn = Fn-1 + Fn-2  // F0 = 0 and F1 = 1


  //Using recursion with time complexity : T(n) = T(n - 1) + T(n - 2) 
  public int fibonacciRecursion(int n){
      
      if(n <= 1) return n;
      
      return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2); 
   
   }
      
      
  // Using dynamic programming using array
   
   public int fibonacciDp(int n){
      
      int[] dp = new int[n + 2]; // coz in case of 0, new int[0] or int[1] will lead to exception
      
      dp[0] = 0;
      dp[1] = 1;
      
      for(int i = 2; i <= n; i++){
         
         dp[i] = dp[i - 1] + dp[i - 2];
                        
      }
      
      return dp[n];
      
   } 
   
   
   // Further space - optimized soln above by using just two variables to store previous n - 1 and n - 2 values
   
   public int fibonacci(int n){
      
      int a = 0, b = 1, c = 0;
      
      if(n <= 1 ) return n;
      
      for(int i = 2; i <= n; i++){
         
         c = a + b;
         a = b;
         b = c;
      } 
      
      return c; 
      
      
   } 

}




class ClimbStairs{

      public int climbStairs(int n) {
        
        //F(n) = F(n - 1) + F(n - 2)
        /* store n - 1 and n - 2 into two variables, as we just require previous two values */
        
        int a = 1, b = 2, c = 0; // a, b are two possible ways to reach a step
        
        if( n <= 2) return n; // n = 1, just one way and n == 2, just 2 ways are possible
        
        // for 3 steps onwards
        for(int i = 3; i <= n; i++){
            
            c = a + b; // F(n - 1 ) + F (n - 2)
            a = b;
            b = c;
        }      
        return c;        
    }      



     /*optimal cost depends on the previous step and the one before that*/

     public int minCostClimbingStairs2(int[] cost) {
                  
      int[] dp = new int[cost.length];
      
      dp[0]=cost[0];
      dp[1]=cost[1];
      
      for(int i = 2;i < cost.length; i++){
          
            dp[i] = cost[i] + Math.min(dp[i - 1],dp[i - 2]);
      }        

      return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
      
  }
      
      public static void main(String[] args){
        
        
        ClimbStairs obj = new ClimbStairs();
        
        System.out.println(obj.climbStairs(3));
      
    }

}



 //LC 322 : https://leetcode.com/problems/coin-change/

class CoinChange {
    
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
}
    


class MinCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
    
        int lastDay = days[days.length - 1];
        
        // Tracks min cost for each day
        int[] dp = new int[lastDay + 1];
        
        Arrays.fill(dp, 0); // Cost is zero if we don't travel on that day 

        int idx = 0;
        
        // Each day
        for(int i = 1; i < dp.length; i++){
            
            // Non travel days
            if(i < days[idx]) dp[i] = dp[i - 1];

            else {

               idx++; 
               // Optimize for 1. 7 or 30 day pass    

               // To avoid i - 1,7,30 being negative 
               // int oneDayBefore = Math.max(0, i - 1); i starts at 1, so don't need this condition
               int oneDayBefore = i - 1;
               int sevenDayBefore = Math.max(0, i - 7);
               int thirtyDayBefore = Math.max(0, i - 30);  


               dp[i] = Math.min(dp[oneDayBefore] + costs[0], 
                    Math.min(dp[sevenDayBefore] + costs[1], dp[thirtyDayBefore] + costs[2]));                                

            }
            
        }

        return dp[lastDay];
                
    }

}


/*Logic : Basically, we can't rob two adjacent homes, so in the dp, we can either choose to include the current home (rob it)
        In doing so, we can only add the sum of home[i - 2] as it is not adjacent.
        or we can skip it...so we will choose [i - 1] home
        So we take max of dp[current - 1]--> The best tally upto the one minus current home vs (current - 2) + curren5t home To make sure we don't           rob adjacent homes.    
*/ 

class HouseRobber {

       
    
    public int rob(int[] nums) {
        // base cases
        if(nums == null | nums.length <= 0 )return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[nums.length];   
        
        // since we are considering i -1 or i -2 + current, to avoid running into index not found exception
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0],nums[1]);
        
        for(int i = 2; i < dp.length; i++){
            
            /* If we skip current home, then we automatically have to choose [i - 1] adjacent home or
            if we choose current home, then we can add items from [i - 2] home as we cant rob adjacent homes*/
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);            
            
        } 
            
        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
        
    }
 
}


//dp - runtime complexity O(M X N), Space - O(M X N)
        // Credits : https://www.youtube.com/watch?v=ASoaQq66foQ


        /** Approach : Comparing "abcd" & "aqce" --> result : 2

            Two possibilities, character match or no match. See example below:
            Starting from left, first character in both strings -> "a"    
            1. "a" and "a" match, so 1 + LCS(previous computed string) --> 1 + LCS(" "," ")
            2. Next, in remaining string "b" and "q" do not match, 
                so --> Max(LCS("ab","a") and LCS("b", "aq")) and so on.
            Follow the above logic at each cell in matrix:
            1. --> dp[i][j] = 1 + dp[i - 1][j - 1];
            2. --> dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            return answere from last rightmost cell.

            
                Matrix :    0 a b c d
                        0     
                        a
                        q
                        c
                        e 
        

            Note : why dp[i][j] = 1 + dp[i - 1][j - 1]; and dp[i][j] = 1 + dp[i + 1][j + 1] during recursion ??
            In Recursion, it is solving subproblems on a stack, so one char at a time.
            In DP, we have 0 padding and we calculate subproblems looking at dp array. 
        
        */
class LongestCommonSubsequence {
    
    
    public int longestCommonSubsequence(String text1, String text2) {

            //Optimization
            if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

            // Form 2D dp matrix
            int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // For 0 padding

            // Zero padding - " " matched with any string will yield 0;    
            for(int m = 0; m < dp.length; m++){
                dp[m][0] = 0;
            }
            // Zero padding - " " matched with any string will yield 0;    
            for(int n = 0; n < dp[0].length; n++){
                dp[0][n] = 0;
            }


            for(int i = 1 ; i < dp.length; i++){

                for(int j = 1 ; j < dp[0].length; j++){

                    /* Note, we compare text1.charAt(i - 1) instead of text1.charAt(i), to compensate  
                    for 0 padding in dp matrix */      
                    
                    // Character is a match scenario: 
                    // i - 1 in string actually represents i in dp array as we start from empty strign at 0th index in dp[]
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)){

                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } 

                    // Character is not a match scenario
                    // Eliminate oen char from text2 - Move up one col
                    // Eliminate one character from text1 -  Move up one row
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }    

            return dp[dp.length - 1][dp[0].length - 1]; 

    }    
        
}



// LC 72 : https://leetcode.com/problems/edit-distance/
class EditDistance {

    //credits : https://www.youtube.com/watch?v=HwDXH35lr0o&list=PLFdAYMIVJQHPXtFM_9mpwwQtIdzP6kxHS&index=13
    // pic : img/edit-distance

    // Bottom up dp
    public int minDistanceBottomUp(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1]; // starting with. " "

        dp[0][0] = 0; // " "


        // " " -> text 2 - Need insertions
        for(int r = 1; r < m + 1; r++){
            dp[r][0] = r;
        } 

        // tex2 -> empty - Need deletions 
        for(int c = 1; c < n + 1; c++){
            dp[0][c] = c;
        }


        for(int i = 1; i < m + 1; i++){

            for(int j = 1; j < n + 1; j++){

                // Same character
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){

                    dp[i][j] = dp[i - 1][j - 1]; // Skip
                }

                else {

                    int top = dp[i - 1][j]; 
                    int left = dp[i][j - 1]; 
                    int leftTopDiag = dp[i - 1][j - 1]; 

                    // 1 operation + min (-,-,-)
                    dp[i][j] = 1 + Math.min(top, Math.min(left, leftTopDiag));

                }
                
            }
        }

        return dp[m][n];
              
    }

}