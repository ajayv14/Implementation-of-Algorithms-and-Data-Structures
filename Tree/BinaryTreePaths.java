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
    
private void preTraverse(TreeNode root,String path,List<String> allPath){
    
if(root.left==null && root.right == null)  allPath.add(path+root.val);   

if(root.left!=null)  preTraverse(root.left,path+root.val+"->",allPath);  

if(root.right!= null)    preTraverse(root.right,path+root.val+"->",allPath); 
    
    
}    
    
    
    
    
}