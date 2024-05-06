import java.util.*;
public class ThreadDemo10_0_Account_without_lock {
// total balance should ideally be 20000
      
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
   
   
   public void firstThread(){

      Random random = new Random();
      
      for(int i = 0; i < 10000; i++){
         
         Account.transfer(acc1, acc2, random.nextInt(100));                
                 
      }     
      
   }
   
   public void secondThread(){
      
       Random random = new Random();
      
      for(int i = 0; i < 10000; i++){
         
         Account.transfer(acc2, acc1, random.nextInt(100));                
                 
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