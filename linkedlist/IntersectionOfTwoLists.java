import com.app.models.ListNode;

public class IntersectionOfTwoLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
   
   /*     
        HashMap<ListNode,Integer> hm = new HashMap<ListNode,Integer>();
        
        if(headA==null || headB==null)return null;
        
        ListNode i = headA;
        ListNode j = headB;
        
        while(i!=null){
          hm.put(i,i.val);
          i = i.next;
        }
            
        while(j!=null){
            if(hm.containsKey(j)){
               return j;
            }
            j=j.next;
        }              
        return null;              
    /*


    }
}