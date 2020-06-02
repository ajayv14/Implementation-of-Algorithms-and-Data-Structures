//credits: https://www.youtube.com/watch?v=oNI0rf2P9gE

/**    All Pairs Shortest Paths Algorithm   
  *  Compute shortest distances from each node to every other node using All pairs shortest path algo.   
  *  Find the node which has least num of paths within the threshold  
**/

class FindTheCity {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
                
        int[][] dp = new int[n][n];
        
        /*fill the array with Integer-Max value*/
        for(int[] d : dp){
            Arrays.fill(d,Integer.MAX_VALUE);
        }       
       
        /*fill the 2-D matrix with distance values from each node to the other*/
        
        for(int[] edge : edges){            
            int i = edge[0];
            int j = edge[1];            
            dp[i][j] = edge[2];
            dp[j][i] = edge[2];
        }       
        
        /*All pairs shortest path algorithm*/
        for(int k = 0; k < n; k++ ){            
            
            for(int i = 0; i < n; i++){                
                for(int j = 0; j < n; j++){
                                                        
                    /*The if condition is required as anything added to Integer.MAX_VALUE will produce -ve results*/
                    if(dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    } 
					    
                }                
            }        
        } 
        
        /*take the city with smallest */
        // int[] cityCount[ = new int[n];
        
        int smallestIndex = 0;
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < dp.length; i++){
            
            int count = 0; // cities below or equal to threshold
            for(int j = 0; j < dp[0].length; j++){
                
                if(i != j && dp[i][j] <= distanceThreshold) count++;                
            }            
            
            //System.out.print(count + " ");
            if(count <= min){
                min = count;
                smallestIndex = i;        
            }
        }        
        
        /*pick the smallest one from the array, if there is atie, automatically the later index with smallest num will be returned
              
        for(int i = 0; i < cityCount.length; i++){
            
            if(cityCount[i] >= 0 && cityCount[i] <= smallestCount){
                smallestCount = cityCount[i];
                smallestIndex = i;
            }
        }*/
        return smallestIndex;
    }
}