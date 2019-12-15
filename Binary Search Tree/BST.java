public class BST{

   Node root = null;

   class Node{
      int val;
      Node left;
      Node right;
   
       public Node(int val){
          this.val = val;      
       }
   } 
  
   private void insert(int val){   
     root = create(root, val);        
   }
  
   private Node create(Node root,int val){
                  
      if(root == null){
      
         root = new Node(val);
         return root;
      } 
      
      if(val <= root.val){
               
         root.left = create(root.left, val);
               
      }
      
      else if(val > root.val ){
         
         root.right = create(root.right, val);
      }
      
      return root;
      
   }
   
   
   public static void main(String[] args){
      
      BST obj = new BST();
      
         obj.insert(20);
         obj.insert(25);
         obj.insert(30);
         obj.insert(15);
         obj.insert(20);
      
         obj.traverse(obj.root);            
      }      
           
      public void traverse(Node root){
         
         if(root != null){
            System.out.println(root.val);                            
            traverse(root.left);
            traverse(root.right);            
         }
      }
}