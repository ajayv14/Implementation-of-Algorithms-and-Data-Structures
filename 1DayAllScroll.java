

// LC : 398 https://leetcode.com/problems/random-pick-index/

// Need Reservoir sampling  !!!!! optimized space com : O(1)

public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = (int) (Math.random() * numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
    }

}



// https://leetcode.com/problems/max-consecutive-ones-iii/
class MaxConsequitiveOnes3 {

    // Sliding window - variable length

    public int longestOnes(int[] nums, int k) {

        int left = 0, right = 0;
        int zeros = 0;
        int maxLen = 0;    

        while(right < nums.length){

            if(nums[right] == 0) zeros++;

            // Move left pointer until we find a zero.
            // Slide the window to right and decrement count of zeros if we encounter a zero, then left++
            while(zeros > k) {                
                            
                if(nums[left] == 0) zeros--;
                 
                left++;
            }
            
            // Window size is now optimal with just k number of zeros or less
            maxLen = Math.max(maxLen, right - left + 1); // + 1 to compensate for index 0                  

            right++;

        }
    
        return maxLen;   

    }

}
   


   //https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
class SparseVector {
    
        Map<Integer,Integer> map = new HashMap<>();
        
        SparseVector(int[] nums) {
                   
            for(int i = 0; i < nums.length; i++){
                
                if(nums[i] != 0) map.put(i, nums[i]);
            
            }
        
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
    
            int dotProduct = 0;
    
           // key is non zero index     
           for(Integer key : map.keySet()){
    
                // if second sparse vector also contains non-zero key
                if(vec.map.containsKey(key)) {
    
                    dotProduct += vec.map.get(key) * map.get(key);
                }
    
           }
           
    
           return dotProduct;
                    
        }
}
    
    // Your SparseVector object will be instantiated and called as such:
    // SparseVector v1 = new SparseVector(nums1);
    // SparseVector v2 = new SparseVector(nums2);
    // int ans = v1.dotProduct(v2);


// https://leetcode.com/problems/product-of-two-run-length-encoded-array
class RLEDotProduct {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {


        List<List<Integer>> res = new ArrayList<>();

        int i = 0, j = 0;
        
        while(i < encoded1.length && j < encoded2.length){

            int val1 = encoded1[i][0], freq1 = encoded1[i][1]; 

            int val2 = encoded2[j][0], freq2 = encoded2[j][1];

           
            int product = val1 * val2;
            int freq = Math.min(freq1, freq2);
            
            // Add to result

            if(res.size() == 0 || res.get(res.size() - 1).get(0) != product){

                res.add(Arrays.asList(product,freq));
            }
            else {
                 int existingFreq = res.get(res.size() - 1).get(1);
                 res.remove(res.size() - 1);
                 res.add(Arrays.asList(product,existingFreq + freq));
            }

            // Update frequencies

             encoded1[i][1] -= freq;
             encoded2[j][1] -= freq;

             // the min freq value   
             if(freq1 == freq) i++; 
             if(freq2 == freq) j++;             

        }
        
        return res;
    }
}



class MissingNumber {

    public int missingNumber(int[] nums) {

        // O(n) time

        // Summation of nums 0 -n :  n (n + 1) / 2

        int summation = nums.length * (nums.length + 1) / 2;

        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }        

        return summation - sum;
    }
}



// https://leetcode.com/problems/flatten-nested-list-iterator/

// Can we use Queue instead ?/

public class NestedIterator implements Iterator<Integer> {

    List<Integer> flatList = new ArrayList<>();
    int idx = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
             flatten(nestedList);
    }

    @Override
    public Integer next() {

        if(hasNext()) return flatList.get(idx++);

        else return -1;    
    }

    @Override
    public boolean hasNext() {

        if(idx < flatList.size()) return true;

        else return false;
        
    }

    private void flatten(List<NestedInteger> nestedList){

        for(NestedInteger nested : nestedList){

            if(nested.isInteger()) flatList.add(nested.getInteger());

            else flatten(nested.getList());
        }
    }

}
/*
class NestedInteger {

    boolean isInteger;
    int val;
    List<NestedInteger> list;
}
*/


// https://leetcode.com/problems/nested-list-weight-sum/description/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedIntegerDepthSumProd {
    public int depthSum(List<NestedInteger> nestedList) {
       
        Queue<NestedInteger> q = new LinkedList<>();

        int result=0;
        
        int level = 1;

        for(NestedInteger nested : nestedList){
           q.add(nested);
        }

        while(!q.isEmpty()){
            
            int size = q.size();
            
            for(int i = 0;i < size; i++){
                
              NestedInteger item = q.poll();
              
              if(item.isInteger()){

                result += item.getInteger() * level;
              
              }   

              else{

                for(NestedInteger n : item.getList()){
                    q.add(n);
                }

              }

            }
            
            level++;
        }

        return result;
    }
}


class Pow_x_n {
   
    
    public double myPow(double x, int n) {
        
          
       if(n == 0) return 1.0;
        
       if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        return fastPower(x, n);    
    
    }
    
    // This approach reduces the number of multiplications required.
    // calc 2 pow 8  = 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 
    //  2^8 -> 2^4 (half of 8) = 2^2 * 2^2 = 4 * 4 = 16
    // Square the result: 16 * 16 = 256

    // 2^3 -> 2 pow 2 * 2 -- for odd 
    //Calculate 2^1 = 2, Square the result: 2 * 2 = 4, Multiply by remaining 2: 4 * 2 = 8
  

    // Time O(log n) space O(log n) 
    public double fastPower(double x, int n){
        
       if(n == 0) return 1.0;
        
       double res = fastPower(x , n / 2); // calculate half the values
               
       if(n % 2 == 0){ // even
           
           res = res * res;           
       } 
        
       else  {
           
           res = res * res * x;
       }
        
        return res;
        
    }
    
    
     /*public double myPow(double x, int n) {
        
        //brute force
         
       if(n == 0) return 1.0;
        
        if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        
        double res = 1;
        
        for(int i = 0; i < n; i++) {
            
            res = res * x;                 
        }
        
        return res;
        
    }*/        
        
}

 // LC https://leetcode.com/problems/buildings-with-an-ocean-view      

public class FindBuildingsOceanView {
    

   public int[] findBuildings(int[] heights) {

       List<Integer> res = new ArrayList<>();

       int maxSoFar = 0;

       if(heights == null || heights.length == 0) return new int[0];
       if(heights.length == 1) return new int[] {0};

       res.add(heights.length - 1);
       maxSoFar = heights[heights.length - 1];

       for(int i = heights.length - 2; i >= 0; i--){

           if(heights[i] > maxSoFar) {
               res.add(i);

               maxSoFar = Math.max(maxSoFar, heights[i]);
           }
       }

       // return results in list in reverse order
       int[] result = new int[res.size()];
       for(int i = 0; i < res.size(); i++) {
           result[i] = res.get(res.size() - 1 - i);
       }
       
       return result;
   }
   
}



// LC 163 : https://leetcode.com/problems/missing-ranges/

public class MissingRanges {


    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        List<List<Integer>> res = new ArrayList<>();    

        int i = 0;

        while(i < nums.length){

            if(nums[i] != lower){

                List<Integer> missing = new ArrayList<>();
           
                res.add(Arrays.asList(lower, nums[i] - 1));
                
                lower = nums[i];
            }
           
            i++;
            lower++;                       
        }

        if(lower <= upper){
               
            res.add(Arrays.asList(lower, upper));
        }

        return res;
        
    }
}

//Variant: static range from 0-99, also output was wanted in a string that had 1-2 number gaps to be a singular number, 3+ number gaps to be a range.
//Input = [1, 4, 7, 30]
//Output = '0, 2, 3, 5, 6, 8-29, 31-99'


class MissingRangesVariant {
    
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        List<List<Integer>> res = new ArrayList<>();    

