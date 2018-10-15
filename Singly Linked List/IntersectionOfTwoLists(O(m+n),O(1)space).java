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
     
       if(headA==null || headB==null) return null;        
        
        /*get the lengths of linked lists*/        
        int l1=0,l2=0;        
        ListNode p=headA, q=headB;      
        
        while(p!=null){
            l1++;
            p=p.next;
        }
        
        while(q!=null){
            l2++;
            q=q.next;
        }
        
        //int diff=Math.abs(l1=l2);
        
        /*re-assign p and q to head*/
        p=headA;
        q=headB;
        
        /*pick the longer list and move ptr to diff places,(to a point where lengths of Lists are equal */
        
        if(l1>l2){
            int diff = l1 - l2;  
            while(diff>0){
                p=p.next;
                diff--;
            }            
        }
        
        if(l2>l1){
            int diff = l2-l1;
            while(diff>0){
                q=q.next;
                diff--;
            }            
        }
        
        /*move ptr unless l1=l2, else return null*/
        while(p!=null && q!=null){
            if(p.val==q.val) return p;
            p=p.next;
            q=q.next;
        }
        
        return null;    
        
    }
}
