/*Level Order traversal + map */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import com.app.common.Pair;
import com.app.models.TreeNode;


// LC 314. Binary Tree Vertical Order Traversal
// https://leetcode.com/problems/binary-tree-vertical-order-traversal/

class VerticalOrderTraversal {
    

    // Time O(n log n ) Space O(n)
    // n log n as we maintain sorted order in tree map

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;
        
        // Level order traversal - colIndex, Node. : 
        //(colIndex computed by : root colINdex is 0, left child = colIndex - 1, rigth child = colIndex + 1)
        
        Queue<Pair<Integer,TreeNode>> q = new LinkedList<>();
        
        // TreeMap <Column Index, node values> // TreeMap tohave column index sorted
        Map<Integer,List<Integer>> colMap = new TreeMap<>(); 

        q.add(new Pair(0, root));
        
        while(!q.isEmpty()){

            Pair<Integer, TreeNode> p = q.remove();

            int col = p.getKey(); 
            TreeNode n = p.getValue();
            
            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).add(n.val);

            if(n.left != null) q.add(new Pair(col - 1, n.left));

            if(n.right != null) q.add(new Pair(col + 1, n.right));
        }

        // Now convert TreeMap to result 
        for(Integer col : colMap.keySet()){
            
            res.add(colMap.get(col));
        }

        return res;

    }
    
    
    
    
    
    
}