

public class StackArray {
   
    int i = -1;
    int[] stack;
       
    public StackArray(int size){
         stack = new int[size];
      }
      
      public void push(int number){
         stack[++i] = number;      
      } 

      public int pop(){
         return (i >=0 )?stack[i--]:-1;
      }
      
      public int peek(){
         return (i >=0 )?stack[i]:-1;
      }
      
      public static void main(String args[]){
         StackArray st = new StackArray(10);
         st.push(2);
         st.push(4); 
         st.push(6);        
         System.out.println(st.peek());
         System.out.println(st.pop()); 
         System.out.println(st.peek());
         System.out.println(st.pop());
         System.out.println(st.pop()); 
         System.out.println(st.peek());
         System.out.println(st.peek());
         System.out.println(st.peek());
      }     
 
  /*
     int[] arr;
     int top = -1;

    
      public static void main(String[] args) {

        StackArray s = new StackArray();
        s.stackSize(100);
        s.push(10);
        s.push(20);
        System.out.println("pushed");
        s.pop();

        System.out.println(s.top());
        System.out.println(s.isNull());

       }

   public void push(int num) {
        top = top + 1;
        arr[top] = num;
    }

    public int pop() {
        top = top - 1;
        return arr[top + 1];
    }


    public String top() {

        String r = null;
        if (!isNull()) {
            r = " " + arr[top];
        }
        return r;
    }

    public boolean isNull() {
        return top==-1; 
    }


    public void stackSize(int size) {
        arr = new int[size];
    }
/*

}
