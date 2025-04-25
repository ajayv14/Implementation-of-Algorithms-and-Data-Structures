public class SearchMatrix1 {

    /**
     * 
     * Binary search template for matrix : 
     * Search in a 2D matrix using binary search.

        Use When: Matrix is sorted row-wise and column-wise.

        Treat the matrix as a 1D array:

        int midVal = matrix[mid / cols][mid % cols];

     * 
     */

    public boolean searchMatrix(int[][] matrix, int target) {

        // Binary search 
        
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;
        
        while(left <= right){

            int mid = left + right - left / 2;

            int midVal = matrix[mid / n][ mid % n];    

            if(target == midVal) return true;

            else if(target < midVal) right = mid - 1;

            else left = mid + 1;

        }
        return false;        
    }

}
