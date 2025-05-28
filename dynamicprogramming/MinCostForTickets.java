import java.util.Arrays;

public class MinCostForTickets {

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
