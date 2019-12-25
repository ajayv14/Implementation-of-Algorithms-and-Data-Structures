// credits: www.caveofprogeramming.com
/*overview, we have a main app which calls the methods produce() and consume() inside two diff threads using the object of the Processor class.  The produce and consume are synchrionized on a common class object
  We demonstrate how the wait() and notify() can be used to transfer lock from one thread to another. For multiple threads waiting, can use notifyAll();
  
  Here we create a FIFO queue with limit of 10, the produce() notifies the consunme() when new item is insteted. Also produce() waits for consumer() to remove an item when queue is full;
  Similarly, consume() notifies the prosuce() when an element is reoved and consume() wait for a notify from produce() when there is no item in the queue-- size == 0 

*/

import java.util.Scanner;
import java.util.LinkedList;

class ThreadDemo8_LowLevelSync {       
//    main app 
  
   public static void main(String[] args){
      
      final Processor processor = new Processor();   
      
      Thread t1 = new Thread(new Runnable(){
      
         public void run(){
            try{
               processor.produce();
            }
            catch(Exception e){
               e.printStackTrace();
            }
         }
      
      });
      
      
      Thread t2 = new Thread(new Runnable(){
         
         public void run(){
            
            try{
               processor.consume();
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
   }   
}



class Processor {
      
      private LinkedList<Integer> list = new LinkedList<Integer>();
      private final int LIMIT = 10;
      
      private Object lock = new Object();
      
      public void produce() throws InterruptedException {
      
         int value = 0;
         
         while(true){
            
            synchronized(lock){
            
               while(list.size() == LIMIT){ // using while and not if loop, to check continuously 
                  lock.wait();  // call the wait() on the lock object only  
               }
            
               list.add(value++);
               
               lock.notify();
            }           
         }        
            
      }
      
      
      public void consume() throws InterruptedException{
         
         while(true){
            synchronized(lock){
               
               while(list.size() == 0){
                  lock.wait();   // keep waiting till list has some values entered in it                
               }
                
               System.out.print(" list size : " + list.size());
               int value =list.removeFirst();
               System.out.println(" value : "+ value);
                                
               lock.notify();
                           
            }  
            
            Thread.sleep(1000);         
            
         }
               
      } 



}