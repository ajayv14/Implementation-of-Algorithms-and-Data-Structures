package com.app.linkedlist;

import com.app.models.Node;

// LC 2487 - Remove every node which has a node with a greater value anywhere to the right side of it.
public class RemoveNodes {


    public Node removeNodes(Node head) {

        if(head == null || head.next == null) return head;
        
        //Reverse the list

        Node next = null;
        Node prev = null;
        Node current = head;

        while(current != null){

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } 

        head = prev;

        Node ptr = head;

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
