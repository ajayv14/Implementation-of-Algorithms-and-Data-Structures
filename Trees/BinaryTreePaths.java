/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
       
     List<String> allPath = new ArrayList<String>();  
  
    if(root == null) return allPath;
    
    else preTraverse(root,"",allPath);
    
    return allPath;    
        
    }

   public void DFS(TreeNode root, String tempPath, List<String> allPaths ){
        
        tempPath += root.val;
        
        if(root.left != null){
            DFS(root.left, tempPath + "->", allPaths);
        }
        
        if(root.right != null){
            DFS(root.right, tempPath + "->", allPaths);
        }
        
        if(root.left == null && root.right == null){
            allPaths.add(tempPath);
            return;
        }        
    }


   /*old method*/
   private void preTraverseOld(TreeNode root,String path,List<String> allPath){
    
      if(root.left==null && root.right == null)  allPath.add(path+root.val);   

      if(root.left!=null)  preTraverse(root.left,path+root.val+"->",allPath);  

      if(root.right!= null)    preTraverse(root.right,path+root.val+"->",allPath); 
    
    
   }    
    
    
    
    
}