package com.app.linkedlist;
import java.util.List;

public class Util {

    public static ListNode createNode(List<String> nodeItems){

        ListNode head = null;
        ListNode current = null;

        for(String val : nodeItems){

            ListNode node = new ListNode(val);

            if(head == null){

                head = node;
                current = head;
            }
            else {

                current.next = node;
                current = current.next;
            
            }

            
        }
        return head;
    } 


    public static void printList(ListNode list){

        while(list != null){

            System.out.print(list.data + " ");     
            list = list.next;           
        }
        System.out.println(" ");
    }
}
