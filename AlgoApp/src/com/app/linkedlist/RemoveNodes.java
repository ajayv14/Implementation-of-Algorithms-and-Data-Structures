package com.app.linkedlist;

// LC 2487 - Remove every node which has a node with a greater value anywhere to the right side of it.
public class RemoveNodes {


    public ListNode removeNodes(ListNode head) {

        if(head == null || head.next == null) return head;
        
        //Reverse the list

        ListNode next = null;
        ListNode prev = null;
        ListNode current = head;

        while(current != null){

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } 

        head = prev;

        ListNode ptr = head;

        while(ptr.next != null){

            if(ptr.val > ptr.next.val){
                ptr.next = ptr.next.next;                
            }
            else ptr = ptr.next;

        }

        current = head;
        prev = null;
        next = null;

        while(current != null){

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } 

        
        return prev;
        
    }

}
