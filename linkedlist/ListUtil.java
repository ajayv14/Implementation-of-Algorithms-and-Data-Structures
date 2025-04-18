package com.app.linkedlist;
import java.util.List;

import com.app.models.ListNode;


public class ListUtil {

    public static ListNode createNode(List<String> nodeItems){

        ListNode head = null;
        ListNode current = null;

        for(String val : nodeItems){

            ListNode node = new ListNode(Integer.parseInt(val));

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

            System.out.print(list.val + " ");     
            list = list.next;           
        }
        System.out.println(" ");
    }
}
