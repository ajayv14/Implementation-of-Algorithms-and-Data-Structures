// credits : www.caveofprogramming.com

// Chapter 1
class ThreadDemo1 implements Runnable {
   
   public void run(){
      
      for(int i = 0; i < 10; i++ ){
         System.out.println(i);
      }      
   }   

   public static void main(String[] args){
      
      ThreadDemo1 obj = new ThreadDemo1();
      
      Thread t1 = new Thread(obj);
      t1.start();            
   }
}