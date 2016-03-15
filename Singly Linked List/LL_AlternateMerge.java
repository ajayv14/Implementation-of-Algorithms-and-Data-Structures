

//Merge a linked list into another linked list at alternate positions

/*First Linked List:
1 2 3
Second Linked List:
4 5 6 7 8
Modified First Linked List:
1 4 2 5 3 6 7 8
*/
public class LL_AlternateMerge {

	Node head; // head pointer
     
	//Node class
	class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}

		public Node() {

		}

		
	}
    // Add elements to front of list
	public void addToFront(int val) {

		Node n = new Node(val);

		if (head == null) {
			head = n;

		} else {
			n.next = head;
			head = n;

		}

	}
    
	//Merge Linked List at alternate positions
	public void merge(LL_AlternateMerge l2) {

		Node n = head;    // head pointer of List 1
		Node m = l2.head; // head pointer of List 2
		while (n.next != null && m != null) {
            
			// Temp Nodes to store m.next and n.next 
	   		Node m_next = m.next; 
			Node n_next = n.next;

			n.next = m;
			m.next = n_next;

			n = n_next;
			m = m_next;

		}

		if (n.next == null) // join remaining part of un-equal list
			n.next = m;
	}
	
	public void printList()
	{
		// print list
		Node n = head;
		while (n!= null)
		{
		System.out.print(n.data+"-->");
		n = n.next;
		}
		System.out.println();
	}
	
   public static void main(String args[]){
	   
	   //test
	   LL_AlternateMerge l1 = new LL_AlternateMerge();
	   LL_AlternateMerge l2 = new LL_AlternateMerge();
	   
	   l1.addToFront(3);
	   l1.addToFront(2);
	   l1.addToFront(1);

	   System.out.println("Linked List 1: ");
	   l1.printList();
	   
	   l2.addToFront(8);
	   l2.addToFront(7);
	   l2.addToFront(6);
	   l2.addToFront(5);
	   l2.addToFront(4);
	   
	   System.out.println("Linked List 2: ");
	   l2.printList();
	   
	   l1.merge(l2);
	   
	   System.out.println("Mofified Linked List 1: ");
	   l1.printList();
   }
	
	
	
	
}
