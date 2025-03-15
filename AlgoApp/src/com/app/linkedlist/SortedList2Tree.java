import com.app.binarytree.TreeNode;
import com.app.models.Node;

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
    public TreeNode sortedListToBST(Node head) {
        
            if(head==null) return null;        
            return helper(head,null);        
    }
        
    private TreeNode helper(Node head, Node tail){
        
        if (head == tail) return null; // important !, termination condition : when there is no more mid element to be discovered

        Node slow = head;
        Node fast = head;
                        
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