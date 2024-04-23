import java.util.List;

public class Util {

    public static ListNode createNode(List<String> nodeItems){

        ListNode head = null;
        ListNode pointer = head;

        for(String val : nodeItems){

            pointer.next = new ListNode(val);
            pointer = pointer.next;
        }
        return head;
    } 
}