        int i = 0;

        while(i < nums.length){

            if(nums[i] != lower){

                List<Integer> missing = new ArrayList<>();

                // variant - length of range <= 3, then add individual numbers
                if(((nums[i] - 1) - lower) <= 3){

                      for(int j = lower; j < nums[i]; j++){
                        res.add(Arrays.asList(j));
                      }  

                }
                
                else res.add(Arrays.asList(lower, nums[i] - 1));

                lower = nums[i];
            }
           
            i++;
            lower++;                       
        }

        if(lower <= upper){
               
            res.add(Arrays.asList(lower, upper));
        }

        return res;
        
    }
}





    /*
    Example : 1 3 5 4 2
    We have ones, tenth, hundreds and so on based on weights.
    
    1. To get next greater permutation, we need to identify an index from right (ones) to satisfy
    a[i] < a[i + 1] -> 3 < 5 . Why ? -> Replacing smaller number with higher weight with higher number with currently smaller weight.
    But, we also need the next greater permutation, not just any greater permutation, so need
    
    2 To find next greater number than num at index i from the right end that can effectively replace smaller num identified in step 1..
    a[j] > a[i]. -> 4 > 3
    So we swap and get 1 4 5 3 2.
    
    3. We can also observer, after index i, the numbers are in decreasing order -> 5 3 2.
    reverse them and we get 1 4 2 3 5.    

*/

// LC 31. Next Permutation
// LC https://leetcode.com/problems/next-permutation/

public class NextPermutationLexi {


    public void nextPermutation(int[] nums) {


        // Find a number that satisfies a[i] < a[i + 1] - Basically find a smaller digit that can be  replaced with higher weight
        int i = 0;

        for (i = nums.length - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {
                break;
            }
        }


        // Find a digit that is greater than at index i, to effectively replace smaller digit with larger digit

        if (i >= 0) {

            for (int j = nums.length - 1; j > i; j--) {

                if (nums[j] > nums[i]) {

                    swap(i, j, nums);
                    break;
                }
            }


        }


        // Swap nums after index i as it is in decreasing order

        int start = i + 1;
        int end = nums.length - 1;

        while (start < end) {
            swap(start, end, nums);
            start++;
            end--;
        }

    }

    private void swap(int i, int j, int[] nums) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
}




// O(n)^2 time
// O(1) space
class SubArraySumEqualsKNonOptimized {

    public int subarraySum(int[] nums, int k) {
        
       int count = 0;
        
        for(int i = 0; i < nums.length; i++){
            
            int sum = 0;
            
            
            for(int j = i; j < nums.length; j++){
                
                sum += nums[j];
                
                if(sum == k){
                    //System.out.println(sum);
                    count++;
                    
                }             
            }
            
        }

        return count;
    }
}

// https://leetcode.com/problems/subarray-sum-equals-k/description/
// LC 560
class SubArraySumEqualsK {
    /*
        [1 2 3] k = 3
    
        Create map of prefix Sum at each index and its occurence. 
        Startign from 0,1 ->  Sum of 0 occurs one time
        // Why ? , When we check if (prefixSum - k) exists, example 3 - 3 exists, then we check if 0 exists
     
        Now similar to 2 sum, find if current prefixSum - K is present in the stored prefixSums.
        
        sum(nums[i,j] = prefixSum[j] -  prefixSum[i - 1])
        prefixSum[i - 1] = prefixSum[j] - k; 
    
       
      */
    
        // Time and space : O(N)
        public int subarraySum(int[] nums, int k) {
    
            int prefixSum = 0, count = 0;
    
            Map<Integer, Integer> prefixSumOccurence = new HashMap<>();
            prefixSumOccurence.put(0, 1); // Sum of 0 occurs 1 time
    
            for (int i = 0; i < nums.length; i++) {
    
                prefixSum += nums[i];
    
                System.out.println("prefix Sum : " + prefixSum + " map : " + prefixSumOccurence);
    
                if (prefixSumOccurence.containsKey(prefixSum - k)) {
    
                    count += prefixSumOccurence.get(prefixSum - k);
                }
    
                // Update map
                prefixSumOccurence.put(prefixSum, prefixSumOccurence.getOrDefault(prefixSum, 0) + 1);
            }
    
            return count;
    
        }
    
    
        // Time O(n)^2 Costant space
        public int subarraySumBrute(int[] nums, int k) {
    
            int count = 0;
    
            // Subarray starting from each number
            for (int i = 0; i < nums.length; i++) {
    
                int sum = 0;
    
                for (int j = i; j < nums.length; j++) {
    
                    sum += nums[j];
    
                    if (sum == k) count++;
                }
            }
    
            return count;
    
        }
    
    }




// https://leetcode.com/problems/merge-sorted-array

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        /*
           Straigntforward Merge from end of array in descending order.
         */
        
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;


        while(i >= 0 && j >= 0){
            
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }

            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        
        // while i >= 0 is not required as 

        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }        
       
    }
}


//https://leetcode.com/problems/maximum-swap/

public int maximumSwap(int num) {

    char[] nums = Integer.toString(num).toCharArray();

     // We can't store actual numbers coz then we won;t know what index to swap with
    int[] maxIndexToRight = new int[nums.length];

    maxIndexToRight[nums.length - 1] = nums.length - 1;


    for(int i = nums.length - 2; i >= 0 ; i--){
        
        if(nums[i] > nums[maxIndexToRight[i + 1]]) maxIndexToRight[i] = i;

        else maxIndexToRight[i] = maxIndexToRight[i + 1];
    }

   

    for(int j = 0; j < nums.length; j++){

        
        if(nums[j] < nums[maxIndexToRight[j]]){

            //System.out.println("j : " +nums[j]+ " max : " +nums[maxSeenSoFar]);
            swap(nums,j,maxIndexToRight[j]);
            break;
        }
    }


    return Integer.parseInt(new String(nums));
    
}


private void swap(char[] nums, int i, int j){

    char temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}




// https://leetcode.com/problems/kth-largest-element-in-an-array

class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {

        return quickSelect(0, nums.length - 1, nums, k);

    }

    // Quick select 

    private int quickSelect(int left, int right, int[] nums, int k){
  

        while(left < right){

            // like mid in binary search
            int pIndex = partition(left,right,nums);

            if(pIndex == nums.length - k) return nums[pIndex];

            else if(pIndex < nums.length - k) return quickSelect(pIndex + 1,right,nums,k);  

            else return quickSelect(left,pIndex - 1,nums,k);
        }

        return nums[left];
    }    

        private int partition(int left, int right, int[] nums){

            int i = left - 1;
            int j = left;

            int pVal = nums[right];

            for(j = left; j < right; j++){

                if(nums[j] < pVal){

                    i++;

                    swap(i,j,nums);
                }
            }

            swap(i + 1,right, nums);

            return i + 1;
        }

        private void swap(int i, int j, int[] nums){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        

    
}



// LC : 636 : https://leetcode.com/problems/exclusive-time-of-functions/

public class ExclusiveTomeOfFunctions {


    // optimized
/*
        Use a stack to keeop track of start - end iof a func.
        Timer to keep track of time
    */
    // Time O(n), space O(n/2) - only start of func is stored
    public int[] exclusiveTime(int n, List<String> logs) {

        // n - num of unique functions
        int[] res = new int[n];    

        Stack<Integer> stack = new Stack<>();

        int timer = 0;

        // pre-populate stack
        String[] logPrev = logs.get(0).split(":");
        
        stack.push(Integer.parseInt(logPrev[0])); //id
        timer = Integer.parseInt(logPrev[2]);

        for(int i = 1; i < logs.size(); i++){
                        
            String[] log = logs.get(i).split(":");

            // Process log
            int id = Integer.parseInt(log[0]);
            String action = log[1];
            int timeUnits = Integer.parseInt(log[2]);
            
            // optimization
            if(action.equals("start")){

                // Update time cosumed by previpus func                              
                if(!stack.isEmpty()) res[stack.peek()] += timeUnits - timer;
                                
                timer =  timeUnits; 

                stack.push(id);          
            } 
            
            // action is "end" 
            else {
                
                // Update time consumed by current func that ended
                // func runs till end time, so add + 1               
                res[stack.pop()] += timeUnits - timer + 1;
                
                timer = timeUnits + 1; // Update previous
            }      
           
        }        
        
        return res;
    }




