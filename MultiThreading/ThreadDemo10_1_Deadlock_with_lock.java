import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* below is no easy solution as we dont know which accoutn comes first and which order to lock them at compile time 
         lock1.lock();
         lock2.lock();
         
         try{
            Account.transfer(acc1, acc2, random.nextInt(100)); 
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock1.unlock();
            lock2.unlock();
         } 
         
         
         or cud be below for acc2 : 
         
         lock2.lock();
         lock1.lock();
         
         try{
            Account.transfer(acc1, acc2, random.nextInt(100)); 
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock2.unlock();
            lock1.unlock();
         }          

*/
// Deadlock Cndition
// finally prioritizing lock1 when acc1 and lock 2 when acc2, we get to a deadlock condition 

public class ThreadDemo10_1_Deadlock_with_lock {

      
      public static void main(String[] args){
           
          Runner runner = new Runner();         
          
          Thread t1 = new Thread(new Runnable(){
          
            public void run(){
               runner.firstThread();               
            }
          
          });
            
          Thread t2 = new Thread(new Runnable(){
          
            public void run(){
               runner.secondThread();                    
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

   Account acc1 = new Account();
   Account acc2 = new Account();
   
   private Lock lock1 = new ReentrantLock();
   private Lock lock2 = new ReentrantLock(); 
   
   
   public void firstThread(){

      Random random = new Random();
      
      for(int i = 0; i < 10000; i++){
         lock1.lock();
         lock2.lock();
         
         
         try{
            Account.transfer(acc1, acc2, random.nextInt(100)); 
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock1.unlock();
            lock2.unlock();
            
         }
         
                 
      }     
      
   }
   
   public void secondThread(){
      
       Random random = new Random();
      
      for(int i = 0; i < 10000; i++){
         
         lock2.lock();
         lock1.lock();
         
         try{
            Account.transfer(acc2, acc1, random.nextInt(100)); 
         }
         catch(Exception e){
            e.printStackTrace();
         }
         finally{
            lock2.unlock();
            lock1.unlock();
         }
                         
                 
      } 
   
   }
   
   public void finished(){
      
      System.out.println(" Account 1 Balance : " + acc1.getBalance());
      System.out.println(" Account 2 Balance : " + acc2.getBalance());
      System.out.println(" Total Balance : " + (acc1.getBalance() + acc2.getBalance()));
                  
   }  

}



class Account{

   private int balance = 10000;
   
   public void deposit(int amt){
      balance += amt;  
   }
   
   public void withdraw(int amt){
      balance -= amt;
   }
   
   public int getBalance(){
      return balance;
   }
   
   public static void transfer(Account acc1, Account acc2, int amt){
      acc1.withdraw(amt);
      acc2.deposit(amt);
   }
   
}