public class KthMissingPositiveNumber {

    // LC 1539 :  https://leetcode.com/problems/kth-missing-positive-number/

     // Minimization problem
    public int findKthPositive(int[] arr, int k) {

              
        int low = -1, high = arr.length;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(arr[mid] - (mid + 1) < k) low = mid;

            else high = mid;  

        }
            
        return high + k;
        
    }
    

}