    // Non optimized
    public int[] exclusiveTime2(int n, List<String> logs) {

        // n - num of unique functions
        int[] res = new int[n];    

        Stack<Integer> stack = new Stack<>();

        int time = 0;

        // pre-populate stack
        String[] logPrev = logs.get(0).split(":");
        stack.push(Integer.parseInt(logPrev[0])); //id
        time = Integer.parseInt(logPrev[2]);

        for(int i = 1; i < logs.size(); i++){
                        
            String[] log = logs.get(i).split(":");
         
            int id = Integer.parseInt(log[0]);
            String action = log[1];
            int timeUnits = Integer.parseInt(log[2]);
            
            // Run the timer
            while(time < timeUnits){
                res[stack.peek()]++;
                time++;
            }

            if(action.equals("start")) stack.push(id);
            
            // action is "end" 
            else {
                // func runs till end time, so 
                res[stack.pop()]++; 
                time++;
            }      
           
        }        
        
        return res;
    }
}



// https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent

class ArrayStringsEqual {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

       
        int i = 0, j = 0;  
        int n = 0, m = 0; // inner

        
        while(i < word1.length && j < word2.length){          

            if(word1[i].charAt(n) != word2[j].charAt(m)) return false;
                
            n++;
            m++; 
            
            if(n == word1[i].length()){
                    
                i++;                
                n = 0;
            }

            if(m == word2[j].length()){                    

                j++;           
                m = 0;
            }            
        }
        
        return i == word1.length && j == word2.length;
        
    }
}



/**
 * 
 *  [2,3,4,7,11], k = 5
 * 
 *  arr[mid] - (mid + 1)
 * 
 * At mid = 2, num is 4, so  4 - (2 + 1) = 1, the 1st missing number
 * At  mid = 3, num is 7, so  7 - (3 + 1) = 2, the 2nd missing number
 *  
 * So arr[mid] - (mid + 1) = k will give the answer
 */


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


// First greater element 
// [2,3,4,7,11], target = 4, op = 7

public class FirstGreater {
    
    // Minimization problem
    public int findKthPositive(int[] arr, int k) {

              
        int low = -1, high = arr.length;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(arr[mid] <= target) low = mid;

            else high = mid;  

        }

        // Check if high is within bounds
        if(high == arr.length) return -1; // No element greater than k found
            
        return high;
        
    }   
    

}




// LC 34 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


/**
 * 
 * Minimization problem - Find first - somewhat like find in first half of array, 
 * so we try to bring high closer to mid. Return high
 * 
 * Maximization - find last - Somewhat answer is towards end of array, so push low towards end, return low.
 * 
 * nums = [5,7,7,8,8,8,8,8,8,8,10], target = 8
 * 
 *  Find first      | |
 *             [5,7,7,8,8,8,8,8,8,8,10]
 *                low high
 * 
 * 
 *  Find last                     | |
 *             [5,7,7,8,8,8,8,8,8,8,10]
 *                               low high
 * 
 * 
 */                



 public class FindFirstAndLast {

    /**
        Use binary search minimization and maximization template.
     */
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];

        res[0] = findFirst(nums,target);
        res[1] = findLast(nums,target);

        return res;
        
    }

    private int findFirst(int[] nums, int target){

        int low = -1;
        int high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low) /2;

            if(nums[mid] >= target) high = mid;

            else low = mid;
        }

        // Number doesn't exist - Either too high or too low
        if(high == nums.length || nums[high] != target) return -1;

        return high;
    }

    private int findLast(int[] nums, int target){

        int low = -1;
        int high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low) /2;

            if(nums[mid] <= target) low = mid;

            else high = mid;
        }

        // Number doesn't exist - Either too high or too low
        if(low == -1 || nums[low] != target) return -1;

        return low;

    }

}



// https://leetcode.com/problems/missing-element-in-sorted-array/submissions/1620714112/

// Non optimized

class MissingElementInSortedArrayFrmFirstNum  {

    

    public int missingElement(int[] nums, int k) {
        

        

        int low = -1, high = nums.length;

        while(low + 1 < high){
            
            int mid = low + (high - low)/2;

            // from start to end, not just compared to previous num 
            int missNumCount = nums[mid] - nums[0] - mid;

            if(missNumCount < k ) low = mid;

            else high = mid;           

        }

        // Final miss num count - recalculate

         int missNumCnt = nums[low] - nums[0] - low;       

         return nums[low] + k - missNumCnt;
    }
}



// optimized binary search version
class MissingElementInSortedArrayFrmFirstNumOpt {
    

    public int missingElement(int[] nums, int k) {
      

        int low = -1, high = nums.length;

        while(low + 1 < high){
            
            int mid = low + (high - low)/2;

            // from start to end, not just compared to previous num 
            int missNumCount = nums[mid] - nums[0] - mid;

            if(missNumCount < k ) low = mid;

            else high = mid;           

        }      


        // starting point : first number + 
        // current index at high (place where we have k missing numbers) + 
        // k missing nums
        return nums[0] + high + k - 1; // - 1 as high is set to out of bounds in begining
    }
}




public class MinimumCostTickets {

    /*
     * There are 2 arrays which denote departing and returning flights with the respective indexes being 
     * time and the values of the array being the cost it takes for the flight.
     *  Return the minimum cost for a round trip provided the return flight can only be taken at a time post departing flight time 
     * (i.e if departing at time i, one can catch a returning flight only from time (i+1) onwards). 
     * For eg departing = [1,2,3,4] and returning = [4,3,2,1], 
     * the minimum cost for round trip will be 2 i.e departing[0] + returning[3]. Solve this is O(n) time
     * 
     */

     public int findMinCost(int[] departing, int[] returning) {

        int n = departing.length;
        int minReturn = Integer.MAX_VALUE;
        int minCost = Integer.MAX_VALUE;
    
        // Traverse backwards from the end to track minimum returning[i] for j > i
        for (int i = n - 1; i > 0; i--) {
            // Update minReturn before using it, since i is for departing
            minReturn = Math.min(minReturn, returning[i]);
            int tripCost = departing[i - 1] + minReturn;
            minCost = Math.min(minCost, tripCost);
        }
    
        return minCost;
    }
    
    public static void main(String[] args) {

        int[] departing = {1, 2, 3, 4};
        int[] returning = {4, 3, 2, 1};
        
        MinimumCostTickets obj = new MinimumCostTickets();

        int cost = obj.findMinCost(departing, returning);
        
        System.out.println("Expected : 2 " + "actual : " + cost);
    }
}


//https://leetcode.com/problems/find-peak-element/

class FindPeakElement {

    
    public int findPeakElement(int[] nums) {

       int low = -1, high = nums.length - 1;

        while(low + 1 < high){

           int mid = low + high - low/2;

            /* Note : we dont need to check : && nums[mid]  > nums[mid - 1]
                as in the case it fails, the number to left (nums[mid - 1]) will be the peak element. 
                Basically the condition is already checked in previous iterations
            */
           if(nums[mid] > nums[mid + 1]){

                high = mid;        
           }

           else {
                low = mid;
           }
        }

        return high;        
    }
    
    // Minimization problem
}




class Fibonacci {

    /* //recursive solution 
    public int fib(int N) {        
        if(N <= 1) return N;        
        return fib(N - 1) + fib(N - 2);        
    }*/
    
  
    // easy to understand memoization
    
