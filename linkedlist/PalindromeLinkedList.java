package com.app.linkedlist;

import com.app.models.ListNode;

// LC 234 https://leetcode.com/problems/palindrome-linked-list/

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        
        if(head == null || head.next == null) return true;

        // Find mid
        ListNode mid = findMiddle(head);

        // Reverse second half of list
         ListNode prev = null;   
         ListNode nxt = null;

         while(mid != null){
            nxt = mid.next;   
            mid.next = prev;
            prev = mid;
            mid = nxt;
         }   
         
         // Check palindrome
         // Slow reached end == null and prev is the new head of reversed list   
         ListNode cur = head;   

         while(prev != null && cur != null && cur != prev){

            if(prev.val != cur.val) return false;

            cur = cur.next;
            prev = prev.next;

         }
        
         return true; 
         

    }

    
    private ListNode findMiddle(ListNode head){

        ListNode slow = head;
        ListNode fast = head.next;    

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // Based on odd and even num of LL
        if(fast.next != null) return slow.next;
        else return slow;
    }

}
