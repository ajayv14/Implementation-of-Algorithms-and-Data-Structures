class MinStack {  
    
    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public void push(int n){         
          if(n <= min){
              stack.push(min); // note
              min=n;
          }            
          stack.push(n);      
    }    

    public void pop(){     
      if(stack.pop()==min) min=stack.pop(); // set 2nd min as min              
    }
    
    public int getMin(){
       return min;        
    }
        
    public int top(){
        return stack.peek();        
    }   
    
}
