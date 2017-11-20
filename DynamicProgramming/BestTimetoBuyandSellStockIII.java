class BestTimetoBuyandSellStockIII {
    public int maxProfit(int[] prices) {
       
        
        
        /*ref http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/*/
        /*Thushar Roy*/

        /*base case*/
        
        
        
        
        /*k = 2*/
        
        int K = 2;
        
        if(K == 0 || prices.length <= 0) return 0;        
        
        int N = prices.length;
        
        
        int[][] dp = new int[K + 1][N];
        
        
        /**/
        
        
        for(int i = 1; i < K + 1 ; i++){          
            
            for(int j = 1; j < N; j++){
                
              int maxSoFar = Integer.MIN_VALUE;
                
                
                for(int m = 0; m < j; m++){
                    
                    maxSoFar = Math.max(maxSoFar, prices[j] - prices[m] + dp[i - 1][m]);
                    
                }
                
                dp[i][j] = Math.max(dp[i][j - 1], maxSoFar);
                
            }               
            
        }
        
       // printArray(dp);
        
        return dp[K][N - 1];
        
    }
    
    /*
    public static void printArray(int[][] a){
        
        for(int i = 0; i < a.length; i++){
            
            for(int j = 0; j <a[0].length; j++){
                
                
                System.out.print(a[i][j] + " ");
                
                
            }
            
            System.out.println(" ");
        }
        
        
    }
    
  */
    
    
    
} 
    