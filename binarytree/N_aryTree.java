import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
/*pre order, post order and level order traversal*/
class N_aryTree {

    List<Integer> list = new ArrayList<>();
    
    
        
    public List<Integer> preorder(Node root) {
        
     if(root == null) return list;
     
     list.add(root.val);   
        
     for(int i = 0; i < root.children.size(); i++){
        
         preorder(root.children.get(i));
     }        
        return list;        
    }
    
    
    
    public List<Integer> postorder(Node root) {
        
        Stack<Node> st = new Stack<Node>(); 
        
        if(root == null) return list;
        
        st.push(root);
        
        while(!st.isEmpty()){
            
            Node n = st.pop();    
            
            list.add(0, n.val); // add to front of list 
                        
            for(int i = 0; i < n.children.size(); i++ ){
                
                st.push(n.children.get(i));   // add all the children to stack and repeat the process
            }            
        }
                
        return list;        
    }
    
    
     /*modified binary tree lelev order traversal*/
    
    public List<List<Integer>> levelOrder(Node root) {    
                
    
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null) return res;
        
        
        Queue<Node>  q = new LinkedList<>();
           q.add(root);
                     
        while(!q.isEmpty()){            
            
            List<Integer> currentLevelList = new ArrayList<>();
            
            int size = q.size();
            
            for(int i = 0; i < size; i++ ){ // never use for(int i = 0; i < q.size(); i++ ){ as queue size is dynamic
                System.out.println(q.size());
                Node n = q.poll();
                currentLevelList.add(n.val);    
                q.addAll(n.children);                
            }             
             res.add(currentLevelList);
       }  
        
        return res;
        
    }
    
    
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};