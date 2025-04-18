import com.app.models.Node;

class RemoveNthNode {
    
    public Node removeNthFromEnd(Node head, int n) { 
        
         
        Node dummy = new Node(0);
        dummy.next = head;
        Node slow = dummy,fast=dummy;
       
        //move fast ptr n+1 times to create a window
        for(int k=0;k<n+1;k++){
           fast=fast.next;
        }     
        
        //maintain the window by moving slow and fast
        while(fast!=null){
           slow=slow.next;
           fast=fast.next;
        }
        
        //skip the next node
        slow.next=slow.next.next;
      
        return dummy.next;
        
    }
}

// doesnt work with case [1] and n=2