

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import com.app.models.TreeNode;

/*
LC 987. Vertical Order Traversal of a Binary Tree
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

  Using level order traversal.
  (colIndex computed by : root colINdex is 0, left child = colIndex - 1, rigth child = colIndex + 1)
   Collect nodes in each colIndex into a list. This will be the vertical order.
  *
 
  */

public class VerticalOrderTraversalOrdered {


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;

        // ColIndex,Level,TreeNode  - PQ order by level
        Queue<Triplet<Integer,Integer,TreeNode>> q = new LinkedListImplementation<>();

        // ColIndex, ColIndex,Level,TreeNode - TreeMap to collect all nodes in a column in order from left to right
        Map<Integer, List<Triplet<Integer,Integer,TreeNode>>> map =
                     new TreeMap<>(); 
        
        q.add(new Triplet(0,0,root));

        while(!q.isEmpty()){            

            Triplet<Integer,Integer,TreeNode> triplet = q.remove();
                
            int colIndex =  triplet.t1;
            int level = triplet.t2;
            TreeNode n = triplet.t3;

            map.putIfAbsent(colIndex, new ArrayList<>()); 
            map.get(colIndex).add(triplet);

            if(n.left != null) q.add(new Triplet(colIndex - 1, level + 1, n.left));
            if(n.right != null) q.add(new Triplet(colIndex + 1, level + 1, n.right));                                 
           
        } 


        for(int colIdx : map.keySet()){

            List<Triplet<Integer,Integer,TreeNode>> nodes = map.get(colIdx);

            // Sort as per what is given in description : 
            //multiple nodes in the same row and same column. In such a case, sort these nodes by their values.  
            Collections.sort(nodes, (x,y) -> x.t2 == y.t2 ? x.t3.val - y.t3.val : x.t2 - y.t2);


            // Process nodes to get result integer list 
            List<Integer> sortedNodes = new ArrayList<>();
            
            nodes.stream().forEach(n -> sortedNodes.add(n.t3.val));

            res.add(sortedNodes);
        }   

        return res;
    }


    // Generic class to simplify code 
    // Access is not private. TO improve redability     
    class Triplet<T1, T2, T3 > {

     T1 t1;
     T2 t2;
     T3 t3;
    

    public Triplet(T1 t1, T2 t2, T3 t3){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }


}
}
