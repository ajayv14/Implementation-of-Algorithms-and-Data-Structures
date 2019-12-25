// credits: www.caveofprogeramming.com
/*overview, we have a main app which calls the methods produce() and consume() inside two diff threads using the object of the Processor class.  The produce and consume are synchrionized on a common class object
  We demonstrate how the wait() and notify() can be used to transfer lock from one thread to another. For multiple threads waiting, can use notifyAll();
  
  Here we create a FIFO queue with limit of 10, the produce() notifies the consunme() when new item is insteted. Also produce() waits for consumer() to remove an item when queue is full;
  Similarly, consume() notifies the prosuce() when an element is reoved and consume() wait for a notify from produce() when there is no item in the queue-- size == 0 

*/

import java.util.Scanner;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class ThreadDemo9_ReEntrantLocks {       
//    main app 
  
   public static void main(String[] args){
      
      final Runner runner = new Runner();
         
      
      Thread t1 = new Thread(new Runnable(){
      
         public void run(){
            try{
               runner.firstThread();
            }
            catch(Exception e){
               e.printStackTrace();
            }
         }
      
      });
      
      
      Thread t2 = new Thread(new Runnable(){
         
         public void run(){
            
            try{
               runner.secondThread();
            }
            catch(Exception e){
               e.printStackTrace();
            }
         
         }
      
      });
      
      t1.start();
      t2.start();
      
      try{
              
        t1.join();
        t2.join();       
      }      
      catch(Exception e){
          e.printStackTrace();
      }
      
      runner.finished();
      
        
   }   
}



class Runner {
      
      private int count = 0;
      private Lock lock = new ReentrantLock();
      private Condition condition = lock.newCondition(); // To use equivalend of wait() and notify() ( await() and signal() ), we need a condition on lock 
         
      public void increment(){
          
          for(int i = 0; i < 10000; i++ ){
               count++; 
          }           
      }   
          
      public void firstThread() throws InterruptedException {
         /*lock.lock();  // bad coding practice as an exception will lock the thread forever
         increment();
         lock.unlock();*/
         
         lock.lock();
         
         System.out.println("Waiting.......");
         
         condition.await(); // await() and signal() can be called only with lock object, same as using wait and notify insise synchronized block only
         
         System.out.println("woken up by signal() ");   
         
         try{
             increment();
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock.unlock();
         }
         
                              
      }      
      
      public void secondThread() throws InterruptedException{
         
         Thread.sleep(1000); // a small delay to makes firstThread gets an initial lead
         
         lock.lock();
         
         Scanner sc = new Scanner(System.in);
         
         System.out.println("waiting for a return key and press");
         
         sc.nextLine();
         
         System.out.println("Got the return key");
         
         condition.signal(); // does not necessarily unlock the locked thread, other thread can re acquire lock only after this thread unlocks
                           
          try{
            
            increment();
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock.unlock();
         }
            
      }
      
      public void finished(){
         System.out.println(" count :  "+ count);
      }

}