// credits : www.caveofprogramming.com
/*Using the synchronized keyword */


// Chapter 2
class ThreadDemo2 { // no Implements Runnable
   
   int count = 0;

   public static void main(String[] args){
      
      ThreadDemo2 obj = new ThreadDemo2();
         obj.doSomething();               
   }
   
   public synchronized void increment(){ // removing synchronized keyword will remove the sync between threads, leading to junk values when we print count
      count++;
   }
  
   
   public void doSomething(){
   
   
         Thread t1 = new Thread(new Runnable(){
          public void run(){
      
            for(int i = 0; i < 10000; i++ ){
               increment();            }      
          }        
      
      });
      
      
      Thread t2 = new Thread(new Runnable(){
          public void run(){
      
            for(int i = 0; i < 10000; i++ ){
               increment();            }                 
         }        
      
      });
      
      t1.start();      
      t2.start();
     
     try{
      
      t1.join(); // wait for thread to finish before printing the count
      t2.join();
     }
     
     catch(Exception e){
         e.printStackTrace();
     } 
      System.out.println("count : " + count); 
   }   
}