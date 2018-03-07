
/**
 * Use traditional ptr approach and find mid point in linkedlist
 * - make that the root and root.left and root.right is found recursively,
 * - utilizing left half of linkedlist and right half.   
 *    
 *    
 * 
 */



class SortedList2Tree {
    public TreeNode sortedListToBST(ListNode head) {
        
            if(head==null) return null;        
            return helper(head,null);        
    }
        
    private TreeNode helper(ListNode head, ListNode tail){
        
        if (head == tail) return null;

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