    public int fib(int N) {        
        if(N <= 1) return N;        
        
        int[] dp = new int[N];
        dp[0]=1;
        dp[1]=1;
        
        for(int i = 2; i <= N - 1; i++){
            dp[i] = dp[i - 1] + dp[i - 2];            
        }
        
        return dp[N-1];        
    }  
    
    
}



class MaxAvgSubarray {
    public double findMaxAverage(int[] nums, int k) {

        float windowSum = 0; // Method returns double, hence need for precesion.
        
        float maxAvg = 0; // edge case input [-1] & k = 1
        float curAvg = 0;        

        // When window is smaller than k, expand.
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }

        curAvg = windowSum/k;        
        maxAvg = Math.max(maxAvg, curAvg);

        int right = k; // Pointing to right end of window
        
        while(right < nums.length){   
            
            // Slide = add new value and remove leftmost value
            windowSum += nums[right] - nums[right - k]; 

            curAvg = windowSum/k;
            maxAvg = Math.max(maxAvg, curAvg);            
            right++;            
        }

        return (double)maxAvg;        
    }
}

//https://leetcode.com/problems/min-stack/
class MinStack {


    // int[] contains a pair - value and min 
    Stack<int[]> stack;
   
    public MinStack() {
        stack = new Stack<>();          
    }
    
    public void push(int val) {

        if(stack.isEmpty()){

            stack.push(new int[] {val, val});
        }

        else {

            int min = stack.peek()[1];

            min = Math.min(min, val); 
       
            stack.push(new int[] {val, min});
        }    
    }
    
    public void pop() {
        
        stack.pop();   
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */




public class BasicCalculator2 {


    // LC 227 : https://leetcode.com/problems/basic-calculator-ii/

    public int calculate(String s) {
                
        int res = 0;

        int previousNum = 0;
        
        int number = 0;

        char operator = '+'; // prev operator

        
        for(int i = 0; i < s.length(); i++){
            
            Character ch = s.charAt(i);
            
            if(Character.isDigit(ch)){
                number = (number * 10) + Character.getNumericValue(ch); 
            }           
           
            
            if(!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1){

               if(operator == '+') {
                       
                    res += previousNum;
                    previousNum = number;
                }

                else if(operator == '-') {
                    res += previousNum;
                    previousNum = - number;
                }         
                else if(operator == '/') {
                    previousNum /= number; 
                }

                else if(operator == '*'){
                    previousNum *= number;
                }                
                
                operator = ch; // Update 
                number = 0; // Reset               
            }
        }

        res += previousNum;

        return res;       

        
    }

}

// LC 224 : https://leetcode.com/problems/basic-calculator

public class BasicCalculator {

    public int calculate(String s) {
        
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operator = new Stack<>();

        int num = 0;
        int res = 0;
        char sign = '+';

        for(int i = 0; i < s.length(); i++){

            char c = s.charAt(i);

            if(Character.isDigit(c)){

                num = num * 10 + (c - '0');  

                //System.out.println("num : " + num);              
            }

            else if(!Character.isDigit(c) || c == ' ' || i == s.length() - 1){              

                if(c == '+' || c == '-'){           

                    int op = sign == '+' ? 1 : -1;    

                    res += op * num;
                    num = 0; // reset
                    sign = c;  // update previous sign
                } 

                else if(c == '('){

                    numStack.push(res);
                    operator.push(sign);
                    
                    res = 0; // reset
                    sign = '+'; // reset
                }
                else if(c == ')'){

                    // pop and evaluate
                    res += (sign == '+' ? 1 : -1) * num;

                    int op = operator.pop() == '+' ? 1 : -1;

                    res *= op;
                    res += numStack.pop();

                    num = 0;
                }                

            }         
            
        }

        int op = sign == '+' ? 1 : -1; 
        res += op * num;

        return res;  
    
    }
  
}


    
public class NextPermutationLexi {
    
    
        /*
        Example : 1 3 5 4 2
        We have ones, tenth, hundreds and so on based on weights.
        
        1. To get next greater permutation, we need to identify an index from right (ones) to satisfy
        a[i] < a[i + 1] -> 3 < 5 . Why ? -> Replacing smaller number with higher weight with higher number with currently smaller weight.
        But, we also need the next greater permutation, not just any greater permutation, so need
        
        2 To find next greater number than num at index i from the right end that can effectively replace smaller num identified in step 1..
        a[j] > a[i]. -> 4 > 3
        So we swap and get 1 4 5 3 2.
        
        3. We can also observer, after index i, the numbers are in decreasing order -> 5 3 2.
        reverse them and we get 1 4 2 3 5.    
    
    */
    
        // LC 31. Next Permutation
    // LC https://leetcode.com/problems/next-permutation/
        public void nextPermutation(int[] nums) {
    
    
            // Find a number that satisfies a[i] < a[i + 1] - Basically find a smaller digit that can be  replaced with higher weight
            int i = 0;
    
            for (i = nums.length - 2; i >= 0; i--) {
    
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
    
    
            // Find a digit that is greater than at index i, to effectively replace smaller digit with larger digit
    
            if (i >= 0) {
    
                for (int j = nums.length - 1; j > i; j--) {
    
                    if (nums[j] > nums[i]) {
    
                        swap(i, j, nums);
                        break;
                    }
                }
    
    
            }
    
    
            // Swap nums after index i as it is in decreasing order
    
            int start = i + 1;
            int end = nums.length - 1;
    
            while (start < end) {
                swap(start, end, nums);
                start++;
                end--;
            }
    
        }
    
        private void swap(int i, int j, int[] nums) {
    
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
    
        }
    
    }
    


// LC  1570 : https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

// Optimized version.
class SparseVector {
    
    List<int[]> vectors;
    
    SparseVector(int[] nums) {
        vectors = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) vectors.add(new int[] {i, nums[i]});
        
        }
    
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        
       // two pointers
       int i = 0, j = 0, product = 0;

       while(i < vectors.size() && j < vec.vectors.size()){

            int[] vectors1 = vectors.get(i);
            int[] vectors2 = vec.vectors.get(j);


            // if index is the same
            if(vectors1[0] == vectors2[0]){

                product += vectors1[1] * vectors2[1];

                i++;
                j++;
            }

            else if (vectors1[0] < vectors2[0]){
                i++;
            }

            else j++;
       }

       return product;
                
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);



// Nopn optimized - Can lead to collision and other issues with hashmap when a large dataset is used.

class SparseVector2 {
    
    private Map<Integer, Integer> map;
    
    SparseVector2(int[] nums) {
        
        map = new HashMap<>();       
        
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] != 0) map.put(i, nums[i]);
            
        }
        
        
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector2 vec) {
        
        int dotProduct = 0;
        
        for(int key : map.keySet()){
            
            dotProduct += map.get(key) * vec.map.getOrDefault(key,0);            
            
        }
        
        
        return dotProduct;
    }
}





class KthLargestInArray {
    public int findKthLargest(int[] nums, int k) {

        return quickSelect(0, nums.length - 1, nums, k);

    }

    // Quick select 

    private int quickSelect(int left, int right, int[] nums, int k){
  

        while(left < right){

            // like mid in binary search
            int pIndex = partition(left,right,nums);

            if(pIndex == nums.length - k) return nums[pIndex];

            else if(pIndex < nums.length - k) return quickSelect(pIndex + 1,right,nums,k);  

            else return quickSelect(left,pIndex - 1,nums,k);
        }

        return nums[left];
    }    

        private int partition(int left, int right, int[] nums){

            int i = left - 1;
            int j = left;

            int pVal = nums[right];

            for(j = left; j < right; j++){

                if(nums[j] < pVal){

                    i++;

                    swap(i,j,nums);
                }
            }

            swap(i + 1,right, nums);

            return i + 1;
        }

