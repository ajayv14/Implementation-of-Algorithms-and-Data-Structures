
class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        /*priority queue method*/
        
        if(lists==null || lists.length==0) return null;
        
        //define a priority queue with a comparator
        PriorityQueue<ListNode> PQ = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            
            public int compare(ListNode l1,ListNode l2){
                return l1.val - l2.val;
            }
                  
        });
        
        
        
        //new Node that will be returned with sorted values, store the head value and move the pointer to attach nodes, without disturbing                    the head node,
       
        ListNode head = new ListNode(0); //null not accepted, hence initialize with 0
        ListNode ptr = head; // used as pointer to create sorted node
        
        
        for(ListNode list : lists){
            if(list!=null) PQ.offer(list); // add to Priority Queue if not null
        }
        
        
        while(!PQ.isEmpty()){
            
            ListNode cur = PQ.poll();
            ptr.next = cur;
            ptr = ptr.next;          
        
            if(cur.next!=null){
                PQ.offer(cur.next);
            }
                        
        }
       return head.next;   
        
        
        
    }
}