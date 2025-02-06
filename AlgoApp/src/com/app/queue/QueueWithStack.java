import java.util.Stack;

// https://leetcode.com/problems/implement-queue-using-stacks/submissions/1533216319/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days

class QueueWithStack{
   
   Stack<Integer> pushStack;
   Stack<Integer> popStack;
   
   public QueueWithStack(){
      pushStack = new Stack<>();
      popStack = new Stack<>();   
   }   
   
   public void enqueue(int num){
        /*push into push-stack*/
        pushStack.push(num);   
   }
   
   public int dequeue(){
      /*if pop stack is  non empty - pop from pop-stack , if empty dump contents of push stack into this, else return -1*/
      if(!popStack.isEmpty()) return popStack.pop();
      
      else if (popStack.isEmpty()){ 
         
         if(!pushStack.isEmpty()){ 
            //copy contents from push stack to pop stack
            while(!pushStack.isEmpty()){
                 popStack.push(pushStack.pop());
            }
            return popStack.pop(); // return the first element after copy is complete                       
         }         
         else {
            return -1; // both stacks are empty
         }      
      }      
      return -1;      
   }    
   
   
   public static void main(String args[]){
      QueueWithStack obj = new QueueWithStack();
      obj.enqueue(21);
      obj.enqueue(22);
           
      System.out.println(obj.dequeue());
      System.out.println(obj.dequeue());
      System.out.println(obj.dequeue());
          
      obj.enqueue(47);
      obj.enqueue(48);
      
      System.out.println(obj.dequeue());
      System.out.println(obj.dequeue());
      System.out.println(obj.dequeue());         
   } 

}