        private void swap(int i, int j, int[] nums){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        

    
}




public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        /*
           Straigntforward Merge from end of array in descending order.
         */
        
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;


        while(i >= 0 && j >= 0){
            
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }

            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        
        // while i >= 0 is not required as 

        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }        
       
    }
}


// LC 23 : https://leetcode.com/problems/merge-k-sorted-lists/


class MergeKSortedLists {

    //Time O(n log k) // Space O(1)
    // Pick two lists at a time and merge
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0 ) return null;
            
        if(lists.length == 1) return lists[0];

        // two lists at a time
        int i = 1;
        ListNode l1 = lists[0];

        while(i < lists.length){

            ListNode res = merge2Lists(l1, lists[i]);    
            i++;

            l1 = res;
        }    

        return l1;

    }


    private ListNode merge2Lists(ListNode l1, ListNode l2){

        ListNode resultHead = new ListNode(0);// Dummy head
        ListNode ptr = resultHead;

        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                ptr.next = l1;
                l1 = l1.next;
            }  

            else {
                ptr.next = l2;
                l2 =l2.next;
            }  

            ptr = ptr.next;        
        }

        // Remaining
        if(l1 != null) ptr.next = l1;

        else if(l2 != null) ptr.next = l2;

        return resultHead.next;

    }



    // Time - O(n log k), Space - O(k) 

    public ListNode mergeKLists2(ListNode[] lists) {

        if(lists == null) return null;

        ListNode resultHead = new ListNode(0);// Dummy head

        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

        for(ListNode head : lists){

            if(head != null) pq.add(head);    
        }

        ListNode node = resultHead;
        
        while(!pq.isEmpty()){

            ListNode cur = pq.remove();
            
            node.next = new ListNode(cur.val);
            node = node.next;
            
            if(cur.next != null) pq.add(cur.next);

        }

        return resultHead.next; // resultHead contains dummy value 0 in teh beginning

    }
    
}




class CopyRandomPointer {
    public Node copyRandomList(Node head) {
        
        /*basically provide a deep copy, so 1) iterate and add nodes to map <node, new node > -- create a new list 
             using next pointer, */
        
        if(head == null) return null;
        
        Map<Node,Node> map = new HashMap<>();
        
        
        Node cur = head;
        
        /*iterate and copy the nodes into the map*/
        while(cur != null){            
            map.put(cur, new Node(cur.val));           
            cur = cur.next;            
        }
        
        /*iterate again and each (key)entry is a Node, which in turn contains next and random pointers, assign next and random pointers to the (value) Node*/
        
        cur = head; // re-using pointer
        
        while(cur != null){            
            
            Node n = map.get(cur);
            n.next = map.get(cur.next); // we cannot assign node.next directly as we need to link to cloned node, not original node
            n.random = map.get(cur.random);
                   
            cur = cur.next;
        }         
        
        return map.get(head);
    }
}


// LC : 398 https://leetcode.com/problems/random-pick-index/

public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = (int) (Math.random() * numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
    }

}

/**
    Optimized Approach : 

    Reservoir sampling : 
    
    

    {1, 2, 3, 3, 3}

    When you see the first target (count = 1), rand.nextInt(1) always returns 0 → you must pick it.

    For the second target (count = 2), rand.nextInt(2) gives 0 or 1 → 50% chance to pick the new one.

    For the third target (count = 3), 1/3 chance to pick it, 2/3 chance to keep the old one.
        
    And so on.

 */

// Time O(N) Space O(1)

class RandomPickIndexOptimized {

    int samples[];  
    Random rand;  

    public RandomPickIndexOptimized(int[] nums) {

      samples = nums;
      rand = new Random();

  }
  
  public int pick(int target) {
      
      int count = 0;
      int idx = 0;


      for(int i = 0; i < samples.length; i++){

          if(samples[i] == target){

              count++;

              if(rand.nextInt(count) ==  0){
                  idx = i;
              }
          }
      }


      return idx;        
      
  }
}



public class PickRandomWeightProbability {




    /*
        input = [1 2 4]
        total sum = 7, so 1 should have 1 out of 7 chance, 2 -> 2/7 and 4 -> 4/7
        probablity of occurenceCan be represented as [1 2 2 4 4 4 4]. 
        Then we generate a random number between 0 and 7, like 5.42
        so return num at index 5 -> 4

        Optimize the array [1 2 2 4 4 4 4]  
        Use prefix sum instead -> [1 3 7] 
        generate a random number between 0 and 7, like 5.42
        Find index where 5.42 < total sum (7) - index 2. Return index 2, corresponmding to 4 in weights array.  

        LC https://leetcode.com/problems/random-pick-with-weight

     */

     // Soln using linear search and prefix sum. Can use binary search to optimize

     int[] prefixSum;

     public PickRandomWeightProbability(int[] w) {
         
         prefixSum = new int[w.length];
 
         prefixSum[0] = w[0];
        
         // cummulative sum
         for(int i = 1; i < w.length; i++){
             
             prefixSum[i] = prefixSum[i - 1] + w[i];
         }
     }
     
     public int pickIndex() {
         
         double target  = Math.random() * prefixSum[ prefixSum.length - 1]; // 0.00 - 1.00 ?
 
         System.out.println(target);
 
         // target range
         for(int i = 0; i < prefixSum.length; i++){
 
             if(target < prefixSum[i]){
                 return i;
             }
         }
 
         return -1;
 
     }


   
}



// https://leetcode.com/problems/maximum-width-ramp/
// LC 962. Maximum Width Ramp


public class MaxWidthRamp {

    //Time O(n) Space O(n)
    public int maxWidthRamp(int[] nums) {

        int max = 0;

        Stack<Integer> stack = new Stack<>();

        // Find NGE, so monotocally striclty decreasing
        for(int i = 0; i < nums.length; i++){

            if(stack.isEmpty() || nums[i] < nums[stack.peek()]){
                stack.push(i);
            }            
        }

        //System.out.println(stack);

        
        for(int j = nums.length - 1; j >= 0; j-- ){

            // If loop can pick only the first element in stack with i < j, nums[i] < nums[j],. but there cud be more
            while(!stack.empty() && nums[stack.peek()] <= nums[j]){
                
                max = Math.max(max, j - stack.pop());
            }
        }

        return max;   

        
    }
}



//https://leetcode.com/problems/longest-increasing-subsequence/
// LIS
class longestIncreasingSubsequence {

    /*
      Base case : nums array - at index 0 will have subseq of length 1.
      Now starting from index 1, check all nums to left of it, can it be included in strictly increasing subsequence ??      
    
     */


    public int lengthOfLIS(int[] nums) {
        
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1); // Each number itself can be a sequence of length 1

        int max = 1;

