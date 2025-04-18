package com.app.linkedlist;
//reversing a linked list

import java.util.Arrays;

import com.app.models.ListNode;


// LC 206 https://leetcode.com/problems/reverse-linked-list/description/

class ReverseLinkedList {


  public ListNode reverseList(ListNode head) {

    if(head == null || head.next == null) return head;

    ListNode cur = head;
    ListNode prev = null;
  
    while(cur!= null){

        ListNode temp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;
    }

    return prev;        
}



 public ListNode reverseListRecursive(ListNode head) {
       
    if(head == null || head.next == null) return head;
     
    ListNode newHead = reverseList(head.next);
    ListNode curr = head.next;
    curr.next = head; // point to prev node
    head.next = null;
    return newHead; // new head must contain the head , ie last node..not the current head.
      
}

  public static void main(String[] args){

    ReverseLinkedList obj = new ReverseLinkedList();

    ListNode l1 = ListUtil.createNode(Arrays.asList("1","2","3","4","5"));
    System.out.println("Expected : 5 4 3 2 1");
    System.out.print("Actual :" );
    ListUtil.printList(obj.reverseList(l1));
  }


}