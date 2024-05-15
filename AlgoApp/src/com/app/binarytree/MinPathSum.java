package com.app.binarytree;


class MinPathSum{
        
     public int minPathSum(TreeNode root){
           
        if(root == null) return 0;
        
        return helper(root, 0);      
     
     }
     
     /*if both left and right clid exists, then add the lowest value to sum, else if one if it is null ,
         then add the not null child's value to sum, make sure to avoid setting null as 0 and then considering min(0, someValue)) */
         
     private int helper(TreeNode root, int sum){   
          
          if(root == null) return sum;
                              
          if(root.left != null && root.right != null){
            
            int left = 0, right = 0;
            
            left = helper(root.left, sum + root.val);
            right = helper(root.right, sum + root.val);
            
            return Math.min(left, right);  
               
          } 
               
          else if(root.left != null) return helper(root.left, sum + root.val); 
          
          else return helper(root.right, sum + root.val);      
                   
     } 
     
     
     
     public static void main(String[] args){
         
         TreeNode root;
         root = new TreeNode(10);                                              //           10
         root.left = new TreeNode(5);                                          //         /    \ 
         root.right = new TreeNode(5);                                         //        5      5
         root.left.right = new TreeNode(2);                                      //       \      \
         root.right.right = new TreeNode(1);                                   //           2     1
         root.right.right.left = new TreeNode(-1);                               //               /
                                                                                 //              -1 
        MinPathSum obj = new MinPathSum();
        System.out.println(obj.minPathSum(root));
     
     }
            
      
            

}