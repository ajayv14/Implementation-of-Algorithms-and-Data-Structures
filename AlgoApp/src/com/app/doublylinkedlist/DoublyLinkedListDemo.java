//credits: https://www.youtube.com/watch?v=LvUgew66zOQ

class DoublyLinkedListDemo {
   
   static Node head = null;
   int size = 0;      

   public void addToFront(int val){
   
      Node node = new Node(val);
      
      if(head == null){
         head = node;       
      }
      
      else {      //if (head.next == null){
         
         head.prev = node;
         node.next = head;
         head = node;   
      }
   
   }  
   
   public void addToEnd(int val){
   
       Node node = new Node(val);
      
      if(head == null ){
         head = node;       
      }
      
      else {      //if (head.next == null){
         Node n = head;   
         while(n.next != null){         
         n = n.next;
         } 
         
         n.next = node;
         node.prev = n;                     
      }            
   }
   
   
   public void addAfterNode(int val, int number){
       
       Node node = new Node(val);          
              
         if(head == null) return;     
        
         if(head.next == null && head.val == number){
            
            head.next = node;
            node.prev = head;            
         }
         
         else {
         
         Node n = head;   
         while(n != null){
         
            if(n.val == number){
                                                     
               Node tempNext = n.next;
               n.next = node;
               node.prev = n;
               node.next = tempNext;
               tempNext.prev = node;           
               return;
            }         
         n = n.next;       
       }  

         
         
         }         
              
           
   }
    
    
    
   public static void main(String[] args){
      
      DoublyLinkedListDemo obj = new DoublyLinkedListDemo();
         obj.addToFront(20);
         obj.addToFront(10);
         obj.addToEnd(30);
         obj.addAfterNode(35,20);
     
      Node n = head;   
      while(n != null){
         System.out.println(n.val);
         n = n.next;
      }   
      
      
   }      
      




}
