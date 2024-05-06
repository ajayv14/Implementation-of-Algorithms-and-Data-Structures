class CountAllSubstrings {
    public int countSubstrings(String s) {
        
        
    /*Dp solution - can be done without dp also*/
        
        /*pointer i traverses the string from left to right, and ptr j traverses from j to end (left from j   to )*/
        
              
        int count = 0;
        
        int n = s.length();
        
        boolean[][] dp = new boolean[n][n];
        
        
        for(int i = n - 1; i >= 0; i-- ){
            
            for(int j = i; j < n ; j++){
                
                /*dp[i + 1][j - 1] -- previous i and j*/ 
                
               dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]); 
                   
                                          
                
                if(dp[i][j] == true) count++;
                
                
            }           
            
            
        }
        
        
        
        
        
       return count; 
        
        
        
        
        
        
    }
}