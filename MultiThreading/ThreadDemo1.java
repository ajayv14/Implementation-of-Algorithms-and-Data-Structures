// credits : www.caveofprogramming.com

class ThreadDemo implements Runnable {
   
   public void run(){
      
      for(int i = 0; i < 10; i++ ){
         System.out.println(i);
      }      
   }   

   public static void main(String[] args){
      
      ThreadDemo obj = new ThreadDemo();
      
      Thread t1 = new Thread(obj);
      t1.start();            
   }
}