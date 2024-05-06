// credits : www.caveofprogramming.com

import java.util.concurrent.CountDownLatch; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadDemo5_CountDownLatches implements Runnable {
   
   private CountDownLatch latch;
   
   public  ThreadDemo5_CountDownLatches(CountDownLatch latch){
      this.latch = latch;   
   }
   
   public void run(){
      
      System.out.println("Thread started");
      
      try{
         Thread.sleep(4000);
      }
      catch(Exception e){
         e.printStackTrace();
      }
      
      latch.countDown(); // counts down latch by 1      
   }  
   
   

   public static void main(String[] args){
      
      CountDownLatch latch = new CountDownLatch(3);
      
      ExecutorService executor = Executors.newFixedThreadPool(3);
      
      for(int i = 0; i < 3; i++ ){
         executor.submit(new ThreadDemo5_CountDownLatches(latch));
      }
      
      
       try{
         latch.await(); // wait until countDown latch has counted down to zero
      }
      catch(Exception e){
         e.printStackTrace();
      }
      
      System.out.println("completed");
            
   }



}