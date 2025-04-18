package com.app.linkedlist;

import com.app.models.ListNode;

// LC 876 : https://leetcode.com/problems/middle-of-the-linked-list/

public class MiddleOfLinkedList {

     // Don't care abt pos of fast, slow will always be in mid at end of loop for odd and even lists
     public ListNode middleNode(ListNode head) {
        
        if(head == null || head.next == null) return head;

        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            
            slow = slow.next;
            fast = fast.next.next;            
        }       
        
        return slow;        
    }

}
