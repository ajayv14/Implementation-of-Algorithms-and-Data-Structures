//reversing a linked list
public class ReverseList {
    public ListNode reverseList(ListNode head) {
  
     /* Working recursive soln
  
     if(head == null || head.next == null){
        return head;
     }      
     ListNode newHead =  reverseList(head.next);  
     ListNode curr = head.next;
     curr.next = head; // point to prev node
     head.next = null;   
   return newHead;// new head must contain the head , ie last node..not the current head.      
    
    */    
    
    ListNode nxt,prv,cur;
    
    cur = head;
    prv = null;
    
    if(head == null || head.next == null){
        return head;
    }  
    
    while(cur!=null){
      nxt = cur.next;
      cur.next = prv;
      prv = cur;
      cur = nxt;
    }
    
    head = prv;
  return prv; 
      
    }
}