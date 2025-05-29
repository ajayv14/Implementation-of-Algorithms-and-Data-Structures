
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

   public static void main(String[] args){
      
      Fibonacci obj = new Fibonacci();
        System.out.println( obj.fibonacci(9) );      
   }


}