package com.app.binarytree;

/** Do a pre order traversal from root to leaf. Each iteration, add to a temp string
     When leaf node is reached, convert binary string into decimal and add to the sum.
    
 **/

class RootToLeafBinaryNumbers {      
         
    int sum = 0;
    
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;
        helper(root,"");  
        return sum;
    }
    
    
    private void helper(TreeNode root, String temp){
        
        if(root == null) return;
        
        temp += root.val;
        
        if(root.left == null && root.right == null){
            // leaf node reached, convert temp binary string to int
            //sum += binaryToDecimal(temp); 
            sum += Integer.parseInt(temp,2);
        } 
        
        helper(root.left, temp);
        helper(root.right, temp);    
    }
    
    /* private int binaryToDecimal(String binary){
        
        int decimal = 0;
        int factor = 1;        
                       
        for(int i = binary.length() - 1; i >= 0; i--){
            decimal += factor * Integer.parseInt(String.valueOf(binary.charAt(i))); 
            factor *= 2;    
        }
        
        return decimal;
    }*/
    
    /*Test*/
    public static void main(String[] args){
      
      RootToLeafBinaryNumbers obj = new RootToLeafBinaryNumbers();
      //      
    }
    
}