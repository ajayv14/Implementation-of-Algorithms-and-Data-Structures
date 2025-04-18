// credits : JAVAAID  : https://www.youtube.com/watch?v=Lj90FqNCIJE
class LongestCommonSubstring {

   public int longestCommonSubstring(String s1, String s2){
   
      int m = s1.length();
      int n = s2.length();
      
      char[] X = s1.toCharArray(); // for convenince
      char[] Y = s2.toCharArray();
      
      int longest = 0;
      
      int[][] dp = new int[m + 1][n + 1];
      
      for(int i = 0; i <= m; i++){
         for(int j = 0; j <= n; j++){
            
            if(i == 0 || j == 0){
               dp[i][j] = 0;    // set first row and col to 0            
            }
            
            else if(X[i - 1] == Y[j - 1]){
               dp[i][j] = dp[i - 1][j - 1] + 1;  // top diagonal + 1
               longest = Math.max(longest,dp[i][j]); // if more than one substring exists 
            }
            
            else{
               dp[i][j] = 0;
            }         
         }      
      }   
      
      return longest;   
   }
   
   
   
   
   public static void main(String[] args){
      
      String s1 = "javaisfun";
      //String s2 = "javaisnotatallfun";
      String s2 = "funisfun";
      //String s2 = "poda";      
      LongestCommonSubstring obj = new LongestCommonSubstring();
      int res = obj.longestCommonSubstring(s1,s2);
      System.out.println(res);           
   }
}