import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadDemo10_2_Deadlock_Resolve_1 {

      
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
   
   
   private void acquireLocks(Lock lock1, Lock lock2){
      
      while(true){
         
         //acquire locks using tryLock boolean 
         
         boolean gotFirstLock = false;
         boolean gotSecondLock = false;
         
         try{
            gotFirstLock = lock1.tryLock();
            gotSecondLock = lock2.tryLock();
         }
         finally{
            if(gotFirstLock && gotSecondLock) return;
            
            if(gotFirstLock) lock1.unlock();
            
            if(gotSecondLock) lock2.unlock();
            
             
            
         }
         
                  
      }
        
   }
   
   public void firstThread(){

      Random random = new Random();
      
      for(int i = 0; i < 10000; i++){
         /*lock1.lock();
         lock1.2ock();*/
         
         acquireLocks(lock1,lock2);          
         
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
         
          acquireLocks(lock2,lock1);
         
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