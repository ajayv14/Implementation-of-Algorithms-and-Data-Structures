// credits : Kevin Naughton Jr. https://www.youtube.com/watch?v=Pj9378ZsCh4

package com.app.array2d;


public class WallsAndGates {
/*10000 --> empty rooms, -1 --> Barriers, 0 ---> gates -- find the distance to nearest gates from all rooms--10000 */

/*logic - when you find a gate --> 0, dfs and find rooms nearby, update the distance from gate*/

   public void wallsAndGates(int[][] rooms){
      
      for(int i = 0; i < rooms.length; i++){
         for(int j = 0; j < rooms[i].length; j++){
            
            // when we find a gate
            if(rooms[i][j] == 0){
               dfs(i,j,0,rooms);
            }         
         }
      }   
   
   }
   
   
   private void dfs(int i, int j, int count, int[][] rooms){
      //bounds // rooms[i][j] < count --> already a more closer gate is found
      if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < count ) return;             
      
      rooms[i][j] = count;
      dfs(i + 1,j, count + 1, rooms);
      dfs(i - 1,j, count + 1, rooms);
      dfs(i,j + 1, count + 1, rooms);
      dfs(i,j - 1, count + 1, rooms);     
      
   }



   /*test*/
   public static void main(String[] args){
   
      //input array
      int[][] arr = {{10000, -1, 0, 10000},              
                      {10000, 10000, 10000, -1},
                        {10000,-1, 10000, -1},
                         {0, -1,  10000, 10000}};
    
    /*expected o/p                     
                           3 ,-1 ,0 ,1 ,
                        2 ,2 ,1 ,-1 ,
                        1 ,-1 ,2 ,-1 ,
                        0 ,-1 ,3 ,4 ,
   */
   
      WallsAndGates obj = new WallsAndGates();
      obj.wallsAndGates(arr); 
      
      for(int i = 0; i < arr.length; i++){
         for(int j = 0; j < arr[i].length; j++){
            System.out.print(arr[i][j]+ " ,");                     
         }
         System.out.println("");
      }  
   
   }
}