package com.app.dynamicprogramming;

//credits 1) JAVAAID : https://www.youtube.com/watch?v=aL6cU5dWmWM
//credits 2) Back To Back SWE https://www.youtube.com/watch?v=xCbYmUPvc2Q&t=875s

class Knapsack0_1 {

   /*logic : We make a choice as whether to pick the current item or not:
     visulaize a 2D matrix with - item numbers as rows and capacity from 1 - C as columns. 
      if no, then we retain the previous weight(previous row and same column in a matrix), 
      if yes then we pick the value of current item, 
        then find capacity -(minus) weight of current item, pick the item with the remaining free space(weight) from row above -- as current row is only for outputs relating to current item. 
    */

   public int fillSack(int[] values, int[] weights, int C){
      
      int[][] dp = new int[values.length + 1][C + 1];
      
      /*
      // fill 1st row with zeros --(depecting fake 0th item)
      for(int i = 0; i < dp[0].length; i++){
         dp[0][i] = 0;
      }
      
      // fill 1st col with zeros
      for(int i = 0; i < dp.length; i++){
         dp[i][0] = 0;
      } */     
      
      /*we start from item = 1 as we have a dummy value called 0, which can be within boundary when we call [item - 1] teh first time*/ 
      for(int item = 1; item < dp.length; item ++){
         
         for(int capacity = 1; capacity < dp[item].length; capacity++){
               
               /*optimized way of filling 1st row and 1st column with 0s*/
               if(item == 0 || capacity == 0){
                  dp[item][capacity] = 0;
               }
                               
               if(weights[item - 1] <= capacity){
                  /*if not picked, then we choose previous value -- above row*/
                  int ifCurrentItemNotPicked = dp[item - 1][capacity];                // NOTE NOTE   dp[item - 1][column capacity] signifies the row above
                                                                                      // NOTE NOTE    values[item - 1]  or weights[item - 1] signifies the current item (not previous item) 
                                                                                       //                 as we have added a dummy item 0 in dp array, but values and weights array are provided                                   
                  /*if picked then */                                                  // originally without item 0. 
                  int ifCurrentItemPicked = values[item - 1] + (dp[item - 1][capacity - weights[item - 1]]);
               
                  dp[item][capacity] = Math.max(ifCurrentItemNotPicked,ifCurrentItemPicked);  
                }                    
         }    
      
      }     
      
      printArray(dp);
      
      return dp[dp.length - 1][dp[0].length - 1];
   }  
   
   
   /*test*/
   public static void main(String args[]) {
    
      int values[] = new int[]{60, 100, 120}; 
      int weights[] = new int[]{10, 20, 30}; 
      
      int  C = 50; // total capacity in sack 
      
      Knapsack0_1 obj = new Knapsack0_1();
      int res = obj.fillSack(values, weights, C); 
      System.out.println(res);          
      
   }     


    private void printArray(int[][] dp){
      
      for(int item = 0; item < dp.length; item ++){
         
         System.out.println(" ");
         
         for(int capacity = 0; capacity < dp[item].length; capacity++){               
               System.out.print(dp[item][capacity] + " ");                                    
         }          
      }
      
      System.out.println("");

    }


}