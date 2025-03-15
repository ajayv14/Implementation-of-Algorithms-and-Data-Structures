package com.app.linkedlist;

import java.util.List;

import com.app.models.Node;

public class Util {

    public static Node createNode(List<String> nodeItems){

        Node head = null;
        Node pointer = head;

        for(String val : nodeItems){

            pointer.next = new Node(Integer.parseInt(val));
            pointer = pointer.next;
        }
        return head;
    } 
}
