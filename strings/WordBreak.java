
// LC : 139 : https://leetcode.com/problems/word-break/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

    /**
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false

        Here, no combination of words exist in wordDict that can perfectly recreate the input string s.
        "cats" + "dog" will require "an" in wordDict in order to recreate s
        "cats" + "and" will require "og" in wordDict in order to recreate s
        "cat" + "sand" will require "og" in wordDict in order to recreate s
     */

class WordBreak {
    
    
    //Classic BFS - Non-optimized soln Time Limit Exceeded
    public boolean wordBreak(String s, List<String> wordDict) {

        int right = 0;
        int left = 0;
        // Contains left pointer 
        Queue<Integer> q = new LinkedList<>();
        
        q.add(left); // 0           

        while(!q.isEmpty()){

            left = q.remove();            
            
            // <= s.length() as substring(left, right) doesn't include char at right
            for(right = left + 1; right <= s.length(); right++){

                String sub = s.substring(left, right);

                System.out.println(sub);

                if(wordDict.contains(sub)){

                    if(right == s.length()) return true;

                    q.add(right);
                } 
            }           
        
        }

        return false;
        
    }
    
    
    
    
    
    
    public boolean wordBreakWithDp(String s, List<String> wordDict) {
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