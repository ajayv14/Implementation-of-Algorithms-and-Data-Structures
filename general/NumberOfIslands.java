class NumberOfIslands{
   // find the number of chain-islands 
           
      
      public int countMaxIslands(int[][] arr){
         
         int maxIslands = 0;
         
         if(arr == null || arr.length <= 0) return 0;  
         
         int m = arr.length;
         int n = arr[0].length; 
         //now find a starting point to an island i.e 1
         
         for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
               
               if(arr[i][j]== 1) maxIslands += sinkIslands(arr, i, j);                             
            }         
         }        
         return maxIslands;      
      }
            
      
      private int sinkIslands(int[][] arr, int i, int j){
         
         if(i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == 0 ) return 0;
         
         arr[i][j]= 0; // sink the island
         //check if neighbors are islands and if they belong to cluster
         sinkIslands(arr, i + 1, j);
         sinkIslands(arr, i - 1, j);
         sinkIslands(arr, i, j + 1);
         sinkIslands(arr, i, j - 1);
         // if all true, then return 1
         return 1;
         
      }
      
      
       public static void main(String[] args){
         
        int[][] arr =  {{1, 0, 0, 0},
                        {1, 0, 1, 1},
                        {1, 0, 1, 1},
                        {0, 0, 0, 0}};
                     
        NumberOfIslands obj = new NumberOfIslands();
        
        System.out.println(obj.countMaxIslands(arr));  
             
      }
}