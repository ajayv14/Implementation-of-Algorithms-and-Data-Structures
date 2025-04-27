public class KthMissingPositiveNumber {

    // LC 1539 :  https://leetcode.com/problems/kth-missing-positive-number/

    public int findKthPositive(int[] arr, int k) {

              
        int low = -1, high = arr.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(arr[mid] <= mid) low = mid;

            else high = mid;  

        }

        return high;
        
    }

}
