/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
     
               
        //get length of the two lists
        int l1=0;
        int l2=0;
        
        ListNode p = headA;
        ListNode q = headB;
                
        if(p==null || q==null) return null;
        
        while(p!=null){
            p=p.next;
            l1++;          
        }
        
        while(q!=null){
            q = q.next;
            l2++;
        }
        
        //go through the longer list and move ptr to a point where both lists are of same size, then compare values
        
        p = headA;
        q = headB;
        
        int diff=0; 
        int ptr=0;
        
        if(l1>l2){
            
            diff = l1-l2;
            while(ptr<diff){
                p=p.next;
                ptr++;
            }                                  
        }
            
         else if(l2>l1){
            diff=l2-l1;
            while(ptr<diff){
                q= q.next;
                ptr++;
            }
        }
                
        while(p!=null && q!=null){
            if(p.val == q.val) return p;
            p=p.next;
            q=q.next;
        }
        
        return null;
        
    }
}