package DSBtree;

public class BinaryTreeTest {

	public static void main(String[] Args){
	
	BinaryTree t = new BinaryTree();
	
	t.add(166);
	t.add(25);
	t.add(30);
	t.add(1);
	t.add(60);
	
	System.out.println("Done adding");
	
	t.deleteNode(166);
	t.traverse();
	
	
	}
	
	
}
