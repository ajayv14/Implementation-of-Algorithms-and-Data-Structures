

import java.util.*;;


// LC 907 https://leetcode.com/problems/sum-of-subarray-minimums/

public class SumOfSubarrayMin {


    /*
     * credits : LC editorial
     * credits : https://leetcode.com/discuss/study-guide/2347639/  A-comprehensive-guide-and-template-for-monotonic-stack-based-problems
     * credits : https://leetcode.com/problems/sum-of-subarray-minimums/solutions/5165808/monotonic-patter-continuation/
     */

    /**
    Consider each element as the minimum value and find its left and right boundaries. For instance, in the array [0,1,3,4,5,8,3,2,7], if 2 is considered the minimum, its range spans from index 2 (3) to index 8 (7). This range encompasses multiple subarrays, such as [8,3,2,7] and [5,8,3,2,7]. To calculate the total count, multiply the number of elements in the left range by the number in the right range.
Implementation Steps
Utilize a monotonic stack, popping larger numbers and inserting the minimum when encountered.
Push the current index onto the stack if the current number exceeds the stack's top element.
The left boundary is the index of the element below the current number in the stack, and the right boundary is the index of the element above.
     */           


    // Time complexity - O(n), space complexity - O(n) 
    public int sumSubarrayMins(int[] arr) {

        // To prevent integer overflow when processing large numbers. Not needed otherwise
        //final int MOD = (int) 1e9 + 7; 
        final int MOD = 1000000007;

        long sum = 0;    

        Stack<Integer> stack = new Stack<>();

        // i <= condition occurs when all elements are processed and rest needs to be popped out of stack
        for(int i = 0 ; i <= arr.length; i++){

            // Stack is not empty and incoming element is less than current element in stack top.
            while(!stack.isEmpty() && (i == arr.length || arr[stack.peek()] >= arr[i])){

                int mid = stack.pop();

                //Find range - left and right side
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int rightBoundary = i;

                // Use MOD to stop integer overflow error.
                // Count the sub-arrays where mid is the min element
                long count = ((mid - leftBoundary) * (rightBoundary - mid)) % MOD;

                sum += (count * arr[mid]) % MOD;
                sum %= MOD;

            }

            stack.push(i);

        }

        return (int) sum;
        
    }


}
