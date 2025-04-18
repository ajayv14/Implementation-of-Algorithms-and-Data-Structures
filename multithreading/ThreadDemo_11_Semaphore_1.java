import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class ThreadDemo_11_Semaphore_1 {
   
   
   public static void main(String[] args) throws Exception {
   
      ExecutorService executor = Executors.newCachedThreadPool(); // will reuse threads
      
      for(int i = 0; i < 200; i++ ){
         
         executor.submit(new Runnable(){
            
            public void run(){
               Connection.getInstance().connect();
                           
            }
         
         });
         
      } 
      
      executor.shutdown();
      executor.awaitTermination(1, TimeUnit.DAYS);  
   
   }
   

}



class Connection {
   
      
   private static Connection instance = new Connection();
   
   private Semaphore semaphore = new Semaphore(10, true); // to limit number of connections thro semaphore

   
   private int connections = 0;
   
   private Connection(){
   
   }
   
   public static Connection getInstance(){
      return instance;
   }
   
   public void connect(){
      
     try{
         semaphore.acquire(); // acquire permit, max permit is 10
      }
      catch(Exception e){
         e.printStackTrace();
      }
      
      
      try{
         doConnect();
      }
      finally{
         semaphore.release(); // relsease permit
      }     
   
   }
   
   public void doConnect(){   
      
      
      synchronized(this){
         connections++;
         System.out.println("Current connections  : " + connections);
      }
     
      try{
         Thread.sleep(2000);
      }
      catch(Exception e){
         e.printStackTrace();
      }
            
      synchronized(this){
         connections--;         
      }    
      
   }
}