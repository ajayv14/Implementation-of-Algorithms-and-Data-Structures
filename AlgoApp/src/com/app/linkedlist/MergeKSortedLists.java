package com.app.linkedlist;

//credits: https://leetcode.com/problems/merge-k-sorted-lists/ 

import java.util.PriorityQueue;

import com.app.models.ListNode;

// LC 23 : https://leetcode.com/problems/merge-k-sorted-lists/
class MergeKSortedLists {

    // Time - O(n log k), Space - O(k) 

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null) return null;

        ListNode resultHead = new ListNode(0);// Dummy head

        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        for(ListNode head : lists){

            if(head != null) pq.add(head);    
        }

        ListNode node = resultHead;
        
        while(!pq.isEmpty()){

            ListNode cur = pq.remove();
            
            node.next = new ListNode(cur.val);
            node = node.next;
            
            if(cur.next != null) pq.add(cur.next);

        }

        return resultHead.next; // resultHead contains dummy value 0 in teh beginning

    }
    
}