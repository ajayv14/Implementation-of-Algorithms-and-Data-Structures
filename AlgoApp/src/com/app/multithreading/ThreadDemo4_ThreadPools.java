// credits : www.caveofprogramming.com
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadDemo4_ThreadPools implements Runnable {

      private int id;
      
      public ThreadDemo4_ThreadPools(int id){
         this.id = id;
      }
      
      
      public void run(){
           System.out.println("started thread : " + id);
           
           try{
             Thread.sleep(4000);
           }
           catch(Exception e){
               e.printStackTrace();
           }
           
           
           System.out.println("close thread : " + id);
      }
      
      
      public static void main(String[] args){
          ExecutorService executor = Executors.newFixedThreadPool(2); //thread pool size
          
          for(int i = 0; i < 5; i++){
             
             executor.submit(new ThreadDemo4_ThreadPools(i));  // 5 tasks submitted
          
          }
          
          executor.shutdown();// wait and shut down after all tasks are complete
          
          System.out.println("all tasks are submitted");
          
          try{
              executor.awaitTermination(1,TimeUnit.DAYS); // wait till task execution completion // here it is 1 day      
          }
          catch(Exception e){
            e.printStackTrace();
          }
          
          System.out.println("all tasks are now complete");
         
          
      }

}