//credits: https://leetcode.com/problems/merge-k-sorted-lists/ 


class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        /*priority queue method*/
        
        if(lists==null || lists.length==0) return null;
        
        //define a priority queue with a comparator
       /* PriorityQueue<ListNode> PQ = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            
            public int compare(ListNode l1,ListNode l2){
                return l1.val - l2.val;
            }
                  
        }); */
        
        /*updated way of definign a priority queue with comparator*/        
        PriorityQueue PQ = new PriorityQueue<>((a,b)->a.val - b.val);
        
        /*head is the result node to be returned with sorted values, 
          move the prt pointer to attach nodes, without disturbing the head node*/
       
        ListNode head = new ListNode(0); //null not accepted, hence initialize with 0
        ListNode ptr = head; // used as pointer to create a sorted list
        
        
        for(ListNode listHead : lists){
            if(listHead !=null) PQ.offer(list); // add to Priority Queue if not null
        }
        
        
        while(!PQ.isEmpty()){
            
            ListNode cur = PQ.poll();
            ptr.next = cur;
            ptr = ptr.next;          
        
            /*add the next nodes in list to thr Priority Queue*/   
            if(cur.next!=null){
                PQ.offer(cur.next);
            }
                        
        }
       return head.next; // as head is initialized with (0), exclude it -> due to leetcode Node class  
        
        
        
    }
}