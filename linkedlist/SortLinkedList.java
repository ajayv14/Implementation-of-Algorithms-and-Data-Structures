import com.app.models.Node;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class SortLinkedList {

    public Node sortList(Node head) {
        
        /*using merge sort --easy */
        
        //base case
        if(head == null || head.next == null) return head;
        
        //find mid point and split
        
        Node pre = null, slow = head, fast = head; 
        
        while(fast != null && fast.next != null){
            
            pre = slow; //to note down the previous value of slow -- to split the list
            slow = slow.next;
            fast = fast.next.next;            
        }
        
        //recursive alg to create more sublists
         
         pre.next = null; // to break the list into two head -- pre and slow to fast*/
        
         Node l1 = sortList(head);
         Node l2 = sortList(slow);
        
        return merge(l1,l2);
        
    }
    
    
    public Node merge(Node l1, Node l2){
        
       /*base case*/
        
        if(l1 == null) return l2;
        
        if(l2 == null) return l1;
        
        if(l1.val < l2.val){
            
           l1.next = merge(l1.next, l2);
           return l1;
            
        }       
        
        else /*(l2.val < l1.val)*/ {
            
            l2.next = merge(l1, l2.next);
            return l2;
            
        }              
        
    } 
    
    
    
}