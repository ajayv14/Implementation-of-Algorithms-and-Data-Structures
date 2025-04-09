package com.app.linkedlist;

//credits: https://leetcode.com/problems/merge-k-sorted-lists/ 

import java.util.PriorityQueue;

import com.app.models.ListNode;

// LC 23 : https://leetcode.com/problems/merge-k-sorted-lists/


class MergeKSortedLists {

    //Time O(n log k) // Space O(1)
    // Pick two lists at a time and merge
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0 ) return null;
            
        if(lists.length == 1) return lists[0];

        // two lists at a time
        int i = 1;
        ListNode l1 = lists[0];

        while(i < lists.length){

            ListNode res = merge2Lists(l1, lists[i]);    
            i++;

            l1 = res;
        }    

        return l1;

    }


    private ListNode merge2Lists(ListNode l1, ListNode l2){

        ListNode resultHead = new ListNode(0);// Dummy head
        ListNode ptr = resultHead;

        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                ptr.next = l1;
                l1 = l1.next;
            }  

            else {
                ptr.next = l2;
                l2 =l2.next;
            }  

            ptr = ptr.next;        
        }

        // Remaining
        if(l1 != null) ptr.next = l1;

        else if(l2 != null) ptr.next = l2;

        return resultHead.next;

    }



    // Time - O(n log k), Space - O(k) 

    public ListNode mergeKLists2(ListNode[] lists) {

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