/**
 Example:
  Given 1->2->3->4->5->NULL,
  return 1->3->5->2->4->NULL.
 */
class oddEvenListSolution {
    public ListNode oddEvenList(ListNode head) {
                       
    /*Basically create two lists , one with odd values and other with even values and attach head of even list to the tail of odd*/    
           
       
       if(head!=null){           
        
        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even; // head of the second list with even values
                
        while(even!=null && even.next!=null){
         
            odd.next =odd.next.next; // odd list or odd.next =even.next;
            odd =odd.next;
            
            even.next = even.next.next; // even list or  even.next = even.odd.next;
            even = even.next;
                        
        }        
        
        odd.next = evenHead;
       }        
        
        return head;        
        
    }
}