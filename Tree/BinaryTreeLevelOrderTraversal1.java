/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeLevelOrderTraversal1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
         List<List<Integer>>  mainList = new ArrayList<List<Integer>>(); 
         
           if(root==null) return mainList; 
         
              Queue<TreeNode> Q = new LinkedList<TreeNode>();
         
              Q.add(root); // make sure atleast one node is in queue 

              while(!Q.isEmpty()){        
        
               int level = Q.size();
               List<Integer> l1 = new ArrayList<Integer>();  // loop will be emptied after each reset  
                   
                  for(int i=0;i<level;i++){
              
                    TreeNode node = Q.remove();// remove each node from Queue
            
                    l1.add(node.val);     
                
                    // enqueue its children
                   if(node.left!= null){
                     Q.add(node.left);                     
                   }
         
                   if(node.right != null){
                     Q.add(node.right);
                   }
              }
         
        mainList.add(l1);
    }
    
    return mainList;
        
    }
}