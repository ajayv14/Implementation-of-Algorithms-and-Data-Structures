
// credits: www.caveofprogeramming.com
/*overview, we have a main app which calls the methods produce() and consume() inside two diff threads using the object of the Processor class.  The produce and consume are synchrionized on a common class object
  We demonstrate how the wait() and notify() can be used to transfer lock from one thread to another. For multiple threads waiting, can use notifyAll
*/

import java.util.Scanner;


// Chapter 7
class WaitAndNotify {       

   
   
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
      
      public void produce() throws InterruptedException{
         
         synchronized(this){  // using intrinsic lock of the class 
            
            System.out.println("producer is running");   
            wait();
            System.out.println("producer resumed");
         }
      
      }
      
      
      public void consume() throws InterruptedException{
         
         Scanner sc = new Scanner(System.in); 
          
         Thread.sleep(2000);
         synchronized(this){
         
            System.out.println("waiting for return key press");
            sc.nextLine();
            System.out.println("return key pressed"); 
            notify(); // can be called only withing synchronized code block, used to notify one other thread which is waiting on the same lock, more than one thread, use notifyAll() 
                            
         }
      
      } 



}