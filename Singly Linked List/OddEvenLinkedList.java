/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        
        if(head==null) return null;
        
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = even;
        
        while(even!=null && even.next!=null){
            
             odd.next = even.next;
             
             odd = odd.next;
             
             even.next = odd.next;
             
             even = even.next;
        }
        
        odd.next = temp;
        
        return head;
        
        
        
    }
}