//credit: https://www.youtube.com/watch?v=E-kjYOZEBxY
import java.util.LinkedList;
import java.util.Queue;

/*on easier side*/
class MovingAverage {
   
   Queue<Integer> q;
   int maxWindowSize;
   double sum = 0;
   
   
   public MovingAverage(int size){
      
      this.maxWindowSize = size;
      this.q = new LinkedList<>();    
   }


   public double next(int val){
      
      if(q.size() == maxWindowSize){
          sum -= q.poll();          
      }
      
      sum += val; 
      q.offer(val);           
      
      return sum / q.size();     
   }
   
   
   public static void main(String[] args){
      MovingAverage obj = new MovingAverage(3);
           System.out.println(obj.next(2));
           System.out.println(obj.next(4));
           System.out.println(obj.next(6));
           System.out.println(obj.next(8));
           System.out.println(obj.next(10));
   }

}