import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
class ThreadDemo6_BlockingQueue {

   BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); // Thread safe blocking queue of size 10

   public static void main(String[] args) {
      
      ThreadDemo6_BlockingQueue obj = new ThreadDemo6_BlockingQueue();     
      
      Thread t1 = new Thread(new Runnable() {
         
         public void run() {               
              try{
                  obj.producer(); // call producer
                  
              }
              catch(Exception e){
                  e.printStackTrace();
              }          
         }
         
      });
 
      Thread t2 = new Thread(new Runnable(){
         
         public void run() {
               try{
                  obj.consumer(); // call consumer

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
   
   
   
   public void producer() throws InterruptedException {
      
      Random rand = new Random();
   
      while(true){         
         queue.put(rand.nextInt(100));         
      }   
   }
   
   
   
   public void consumer() throws InterruptedException{
      
      Random rand = new Random();
      
      while(true){
      
         Thread.sleep(100);
         
         if(rand.nextInt(10) == 0){ // will happen once in 10 runs, done to slow down consumer
            Integer value = queue.take();
            
            System.out.println("value : " + value + ", queue size : " + queue.size());
         }
         
      }
   
   }


}