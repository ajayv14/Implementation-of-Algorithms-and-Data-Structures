class WordBreakSolution {
    public boolean wordBreak(String s, List<String> wordDict) {
     /*using dp*/
      
        boolean[] dp = new boolean[s.length() + 1];
        
        dp[0] = true; // initialize to support algorithm
        
        for(int i=1;i<=s.length();i++){
            
            for(int j=0;j<i;j++){
                         
                //dp of j -- previous
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                }                
                
            }            
            
        }
        
        return dp[s.length()];      
        
    }
}