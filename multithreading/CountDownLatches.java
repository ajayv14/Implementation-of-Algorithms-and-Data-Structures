// credits : www.caveofprogramming.com

import java.util.concurrent.CountDownLatch; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Chapter 5 

/**
 * CountDownLatches demonstrates the use of Java's CountDownLatch for thread synchronization.
 * This class creates multiple threads that count down a latch, 
 * and the main thread waits until all threads have completed their countdown before proceeding.
 */

class CountDownLatches implements Runnable {
   
   private CountDownLatch latch;
   
   public  CountDownLatches(CountDownLatch latch){
      this.latch = latch;   
   }

   
   public void run(){
      
      System.out.println("Thread started");
      
      try{
         Thread.sleep(5000);
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
         executor.submit(new CountDownLatches(latch));
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