        for(int i = 1; i < nums.length; i++){

            for(int j = 0; j < i; j++){

                // condition true for strictly ncreasing subsequence - left < right
                if(nums[j] < nums[i]) {

                    dp[i] = Math.max(dp[j] + 1, dp[i]); 
                }
                // else ignore, won't form a strictly increasing subsequence
                
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}



//https://leetcode.com/problems/russian-doll-envelopes/
class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {

      
       // Sort arr in first dim - ascending order and descending in second dimension 
       Arrays.sort(envelopes, (x,y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);

       // Extract only the second dimension
       int[] dim2 = new int[envelopes.length]; 

       for(int i = 0; i < envelopes.length; i++){

            dim2[i] = envelopes[i][1];              
                
      } 

        return longestIncreasingSubsequence(dim2);
    }

    private int longestIncreasingSubsequence(int[] nums){

        int len = 0;

        int[] dp = new int[nums.length];

        Arrays.fill(dp,1);

        for(int i = 0; i < nums.length; i++){

            for(int j = 0; j < i; j++){

                // Satisfies LIS from left to right
                if(nums[j] < nums[i]){

                    // Current or better
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

                // else ignore, won't form a strictly increasing subsequence
              

            }
            
            len = Math.max(len, dp[i]);
        }

        return len;
    }
}





public class MaxRectangle {

    // https://leetcode.com/problems/maximal-rectangle

    // 85. Maximal Rectangle

     // Monotonic stack based solution - Similar to ractangle area from historam

    public int maximalRectangle(char[][] matrix) {

        int maxArea = 0; 
        
        int[] heights = new int[matrix[0].length];

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                
                heights[col] = matrix[row][col] == '1' ? heights[col] + 1 : 0;
            }

            int area = findMaxRectAreaOf1s(heights);
            maxArea = Math.max(maxArea,area);   
        }       
        return maxArea;
    }

    private int findMaxRectAreaOf1s(int[] heights){

       
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        

        for(int i = 0; i <= heights.length; i++){
                        
            while(!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i])){

                int mid = stack.pop();
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek(); 
                int rightBoundary = i;

                int length = rightBoundary - leftBoundary - 1;
                int height = heights[mid];

                int area = length * height;
                maxArea = Math.max(maxArea, area);

            }

            if(i < heights.length) stack.push(i);
        }

        return maxArea;

    }    

}


// LC 901 https://leetcode.com/problems/online-stock-span/

// Time : O(1) amortized
// Space : O(N)

public class StockSpanner {

    Stack<int[]> stack; //int[] contains price at 0 and span at index 1

    public StockSpanner() {
        stack = new Stack<>();        
    }
    
    // Span can either be 1 (previous price is higher than current) or 1 + span of previous day price. 
    public int next(int price) {
        
        int span = 1; //1 day

        while(!stack.isEmpty() && price >= stack.peek()[0]){

            span += stack.pop()[1];  //Get pre-computed span and add 1 day to it.
        }
        
        stack.push(new int[] {price, span});

        return stack.peek()[1]; // last element in stack has precoumputed span 
    }
}



//LC https://leetcode.com/problems/shortest-path-in-binary-matrix
// LC 1091. Shortest Path in Binary Matrix

// Custom implementation

/* Approach - BFS - Guranteed shortest path in undirected graph
            
 DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
*/



class ShortestPathBinaryMatrix {
    
    static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base - start and end cell should contain 0 
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;


        Queue<int[]> q = new LinkedList<>(); // bfs queue
        q.add(new int[]{0,0});

        grid[0][0] = 1; // to calculate distance starting from 1 unit


        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];
           

            //Check if last right exit cell 
            // This check can also be done below the dirs loop, but will fail for input [[0]]
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return grid[row][col];
            } 

            for(int[] dir : dirs){

               int newRow = row + dir[0];
               int newCol = col + dir[1];

                // Why not replace walls 1 with -1 ?? 
                //-> We will never go back to cell 0,0 which is '1' but not a wall, so can skip this modification. 
                
                // Why ignore grid[newRow][newCol] > 0 ??  
                // -> 1 - wall. Any other positive number is already a shorter path in progress

                if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length 
                    || grid[newRow][newCol] > 0){
                       
                        continue;
                }

                q.add(new int[] {newRow,newCol});

                grid[newRow][newCol] = dist + 1; 

                //System.out.println(dist + 1);               
                
            }        
        }   

        return - 1;                          
    }

      
}



public class ShortestPathBinaryMatrixAlt {


     static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };
    
    public int shortestPathBinaryMatrix(int[][] grid) {


        //base
        if(grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;

        Queue<int[]> q = new LinkedListImplementation<>();
        q.add(new int[]{0,0});

        grid[0][0] = 1;

        while(!q.isEmpty()){

            int[] n = q.remove();

            int row = n[0], col = n[1];

            int dist = grid[row][col];

            //Check if last right cell
            if(row == grid.length - 1 && col == grid[0].length - 1){
                return dist;
            }

            List<int[]> neighbors = getNeighborsin8D(n, grid);
                                   
            for(int[] neighbor : neighbors){

                int r = neighbor[0], c = neighbor[1];                
                grid[r][c] = dist + 1;
                q.add(neighbor);
            }
        }   

        return - 1;                          
    }

    private List<int[]> getNeighborsin8D(int[] node, int[][] grid){

            List<int[]> neighbors = new ArrayList<>();

            for(int[] dir : dirs){

                int[] newDir = new int[] { (dir[0] + node[0]), (dir[1] + node[1])};

                if(newDir[0] >= 0 && newDir[0] < grid.length && 
                        newDir[1] >= 0 && newDir[1] < grid[0].length &&
                        grid[newDir[0]][newDir[1]] == 0
                        ){

                    neighbors.add(newDir);            
                }
            }
            return neighbors;
    }


}



// LC 54 https://leetcode.com/problems/spiral-matrix/

class SpiralMatrix1 {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();     

        int row = 0, col = 0, rowEnd = matrix.length - 1, colEnd = matrix[0].length - 1;

        
        while(row <= rowEnd && col <= colEnd){

            // Traverse from left to right
            for(int c = col ; c <= colEnd; c++){
                res.add(matrix[row][c]);            
            }

            row++; // Prevent reading the last cell in row while traversing downwards (top right)

            // Traverse from top to bottom
            for(int r = row; r <= rowEnd; r++){
                res.add(matrix[r][colEnd]);                
            } 

            colEnd--; // Prevent reading the last cell in col while traversing row (bottom right)          

            // Traverse from right to left
            
            if(row <= rowEnd){
                for(int c = colEnd; c >= col; c--){
                    res.add(matrix[rowEnd][c]);      
                }               
            }            
            rowEnd--;

            if(col <= colEnd){

                // Traverse from bottom to top    
                for(int r = rowEnd; r >= row; r--){
                    res.add(matrix[r][col]);    
                } 
            }             

            col++;
        }
        
        return res;
        
    }
}




//https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPoints {
    
    /*Logic : distance to a point from  origin for (a1,a2) = a1*a1 + a2*a2 .  
    How ? -> D = root( (x2 - x1)^2 + (y2 - y1)^2)  -> Here origin P(x2, y2) = (0,0) -> root( (0 - x1) + (0 - x2)^2) -> x1^2 + x2^2 
              Use priority Queue - max heap to add all points, then pick kth point*/
    
    public int[][] kClosest(int[][] points, int K) {
        
        int[][] result = new int[K][2];        
           
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        //PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b)->( Math.sqrt( (b[1] - a[1]) * (b[1] - a[1])  + (b[0] - a[0]) * (b[0] - a[0])  ) ) );
        
        
       PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            
            
            public int compare(int[] a, int[] b){
                
                return ( ( (b[0]*b[0]) + (b[1]*b[1]) ) - ( (a[0]*a[0]) + (a[1]*a[1]) )  );
                
            }            
            
            
        });
        
        
        
        for(int[] point : points){
            pq.add(point);
            
            if(pq.size() > K) pq.remove(); 
        }
        
        for(int i = 0; i < K; i++){
            result[i] = pq.remove();            
        }      
        
        return result;        
    }
}




// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986


// Time O(M + N) 
// Space O (min(M,N)) - Due to result array


/*
                   s 0 ---------- e 3
                           st 2-----------en 6            

                Now, intersection is between st 2 and e 3.

                if st 2 < e 3, then merge.

                To calc intersection points : 

                 low -> max(s 0 and st 2) -> st 2
                  high -> min(e 3 and en 6) -> e 3 
            */


public class IntervalListIntersections {

    // No priority queue needed as : description: Each list of intervals is pairwise disjoint and in sorted order

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        
        List<int[]> overlaps = new ArrayList<>();

        int i = 0, j = 0;

