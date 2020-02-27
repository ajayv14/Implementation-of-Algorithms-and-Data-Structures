/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class CopyRandomPointer {
    public Node copyRandomList(Node head) {
        
        /*basically provide a deep copy, so 1) iterate and add nodes to map <node, new node > -- create a new list 
             using next pointer, */
        
        if(head == null) return null;
        
        Map<Node,Node> map = new HashMap<>();
        
        
        Node cur = head;
        
        /*iterate and copy the nodes into the map*/
        while(cur != null){            
            map.put(cur, new Node(cur.val));           
            cur = cur.next;            
        }
        
        /*iterate again and each (key)entry is a Node, which in turn contains next and random pointers, assign next and random pointers to the (value) Node*/
        
        cur = head; // re-using pointer
        
        while(cur != null){            
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            
            cur = cur.next;
        }        
        
        
        return map.get(head);
    }
}