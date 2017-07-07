public class LinkedListTest {

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
