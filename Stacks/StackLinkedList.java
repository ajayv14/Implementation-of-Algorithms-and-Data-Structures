package DSPractice;

 class NodeSt{

	String data;
	NodeSt next;
	
	public NodeSt(){
		
	}
	public NodeSt(String data){
	
		this.data = data;
		
	}
	
	public NodeSt(String data, NodeSt next){
		
		this.data = data;
		this.next = next;
	}
		
}


class LinkedList{
//	head-10-->20-->30--> tail

	NodeSt head;
	NodeSt tail;
	
	public void addToFront(String data){
	
		NodeSt node = new NodeSt(data, null);
		if(tail==null){
			
			tail = node;
			head = node;
		}
		else
		{
			
			node.next = head;
			head = node;
			
		}
		
	
}
	
	public void remove(){
		
	  if(tail==null){
		  System.out.println("empty list");
	  }
	  else if(head == tail){
		  		  head = null;
		  		  tail = null;
	  }
	  else {
		  head = head.next;
		  
	  }
	  
	  
	  
	  
				
	}

	
	public void top(){
		
		System.out.println(head.data);
				
	}
	
	
	public void traverse(){
		
		if(head==null){
			System.out.println("Empty List traversed");
		}
		
		NodeSt node = new NodeSt();
		
		node = head;
		System.out.println(node.data);
		
		while(node.next != null){
			
			node = node.next;
			System.out.println(node.data);
			
			
		}
		
		
	}
	
}



public class StackLinkedList {

	public static void main(String[] args){
		
	
		LinkedList l = new	LinkedList();
		
		l.addToFront("first");//push
		l.addToFront("Second");//push
		l.addToFront("Third");//push
		
		System.out.println("-------");
		
		l.remove();//pop
		l.remove();//pop
		System.out.println("-------");
		
		l.top();// top pointer element
	}
	
	
	
	
	
	
	
}
