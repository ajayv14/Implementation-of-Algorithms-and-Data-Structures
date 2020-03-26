/*credits: Nick White : https://www.youtube.com/watch?v=12omz-VAyRk 
           https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/submissions/
*/

class SortedArrayToBST {
   
   /*logic : *mid of 'sorted' array will be the root for the tree, recursively complete the tree*/
   
    public TreeNode sortedArrayToBST(int[] nums) {
        
        if(nums.length == 0) return null;        
                
        return ArrayToBST(nums, 0, nums.length - 1);
    }
    
    public TreeNode ArrayToBST(int[] nums, int left, int right){
        
        if(left > right) return null; 
        
        int mid =  left + (right - left) / 2;
        
        TreeNode node = new TreeNode(nums[mid]);
        node.left = ArrayToBST(nums,left,mid - 1);
        node.right = ArrayToBST(nums, mid + 1, right);
                
        return node;
              
    } 
    
    
    public static void main(String[] args){
      
      int[] arr = {-10,-3,0,5,9};
      
      SortedArrayToBST obj = new SortedArrayToBST();
      TreeNode node = obj.sortedArrayToBST(arr);  
      
      
    } 
    
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
   
}