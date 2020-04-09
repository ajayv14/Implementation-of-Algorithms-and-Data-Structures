
class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
       
        int max = 0;
        int min = Integer.MAX_VALUE;
        int profit = 0;
        
        for(int i = 0; i< prices.length; i++){            
            
            
            if(prices[i] < min){
                min = prices[i]; 
            }
            
            else if(prices[i] - min > profit){
                max = prices[i];
                profit =  max - min;       
            }        
            
        }
        
        return profit;
        
    }
}


// v[2,1,2,1,0,1,2] // o/p expected 2