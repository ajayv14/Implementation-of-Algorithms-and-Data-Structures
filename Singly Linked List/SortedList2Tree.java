
/**
 * Use traditional ptr approach and find mid point in linkedlist
 * - make that the root and root.left and root.right is found recursively,
 * - utilizing left half of linkedlist and right half.   
 *    
 *    
 * 
 */

/*logic : find mid of list and make it the root. Left & right child can be recursively got by splitting the list into two finding mid again--> providing start and end to helper*/


class SortedList2Tree {
    public TreeNode sortedListToBST(ListNode head) {
        
            if(head==null) return null;        
            return helper(head,null);        
    }
        
    private TreeNode helper(ListNode head, ListNode tail){
        
        if (head == tail) return null; // important !, termination condition : when there is no more mid element to be discovered

        ListNode slow = head;
        ListNode fast = head;
                        
        while(fast!= tail && fast.next!=tail){
            
            slow = slow.next;
            fast = fast.next.next;          
                                    
        } 
        
        TreeNode n = new TreeNode(slow.val);
        
        n.left = helper(head,slow);
        n.right = helper(slow.next,tail);
        
        return n;
        
    }
    
}