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

 //(O(m+n),O(1)space)

public class ListIntersection {



    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        ListNode runner1 = headA, runner2 = headB;

        while(runner1 != runner2){

            runner1 = (runner1 != null) ? runner1.next : headB;
            runner2 = (runner2 != null) ? runner2.next : headA;
        }
        
        return runner1;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
           
        /*get the lengths of linked lists*/        
        int l1=0,l2=0;        
        ListNode p=headA, q=headB;      
        
        while(p != null){
            l1++;
            p = p.next;
        }
        
        while(q != null){
            l2++;
            q = q.next;
        }
        
        int diff = Math.abs(l1-l2);
                       
        /*pick the longer list and move ptr to diff places,(to a point where lengths of Lists are equal */
        
        if(l1 > l2){            
            for(int i = 0; i < diff; i++){
                headA = headA.next;
            }                       
        }
        
        if(l2 > l1){
            for(int i = 0; i < diff; i++){
                headB = headB.next;
            }            
        }
        
        /*move ptr unless l1=l2, else return null*/
       while(headA != null && headB != null){
            
           if(headA == headB) return headA;
            
            headA = headA.next;
            headB = headB.next;
        } 
        
        return null;       
    }
          
}