        while(i < firstList.length && j < secondList.length){

            int[] A =  firstList[i], B =  secondList[j];

            int low = Math.max(A[0], B[0]);
            int high = Math.min(A[1], B[1]);

            if(low <= high){
                overlaps.add(new int[] {low, high}); // Not merging intervals, but just calc intersection region
            }    

            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]);

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}



// https://leetcode.com/problems/maximum-width-ramp/
// LC 962. Maximum Width Ramp


public class MaxWidthRamp {

    //Time O(n) Space O(n)
    public int maxWidthRamp(int[] nums) {

        int max = 0;

        Stack<Integer> stack = new Stack<>();

        // Find NGE, so monotocally striclty decreasing
        for(int i = 0; i < nums.length; i++){

            if(stack.isEmpty() || nums[i] < nums[stack.peek()]){
                stack.push(i);
            }            
        }

        //System.out.println(stack);

        
        for(int j = nums.length - 1; j >= 0; j-- ){

            // If loop can pick only the first element in stack with i < j, nums[i] < nums[j],. but there cud be more
            while(!stack.empty() && nums[stack.peek()] <= nums[j]){
                
                max = Math.max(max, j - stack.pop());
            }
        }

        return max;   

        
    }
}






//

/*Logic : cache - fixed size queue and recently used items are in front and least used are are at end of list. So we maintain a Doubly Linked List.
      Operations: get(key)--> value in O(1). So we maintain a hashmap and when the key is accessed, then we re insert the Node(key,value) back into the front of queue.
                  put(key,value) --> we create a Node and insert into front of D. linked list(Deque), then make an entry/modify entry in hashmap. 

 */


 class LRUCache {
 
 
    Map<Integer,DLLNode> map;
 
     DLLNode head = null;
     DLLNode tail = null; 
     int capacity;
 
     public LRUCache(int capacity) {        
         map = new HashMap<>(capacity);   
         this.capacity = capacity;    
     }
             
     public void put(int key, int value) {
 
         if(map.containsKey(key)){
 
             DLLNode node = map.get(key);
 
             findAndRemove(node);            
             
             // Update new value
             DLLNode newNode = new DLLNode(key, value);
             addFront(newNode);
             map.put(key,newNode);
 
         }
 
         else {
 
             DLLNode node = new DLLNode(key,value);
 
             if(map.size() >= capacity){
 
                 DLLNode removed = removeLast();
                 map.remove(removed.key);
             }                  
 
             addFront(node);   
             map.put(key, node);                     
         }       
 
     }
 
 
     public int get(int key) {
 
         if(map.containsKey(key)){
                 
             DLLNode node = map.get(key);          
             int val = node.value;
             findAndRemove(node);           
             addFront(node);
             return val;
         }
 
         return -1;
         
     }
 
     public void addFront(DLLNode node){
 
         if(head == null){
             head = tail = node;                              
         } 
 
         else {
             node.next = head;
             head.prev = node;
             head = node;            
         }                   
     }
 
     public DLLNode removeLast(){
 
         if(tail == null) return null;
 
         else if(head == tail){
             DLLNode d = head;
             head = tail = null;
             return d;
         }
         else {
             DLLNode d = tail;    
             tail = tail.prev;
             tail.next = null;
             return d;
         }            
              
     }
 
 
     public void findAndRemove(DLLNode node) {
                
         if (head == null) return;
         
         else if(head == tail){
             head = tail = null;
         }
     
          // Check if the node to be removed is the head
         else if (head == node) {
           
             head = head.next;
             head.prev = null;           
         }
 
         // Check if the node to be removed is the tail
         else if (tail == node){
             tail = tail.prev;
             tail.next = null;            
         }
 
         // Somewhere in middle
         else {
 
             node.prev.next = node.next;
             node.next.prev = node.prev;          
 
         }
     
     
 }
 
 
 
 
     class DLLNode {
         int key;
         int value;
         DLLNode next;
         DLLNode prev;
 
         public DLLNode(int key, int value){
             this.key = key;
             this.value = value;            
         }   }
 
    
 }



 /*
 Approach : 

 Use two heaps: maxHeap for lower half, minHeap for upper half. Balance heaps after each add.
 For odd total elements, median is maxHeap top. For even, average of both heap tops.

 Keep two heaps. One stores lower half of nums, other stores the upper half.
 Keep the heaps balanced. lower can have one element more than upper (to satisfy odd number of elements condition)
 Upper always can have only as much elements as lower.

 // Imagine a BST structure with median at root. Here left and right subtrees are strored in heaps, the root itself is stored in lower half. (Hence lower heap can have one extra element than upper)  

// LC 295 : https://leetcode.com/problems/find-median-from-data-stream


*/



class FindMedianFromStream {
        
    PriorityQueue<Integer> upperHalf;
    PriorityQueue<Integer> lowerHalf;

    public FindMedianFromStream() {

        upperHalf = new PriorityQueue<>();             
        lowerHalf = new PriorityQueue<>((x,y)-> y - x);
    }
    
    public void addNum(int num) {

        // Same as BST condition
        if(lowerHalf.isEmpty() || num < lowerHalf.peek()){
            lowerHalf.add(num);
        }

        else upperHalf.add(num);

        // Rebalance based on size        
        
        // lower can have one additional element than upper, but not more than 1.
        if((lowerHalf.size() - upperHalf.size()) > 1){
            upperHalf.add(lowerHalf.remove());
        }        

        // Can have same or lesser than lower
        else if(upperHalf.size() > lowerHalf.size()){
            lowerHalf.add(upperHalf.remove());
        }

    }
    
