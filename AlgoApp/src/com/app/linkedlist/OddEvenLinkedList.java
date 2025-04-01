package com.app.linkedlist;
import java.util.Arrays;

import com.app.models.ListNode;


// lc 328 L https://leetcode.com/problems/odd-even-linked-list/description/
/**
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        /*
         * Basically create two lists , one with odd values and other with even values
         * and attach head of even list to the tail of odd, return original start of off
         * list (head)
         */

        if (head == null || head.next == null) return head; // List contains null or just one element

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // head of the second list with even values

        while (even != null && even.next != null) {

            odd.next = odd.next.next; // odd list or odd.next =even.next;

            even.next = even.next.next; // even list or even.next = even.odd.next;

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head; // start of odd list
    }





    

    public static void main(String[] args) {

        OddEvenLinkedList obj = new OddEvenLinkedList();

        ListNode opt1 = ListUtil.createNode(Arrays.asList("1", "2", "3", "4", "5"));
        System.out.println("Expected : [1,3,5,2,4]");
        System.out.print("Actual : " );
        ListUtil.printList(obj.oddEvenList(opt1));
        

        ListNode opt2 = ListUtil.createNode(Arrays.asList("2", "1", "3", "5", "6", "4", "7"));
        System.out.println("Expected : [2,3,6,7,1,5,4]");
        System.out.print("Actual : ");
        ListUtil.printList(obj.oddEvenList(opt2));
    }
}