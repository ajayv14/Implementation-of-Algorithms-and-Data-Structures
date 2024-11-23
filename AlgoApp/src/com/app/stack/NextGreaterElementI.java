


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// LC 496 https://leetcode.com/problems/next-greater-element-i
// credits : https://leetcode.com/problems/next-greater-element-i/solutions/5164552/must-read-intuitive-pattern-for-deep-understanding-of-nge-and-nsm/

public class NextGreaterElementI {

    // Using monotonic stack based template

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] res = new int[nums1.length];
        Arrays.fill(res,-1);

        Map<Integer, Integer> map =  nextGreater(nums2);     

        for(int i = 0; i< nums1.length; i++){

            if(map.containsKey(nums1[i])) res[i] = map.get(nums1[i]);
        }

        return res;
        
    }

    // Calculate next greate num for elements in nums2 and store in a map
    // Use monotonic stack for next greater calc.

    private Map nextGreater(int[] nums2){

        // nums2,next greater element
        Map<Integer,Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();

        // Monotonic stack template
        for(int i = 0; i < nums2.length; i++){

            while(!stack.isEmpty() && stack.peek() < nums2[i]){

                // We pop when we see a greater num in nums2[i], hence we map smaller num - greater num below    

                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }

        return map;
    }
}
