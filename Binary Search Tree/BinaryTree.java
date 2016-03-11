package DSBtree;

public class BinaryTree {

	Node root;
	
	
	public void add(int data){
		
		Node node = new Node(data);
		
		if(root == null){
			root = node;
		}
		else{
		
		traverseAdd(root,node);
		
		}
					
			
		}
	
	public void traverseAdd(Node node,Node nodeToAdd){
			
		if(nodeToAdd.data < node.data){
		
			if(node.leftChild == null){
				nodeToAdd.parent = node;// added later during deletion function
				node.leftChild = nodeToAdd;
			}
			else{
				traverseAdd(node.leftChild, nodeToAdd);	
			}
			
			}
		
		else if(nodeToAdd.data > node.data){
			
			if(node.rightChild == null){
				nodeToAdd.parent = node;// added later during deletion function
				node.rightChild = nodeToAdd;
				nodeToAdd.parent = node;
				
			}
			else{
				traverseAdd(node.rightChild, nodeToAdd);	
			}
					
		}
		
		
		
	}
	
	
	public void traverse(){

		 if(root != null){
			 Node nodeToTraverse = root;
			 
			if(nodeToTraverse.leftChild == null && nodeToTraverse.rightChild == null){
				System.out.println(nodeToTraverse.data);
			 	}
			else{
						
				traverse(nodeToTraverse);
						
			}
		 }
	}
	//function overloading to pass a Node a parameter
	public void traverse(Node node){
		//pre order
		//System.out.println(node.data);
		
		if(node.leftChild != null){
			traverse(node.leftChild);	
		}
		//in order
		System.out.println(node.data);
		
		if(node.rightChild != null){
			traverse(node.rightChild);
		}
		//post order
		//System.out.println(node.data);
		
		
		
		}	

	
	
	public void deleteNode(int val){
//		20           1) Tree contains only the node with no children
//   18    28	     2) Tree has root, left or right child only
//16   19 22 30      3) root, left and right
		
		Node nodeToDelete = find(val);
		
		if(nodeToDelete != null){
			
			if(nodeToDelete.leftChild == null && nodeToDelete.rightChild== null){
				
			//case1 - node has no child
				deleteCase1(nodeToDelete);
		   	}
			
			else if(nodeToDelete.leftChild != null && nodeToDelete.rightChild != null){
		   // case 3- node has both left and right child	
				deleteCase3(nodeToDelete);
			}
			else {
			// Case 2 - node has left or right child not both
				deleteCase2(nodeToDelete);
			}
						
				}
			
	
	}
	
	private void deleteCase1(Node nodeToDelete) {
		

		if(nodeToDelete.parent.leftChild == nodeToDelete){
		
			nodeToDelete.parent.leftChild = null;
		}
		else if(nodeToDelete.parent.rightChild == nodeToDelete){
			nodeToDelete.parent.leftChild = null;
		}
		
	}

	private void deleteCase2(Node nodeToDelete){
		//root condition missing
		
		
		
		if(nodeToDelete.parent.leftChild == nodeToDelete){
			
			if(nodeToDelete.leftChild!=null){
			nodeToDelete.parent.leftChild = nodeToDelete.leftChild;
			}
		    else if(nodeToDelete.rightChild!=null){
			nodeToDelete.parent.rightChild = nodeToDelete.rightChild;
			}
		}
		
		else if(nodeToDelete.parent.rightChild == nodeToDelete){
			
			if(nodeToDelete.rightChild!=null){
				
				nodeToDelete.parent.rightChild = nodeToDelete.rightChild;
			}
			else if(nodeToDelete.leftChild != null){
				nodeToDelete.parent.leftChild = nodeToDelete.leftChild;
				
			}
			
		}
			
		
	}

	private void deleteCase3(Node nodeToDelete){
		
	Node minNode = minLeftTraversal(nodeToDelete.rightChild);
	deleteCase2(nodeToDelete);
	
	minNode.parent = nodeToDelete.parent;
	minNode.leftChild = nodeToDelete.leftChild;
	minNode.rightChild = nodeToDelete.rightChild;
	
	if(nodeToDelete.parent == null){
		
	root = minNode;
	}
	
	else{
	
	if(nodeToDelete.parent.leftChild == nodeToDelete){
		
		nodeToDelete.parent.leftChild = minNode;
		
	}
	else if(nodeToDelete.parent.rightChild == nodeToDelete){
		
		nodeToDelete.parent.rightChild = minNode;
	}
	
	}	
		
	}
	
	private Node minLeftTraversal(Node node){
		
		if(node.leftChild ==null){
			return node;
		}
		
	return	minLeftTraversal(node.leftChild);
		
		
	}
	
	
	public Node find(int val){
		
		
		if(root != null){
			
			return findNode(root,new Node(val));
		}
		return null;
		
	}
	
	
public Node findNode(Node search,Node node){
	// 1) can be root, left or right

	if(search== null){
		return null;
		}
	if(search.data == node.data){
		return search;
		}
	
	else {
	 
		Node returnNode = findNode(search.leftChild,node);
	
      if(returnNode == null){
    	  
    	   returnNode = findNode(search.leftChild,node);
      }
		
      return returnNode;
	}
		
	
	
	
}	
	
	
	
	
}