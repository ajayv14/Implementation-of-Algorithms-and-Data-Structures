package com.app.linkedlist;
import java.util.List;

import com.app.models.Node;

public class ListUtil {

    public static Node createNode(List<String> nodeItems){

        Node head = null;
        Node current = null;

        for(String val : nodeItems){

            Node node = new Node(val);

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


    public static void printList(Node list){

        while(list != null){

            System.out.print(list.val + " ");     
            list = list.next;           
        }
        System.out.println(" ");
    }
}
