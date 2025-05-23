

import java.util.ArrayList;
import java.util.List;

import com.app.models.TreeNode;

/*
    Use dfs and a single list to append and remove nodes.

    Append root.val to list, dfs left and right node. If it adds up to target, then add to the result list.

    Remove the root.val once done with dfs.


    Runtime : O(N)
    Space : O(H), where H is max height of the tree, as we store a single path from root to leaf at most.

*/

public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
        List<List<Integer>> res = new ArrayList<>();          
           
           return dfs(res,new ArrayList<Integer>(), root,0, sum );           
           
       }  
       
       private List<List<Integer>> dfs(List<List<Integer>> res, List<Integer> list, TreeNode root, int currentSum, int sum ){
           
           if(root == null) return res;
                   
           list.add(root.val);
           currentSum += root.val;
           
           if(root.left == null && root.right == null && currentSum == sum ) {
               res.add(new ArrayList<>(list));
               //System.out.println(list);
               
           }
           
           dfs(res,list,root.left,currentSum, sum);
                
           dfs(res,list,root.right,currentSum,sum);
           
           list.remove(list.size() - 1);     
           
           return res;
       }



}
