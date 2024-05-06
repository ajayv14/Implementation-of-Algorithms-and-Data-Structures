public class LinkedList {
    int size = 0;
    Node head;
    Node tail;

    public void addToEnd(String data) {

        Node node = new Node(data);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    
    public void addToFront(String val) {
        Node n = new Node(val);

        if (tail == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head = n;
        }
    }


    public Node find(String data) {

        if (head == null) {
            return null;
        }

        if (head.data == data) {
            return head;
        }

        Node node = head;

        while (node.next != null) {
            node = node.next;
            if (node.data == data) {
                return node;
            }
        }
        return null;
    }


    public Node delete(String data) {

        Node nodeToReturn = null;

        if (size == 0) {
            return null;
        }

        if (size == 1) {
            nodeToReturn = head;
            head = null;
            tail = null;
            size--;
        }


        Node beforeNode = findBefore(data);

        //delete head
        if (beforeNode.data == null) {
            head = head.next;
            size--;
        } else if (beforeNode != null) {
            if (tail.data == data) {
                beforeNode.next = null;
                tail = beforeNode;
            } else {
                beforeNode.next = beforeNode.next.next;
            }
            size--;
        }
        return null;
    }


    public Node findBefore(String data) {
        //new
    /*
     * not required
	 if(head == null){
		return null;
	}
	*/

        if (head.data == data) {
            //returns empty new node, if only head exists
            //check
            return new Node();
        }

        Node node = head;

        while (node.next != null) {
            if (node.next.data == data) {
                return node;
            }
            node = node.next;
        }
        return null;

    }

    public void traverse() {
        if (head == null) {
            System.out.println("Empty List");
        } else {
            Node node = head;
            System.out.println(head);

            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

     public static void main(String[] args) {

        LinkedList l = new LinkedList();

        l.addToEnd("Ajay");
        l.addToEnd("yaja");
        l.addToEnd("dine");
        l.addToEnd("eat");
        l.addToEnd("sleep");
        l.traverse();
        System.out.println("done adding");
        ;

        System.out.println("find result  :" + l.find("Ajay"));
        System.out.println("find result  :" + l.find("hsa"));
        System.out.println("find result:" + l.find("ind"));

        l.delete("dine");
        l.traverse();
        System.out.println("-----");
        l.delete("Ajay");
        l.traverse();
        System.out.println("-----");
        l.delete("sleep");
        l.delete("eat");
        l.traverse();
        System.out.println("-----");


    }
}
