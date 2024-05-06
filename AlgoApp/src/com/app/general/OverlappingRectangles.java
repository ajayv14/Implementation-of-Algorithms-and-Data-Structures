class OverlappingRectangles{

   public boolean checkOverlap(Point l1, Point r1, Point l2, Point r2){
         
         /*4 cases of Non-Overlapping (draw and visualize for better understanding) : 
         
         R2 is left of R1 : compare x-axis values of R1 top left to R2 bottom right
         R2 is right of R1 : compare x-axis values of R2 top left to R1 bottom right 
         R2 is above R1 : compare y-axis values of R2 top left to R1 bottom right
         R2 is below R1 : compare y-axis values of R1 top left to R2 bottom right */
                
         // case 1 || case 2 || case 3 || case 4 
         if(l1.x > r2.x || l2.x > r1.x || l2.y < r1.y  || l1.y < r2.y ) return false;
                           
         return true;         
   }
   
      public static void main(String[] args){
      
      Point l1 = new Point(0,10);
      Point r1 = new Point(10,0);
      Point l2 = new Point(5,5);
      Point r2 = new Point(15,0);
      
      OverlappingRectangles obj = new OverlappingRectangles();
       System.out.println(obj.checkOverlap(l1, r1, l2, r2));      
      
   } 
}


 class Point{
     
      int x;
      int y;
      
      public Point(int x, int y){
         this.x = x;
         this.y = y;
      }   
 }    