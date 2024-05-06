// credits : www.caveofprogramming.com
import java.util.*;

/*brief summary:
   1) No synchnonized keywork/methods. Ideally end of run, list l1 and l2 should have 1000 entries each.Without any synchronized methods, list l1 an l2 will have random number of elements, taking ~4 secs to complete. 
   2) No synchnonized code blocks Making methods stage1() and stage2() synchronized looks to solve the problem, it does, but takes double the time as there is only one lock associated with the class and to run other method, it need to wait to aquire it.
   3) No synchronized methods. Now to overcome this, we are using synchronized blocks inside stage1() and stage2, associating them to each individual Object lock;
   4) can also get synchronized blobk lock on lists, but not ideal, use Object lock as a best practice
*/
class ThreadDemo3 {

   List<Integer> list1 = new ArrayList<>();
   List<Integer> list2 = new ArrayList<>();
   
   Random rand = new Random();
   
   Object lock1 = new Object();
   Object lock2 = new Object();   
   
   public static void main(String[] args){
       ThreadDemo3 obj = new ThreadDemo3();     
         obj.main();
   }
   
   
   public void main(){
      
      System.out.println("started threads");
      
      long start = System.currentTimeMillis();
      
      Thread t1 = new Thread(new Runnable(){
         
         public void run(){
            process();
         }
      
      });
      
      
      Thread t2 = new Thread(new Runnable(){
         
         public void run(){
            process();
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
      
      long end = System.currentTimeMillis();   
      
      System.out.println("time taken : " + (end - start));
      System.out.println("list1 size : " + list1.size() + ", list2 size : " + list2.size());      
      
   }
   
   
   public void process(){
     for(int i = 0; i < 1000; i++){
     
         stage1();
         stage2();     
     }         
            
   }
   
   
   //private synchronized void stage1(){
   private void stage1(){
      synchronized(lock1){
        list1.add(rand.nextInt(100));      
      }         
                     
   }
   
   
   //private synchronized void stage2(){
   private void stage2(){
      
      synchronized(lock2){
        list2.add(rand.nextInt(100));      
      }                      
   }  
   
   
   
}  