    public double findMedian() {
        
        // A case where there are odd num of input numbers
        if(lowerHalf.size() >  upperHalf.size()){
            return lowerHalf.peek();
        }

        else return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
  

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */



 class ExpressionAddOperators {

    
    // Generte all combinations
        List<String> finalResult = new ArrayList<>();
        String nums = null;
        float target;
    
        public List<String> addOperators(String num, int target) {
    
            
                if(num == null || num.length() == 0) return new ArrayList<>();
    
                this.nums = num;
                this.target = target;
    
                recursive(0,0,0,0,new ArrayList<>());
    
                return finalResult;
        }
    
    
    
        private void recursive(int index, long previous, long current, long value, List<String> operations){
    
            System.out.println(operations);    
    
            // end of string, check target
            if(index == nums.length()) {
    
                if(value == this.target && current == 0){
    
                    StringBuilder sb = new StringBuilder();
    
                    // Ignore sign + : [+, 1, -, 2, -, 3]
                    for(int i = 1; i < operations.size(); i++){
                        sb.append(operations.get(i));
                    }   
    
                    finalResult.add(sb.toString());
                }     
                
                return;
            }
            
            // Extend operations by one digit
    
            current = (current * 10) + nums.charAt(index) - '0';
            String current_str = Long.toString(current);
    
            //No op, extend the num by a digit each time - 123 -> 12 + 3 or 105 -> 10 + 5
            if(current > 0) {
    
                recursive(index + 1, previous, current, value, operations);
            }
    
    
            // Why start with addition ? 
            // qns : to insert the binary operators '+', '-', and/or '*' between the digits of num
    
            // Addition
            operations.add("+");
            operations.add(current_str);
    
            recursive(index + 1, current, 0, value + current, operations);
    
            operations.remove(operations.size() - 1);
            operations.remove(operations.size() - 1);
    
            // Order of sub and mul can be changed
            if(operations.size() > 0) {
    
                // Subtraction 
                operations.add("-");
                operations.add(current_str);
    
                recursive(index + 1, -current, 0, value - current, operations);
    
                operations.remove(operations.size() - 1);
                operations.remove(operations.size() - 1);
    
                // Multiplication
                operations.add("*");
                operations.add(current_str);
    
                recursive(index + 1, current * previous, 0, value - previous + (current * previous), operations);
    
                operations.remove(operations.size() - 1);
                operations.remove(operations.size() - 1);
    
            }        
        }   
    
    }


    /*  List<String> operations call stack
    
    123, target = 6
    []
    [+, 1]
    [+, 1, +, 2]
    [+, 1, +, 2, +, 3]
    [+, 1, +, 2, -, 3]
    [+, 1, +, 2, *, 3]
    [+, 1, -, 2]
    [+, 1, -, 2, +, 3]
    [+, 1, -, 2, -, 3]
    [+, 1, -, 2, *, 3]
    [+, 1, *, 2]
    [+, 1, *, 2, +, 3]
    [+, 1, *, 2, -, 3]
    [+, 1, *, 2, *, 3]
    */
    
    /*
    
    Adding no-op to generate additional combinations
    123, target = 123
    []
    []
    []
    []
    [+, 123]
    [+, 12]
    [+, 12]
    [+, 12, +, 3]
    [+, 12, -, 3]
    [+, 12, *, 3]
    [+, 1]
    [+, 1]
    [+, 1]
    [+, 1, +, 23]
    [+, 1, -, 23]
    [+, 1, *, 23]
    [+, 1, +, 2]
    [+, 1, +, 2]
    [+, 1, +, 2, +, 3]
    [+, 1, +, 2, -, 3]
    [+, 1, +, 2, *, 3]
    [+, 1, -, 2]
    [+, 1, -, 2]
    [+, 1, -, 2, +, 3]
    [+, 1, -, 2, -, 3]
    [+, 1, -, 2, *, 3]
    [+, 1, *, 2]
    [+, 1, *, 2]
    [+, 1, *, 2, +, 3]
    [+, 1, *, 2, -, 3]
    [+, 1, *, 2, *, 3]
    
     */
    
    /* 105 and target 5 
    
    []
    []
    []
    []
    [+, 105]
    [+, 10]
    [+, 10]
    [+, 10, +, 5]
    [+, 10, -, 5]
    [+, 10, *, 5]
    [+, 1]
    [+, 1, +, 0]
    [+, 1, +, 0]
    [+, 1, +, 0, +, 5]
    [+, 1, +, 0, -, 5]
    [+, 1, +, 0, *, 5]
    [+, 1, -, 0]
    [+, 1, -, 0]
    [+, 1, -, 0, +, 5]
    [+, 1, -, 0, -, 5]
    [+, 1, -, 0, *, 5]
    [+, 1, *, 0]
    [+, 1, *, 0]
    [+, 1, *, 0, +, 5]
    [+, 1, *, 0, -, 5]
    [+, 1, *, 0, *, 5]
    */




    // Decimal variant 

    class AddBinary {
        public String addBinary(String a, String b) {
            
            //to return result
            StringBuilder sb = new StringBuilder();
            
            int i = a.length() - 1;
            int j = b.length() - 1;
            int carry = 0;
            
            while(i >= 0 || j >= 0){
                
                int sum = carry; // each loop, begin with adding carry
                
                if(i >= 0){
                    sum += a.charAt(i) - '0';  // convert char to int
                    i--;         
                }
                
                if(j >= 0){
                    sum += b.charAt(j) - '0';
                    j--;
                }
                
                // append at beginning
                sb.insert(0,sum % 2);  // if 1 + 1 or 0 + 0, then sum = 0. if 1 + 0, sum = 1
                carry = sum / 2; // if sum is 1 + 0 or 0 + 0 carry is 0, if sum is 1 + 1, then carry is 1;            
            }      
            
            if(carry > 0) sb.insert(0,carry); // add the remaining carry, case when loop is complete
            
            return sb.toString();        
        }
    }    

    //https://leetcode.com/problems/find-pivot-index/
    // sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
    class FindPivotIndex  {
        public int pivotIndex(int[] nums) {
    
            int leftSum = 0, totalSum = 0;
    
            for(int n : nums){
                
                totalSum += n;
            }
    
    
            for(int i = 0; i < nums.length; i++){
    
                // sum of all nums to right fo this idx = 
                // (totalSum - leftSum - nums[i])   
    
                if(leftSum == (totalSum - leftSum - nums[i])){
                    return i;
                }
    
    
                // sum to the left of current index. 
                // Update it like prefix sum
                else {
    
                    leftSum += nums[i];
                }
            }
    
            return -1;
        }
    }

    //https://leetcode.com/problems/diagonal-traverse/
    class DiagonalTraverse {

        /*
            approach - start from all elements in forst row and last column;
        */
        public int[] findDiagonalOrder(int[][] mat) {
            
            if(mat == null || mat.length == 0) return new int[]{0};
    
            int n = mat.length;
            int m = mat[0].length;
    
            int res[] = new int[m * n];
            int r = 0;
    
            List<Integer> tempList = new ArrayList<>();
          
            for(int i = 0; i < n + m; i++){
    
                tempList.clear();
    
                 int row = i < m ? 0 : i - m + 1;
                 int col = i < m ? i : m - 1;   
    
                 //System.out.println("row " + row + " col " + col);
    
                 while(row < n && col >= 0){
    
                    tempList.add(mat[row][col]);
                    row++;
                    col--;
                 } 
    
                 if(i % 2 == 0) Collections.reverse(tempList);
                
                 for(int num : tempList) res[r++] = num;   
    
            }       
            return res;
        }
    }


// meeting scheduler 
//https://leetcode.com/problems/meeting-scheduler
class MeetingScheduler  {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {


        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int pointer1 = 0, pointer2 = 0;


        while (pointer1 < slots1.length && pointer2 < slots2.length) {
            
            // find the boundaries of the intersection, or the common slot
            int intersectLeft = Math.max(slots1[pointer1][0], slots2[pointer2][0]);
            
            int intersectRight = Math.min(slots1[pointer1][1], slots2[pointer2][1]);
            
            if (intersectRight - intersectLeft >= duration) {
                return new ArrayList<Integer>(Arrays.asList(intersectLeft, intersectLeft + duration));
            }
            // always move the one that ends earlier
            if (slots1[pointer1][1] < slots2[pointer2][1]) {
                pointer1++;
            } else {
                pointer2++;
            }
        }
        return new ArrayList<Integer>();
    }
    }
}


    



    //Not abolutely reqd :

    class OverlappingRectangles{

        public boolean checkOverlap(Point l1, Point r1, Point l2, Point r2){
              
              /*4 cases of Non-Overlapping (draw and visualize for better understanding) : 
              
              R2 is left of R1 : compare x-axis values of R1 top left to R2 bottom right
              R2 is right of R1 : compare x-axis values of R2 top left to R1 bottom right 
              R2 is above R1 : compare y-axis values of R2 top left to R1 bottom right
              R2 is below R1 : compare y-axis values of R1 top left to R2 bottom right */
                     
              // case 1 || case 2 || case 3 || case 4 
              if(l1.x > r2.x || l2.x > r1.x || l2.y < r1.y  || l1.y < r2.y ) return false;
                                
              return true;         
        }
        
           public static void main(String[] args){
           
           Point l1 = new Point(0,10);
           Point r1 = new Point(10,0);
           Point l2 = new Point(5,5);
           Point r2 = new Point(15,0);
           
           OverlappingRectangles obj = new OverlappingRectangles();
            System.out.println(obj.checkOverlap(l1, r1, l2, r2));      
           
        } 
     }
     
     
      class Point{
          
           int x;
           int y;
           
           public Point(int x, int y){
              this.x = x;
              this.y = y;
           }   
      }    








class Sqrt {
    public int mySqrt(int x) {
        
        /* 0------------root x ------------------x */
        
        int start = 1;
        int end = x;
        int result = 0;
        
        while(start <= end){
            
            int mid = start + (end - start) / 2 ;
            
        
            if(mid <= x / mid){
              
                start = mid + 1; 
                result = mid;
            }
            
            else {
                
                end = mid - 1;                
                
            }
            
                    
        }
        
        return result;
        
    }
}