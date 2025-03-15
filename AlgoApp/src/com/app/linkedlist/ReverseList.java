package com.app.linkedlist;
//reversing a linked list

import java.util.Arrays;

import com.app.models.Node;

class ReverseList {

  public Node reverseList(Node head) {

    /*
     * Working recursive soln
     * 
     * if(head == null || head.next == null){
     * return head;
     * }
     * ListNode newHead = reverseList(head.next);
     * ListNode curr = head.next;
     * curr.next = head; // point to prev node
     * head.next = null;
     * return newHead;// new head must contain the head , ie last node..not the
     * current head.
     * 
     */

    Node next, prev, current;

    current = head;
    prev = null;

    if (head == null || head.next == null)
      return head;

    while (current != null) {

      next = current.next; // Store cur.next temproarily
      current.next = prev;
      prev = current;
      current = next;
    }

    head = prev;

    return prev;

  }

  public static void main(String[] args){

    ReverseList obj = new ReverseList();

    Node l1 = ListUtil.createNode(Arrays.asList("1","2","3","4","5"));
    System.out.println("Expected : 5 4 3 2 1");
    System.out.print("Actual :" );
    ListUtil.printList(obj.reverseList(l1));
  }


}