import java.util.List;

// LC 120 : https://leetcode.com/problems/triangle

// Backtracking
public class Triangle {

    int minPathSum = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        
        backtrack(triangle, 0, 0, 0);

        return minPathSum;
    }


    private void backtrack(List<List<Integer>> triangle, int row, int idx, int pathSum){

        // Reached leaf node
        if(row >= triangle.size()) {

            minPathSum = Math.min(minPathSum, pathSum);

            return ;
        } 

        pathSum +=  triangle.get(row).get(idx);
        
        backtrack(triangle, row + 1, idx, pathSum);
        backtrack(triangle, row + 1, idx + 1, pathSum);     

    }

}
