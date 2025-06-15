
// LC : 398 https://leetcode.com/problems/random-pick-index/

// Given a target to pick
public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    
    Random rand = new Random(); 

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = rand.nextInt(0,numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
    }

}

/**
    Optimized Approach : 

    Reservoir sampling : Lets say we have a stream of length n. We pick a window of size k and fill values from stream. f
    For the remaining n - k values in stream, in a loop, eah time we pick a rand num from 0 - k.(some index in the window). Replace num at that index with ith num
    remaining in stream
    
    

    {1, 2, 3, 3, 3}

    Here we have to pick with equal probabilities;

    When you see the first target (count = 1), rand.nextInt(1) always returns 0 → you must pick it.

    For the second target (count = 2), rand.nextInt(2) gives 0 or 1 → 50% chance to pick the new one.

    For the third target (count = 3), 1/3 chance to pick it, 2/3 chance to keep the old one.
        
    And so on.

 */

// Time O(N) Space O(1)

// Given a target to pick
class RandomPickIndexOptimized {

    // acts as a pointer s- increase scope from local to class level
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

          // Wait till count matches index . Lets say we have {1, 2, 3, 3, 3} and target is 3  
          if(samples[i] == target){

              count++; // matching window
              
                          //we pick the current number with probability 1 / count (reservoir sampling)
              if(rand.nextInt(count) ==  0){  // rand.nextInt(5) -> 0 - 4
                  idx = i; //
              }
          }
      }


      return idx;        
      
  }
}


// Target is not provided 
public class PickRandomWeightProbability {




    /*
        input = [1 2 4]
        total sum = 7, so 1 should have 1 out of 7 chance, 2 -> 2/7 and 4 -> 4/7
        probablity of occurenceCan be represented as [1 2 2 4 4 4 4]. 
        Then we generate a random number between 0 and 7, like 5.42
        so return num at index 5 -> 4

       

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


     //Optimize the array [1 2 2 4 4 4 4]
     // input : [1 2 4]  
     //Use prefix sum instead -> [1 3 7] 
     //generate a random number between 0 and 7, like 5.42
     //Find index where 5.42 < total sum (7) - index 2. Return index 2, corresponmding to 4 in weights array.  
     
     public int pickIndex() {
         
        int totalSum  = prefixSum[prefixSum.length - 1];

        double target  = new Random().nextDouble(totalSum); //rand.nextDouble(7) -> 0.00 - 6.99
 
        
         // target range
         for(int i = 0; i < prefixSum.length; i++){
 
             if(target < prefixSum[i]){
                 return i;
             }
         }
         
         //or // Find index at target < total sum
        
        int low = -1, high = prefixSum.length - 1;

        while( low + 1 < high){

            int mid = low + high - low / 2;

            if(target > prefixSum[mid]) low = mid;

            else high = mid;
        }
        return high;  
 
         return -1;
 
     }


   
}











//https://leetcode.com/problems/3sum/
class 3Sum {
    
    
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
                
        // For each number
        for(int i = 0; i < nums.length - 2; i++){
                        

           // skip same numbers             
           if(nums[i] <= 0 && (i == 0 || nums[i - 1] != nums[i])){

                 twoSum(nums, i, result);   
                                      
           }
                        
        }
        
        return result;
        
        
    }
    
     private int[] twoSum(int[] nums, int i, List<List<Integer>> result) {

        // Start from nest number to i each time
        int low = i + 1, high = nums.length - 1;

        while(low < high){

            int sum = nums[i] + nums[low] + nums[high];
            
            if(sum < 0) low ++;

            else if(sum > 0) high--;

            else {

                result.add(Arrays.asList(nums[i],  nums[low], nums[high]));

                 low++;
                 high--;

                // skip duplicates
                while (low < high && nums[low] == nums[low - 1]) low++;

            } 
        }

        return new int[] {-1, -1};        
    }
}




// https://leetcode.com/problems/max-consecutive-ones-iii/
//  binary array nums
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

// Time O(1), space O(1)
//// Non optimized for large dataset - Can lead to collision and other issues with hashmap when a large dataset is used.
 class SparseVector {
    
        Map<Integer,Integer> map = new HashMap<>();
        
        SparseVector(int[] nums) {
                   
            for(int i = 0; i < nums.length; i++){
                
                if(nums[i] > 0) map.put(i, nums[i]);
            
            }
        
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec2) {
    
            int dotProduct = 0;
    
           // key is non zero index     
           for(Integer key : map.keySet()){
    
                // if second sparse vector also contains non-zero key
                if(vec2.map.containsKey(key)) {
    
                    dotProduct += vec2.map.get(key) * map.get(key);
                }
    
           }
           
    
           return dotProduct;
                    
        }
}
    
    // Your SparseVector object will be instantiated and called as such:
    // SparseVector v1 = new SparseVector(nums1);
    // SparseVector v2 = new SparseVector(nums2);
    // int ans = v1.dotProduct(v2);




// LC  1570 : https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

// practical version.
class SparseVectoOpt {
    
    List<int[]> vectors;
    
    SparseVector(int[] nums) {
        
        vectors = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            
            if(nums[i] > 0) vectors.add(new int[] {i, nums[i]});
        
        }
    
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        
       // two pointers
       int i = 0, j = 0, product = 0;

       while(i < vectors.size() && j < vec.vectors.size()){

            int[] vectors1 = vectors.get(i);
            int[] vectors2 = vec.vectors.get(j);


            // if index is the same [0] contains index of non zero nums
            if(vectors1[0] == vectors2[0]){

                product += vectors1[1] * vectors2[1];

                i++;
                j++;
            }
            
            // got to move pointer to corret index, till vce1 index = vec 2 index
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
            // filter to handle repeated product
            if(res.size() == 0 || res.get(res.size() - 1).get(0) != product){

                res.add(Arrays.asList(product,freq));
            }
            else {
                 int existingFreq = res.get(res.size() - 1).get(1);
                 res.remove(res.size() - 1);
                 
                 res.add(Arrays.asList(product,existingFreq + freq));
            }

            // Update frequencies
            // why ? example : encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
            // Both array lengths are not equal    

             encoded1[i][1] -= freq;
             encoded2[j][1] -= freq;

             // the min freq value   
             if(freq1 == freq) i++; // all numbere are consumed, then move to next 
             if(freq2 == freq) j++; // all numbere are consumed, then move to next            

        }
        
        return res;
    }
}


// https://leetcode.com/problems/missing-number/description
// Input: nums = [9,6,4,2,3,5,7,0,1]    
// Output: 8  - summation   = 9 (9 + 1)/2 -> 45, sum = 37
class MissingNumber {

    public int missingNumber(int[] nums) {

        // O(n) time

        // Summation of nums 0 -n :  n (n + 1) / 2
 
        int summation =nums.length * (nums.length + 1) / 2;

        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }        

        return summation - sum;
    }
}



// https://leetcode.com/problems/flatten-nested-list-iterator/

// Can we use Queue instead ?/

// Input: nestedList = [[1,1],2,[1,1]]
// Output: [1,1,2,1,1]
public class NestedIterator implements Iterator<Integer> {

    List<Integer> flatList = new ArrayList<>();

    int idx = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
             flatten(nestedList);
    }

    // recursively flatten the nnested data structure
    private void flatten(List<NestedInteger> nestedList){

        for(NestedInteger nested : nestedList){

            if(nested.isInteger()) flatList.add(nested.getInteger());

            else flatten(nested.getList());
        }
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


//Input: nestedList = [[1,1],2,[1,1]]
// Output: 10
// Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

class NestedIntegerDepthSumProd {
    
    public int depthSum(List<NestedInteger> nestedList) {
       
        Queue<NestedInteger> q = new LinkedList<>();

        int result = 0;
        
        int level = 1;

        // Add all elements in current list to queue
        for(NestedInteger nested : nestedList){
           q.add(nested);
        }

        while(!q.isEmpty()){
            
            int size = q.size();
            
            for(int i = 0;i < size; i++){
                
              NestedInteger item = q.remove();
              
              if(item.isInteger()){

                result += item.getInteger() * level;
              
              }   

              else{

                List<NestedInteger> nList = item.getList();

                for(NestedInteger n : nList){
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

    //  2^8 -> square (half of 8) -> square (2^4 ) 
    //  2^4 -> square (half of 4) -> square (2^2)
    // 2^2 -> square (half of 2) -> square(1)
    // 2^1 -> Now n/2 -> 0 , returns 1
    // Square the result: 16 * 16 = 256

    // 2^3 -> 2 pow 2 * 2 -- for odd 
    //Calculate 2^1 = 2, Square the result: 2 * 2 = 4, Multiply by remaining 2: 4 * 2 = 8
  

    // Time O(log n) space O(log n) 
    public double fastPower(double x, int n){
        
       if(n == 0) return 1.0; // recurse till n == 0, that is when it returns 1
        
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
// ocean is to the right of the buildings.
// Input: heights = [4,2,3,1]
// Output: [0,2,3] // indexes

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

               maxSoFar = heights[i];
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
// Input: nums = [0,1,3,50,75], lower = 0, upper = 99
// Output: [[2,2],[4,49],[51,74],[76,99]]

public class MissingRanges {


    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        List<List<Integer>> res = new ArrayList<>();    

        int i = 0;

        while(i < nums.length){

            if(nums[i] != lower){ // can't compare nums[i] and i as some numbers before 0th index may be missing. lower < nums[0] ;  // missing numbers in the beginning 
                       
                res.add(Arrays.asList(lower, nums[i] - 1));
                
                lower = nums[i]; // Have covered lower - nums[i] - 1, so next start from nums[i]
            }
           
            i++;
            lower++;                       
        }


        // missing numbers in the end 
        if(lower <= upper){
               
            res.add(Arrays.asList(lower, upper));
        }

        return res;
        
    }
}

//Variant: static range from 0-99, also output was wanted in a string that had 1-2 number gaps to be a singular number, 3+ number gaps to be a range.
//Input = [1, 4, 7, 30]
//Output = '0, 2, 3, 5, 6, 8-29, 31-99'



// LC 167 https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// Given a 1-indexed array of integers
//Input: numbers = [2,7,11,15], target = 9
//Output: [1,2]


///// NOT a BINARY SEARCH Based Sol
public class TwoSum2 {


    public int[] twoSum(int[] numbers, int target) {

        int low = 0, high = numbers.length - 1;

        while(low < high){

            int sum = numbers[low] + numbers[high];

            // + 1 is due to starting index should be 1 as per question
            if(sum == target) return new int[] { low + 1, high + 1}; 

            else if(sum < target) low++;

            else high--;
        }

        return new int[] {-1, -1};        
    }
}



class MissingRangesVariant {
    
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        List<List<Integer>> res = new ArrayList<>();    

        int i = 0;

        while(i < nums.length){

            if(nums[i] != lower){
       

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
// next lexicographically greater permutation of its integer
// Input: nums = [1,2,3]
// Output: [1,3,2]

// Remember : 1 3 5 4 2   op 1 4 2 3 5 and not 1 4 5 3 2
public class NextPermutationLexi {


    public void nextPermutation(int[] nums) {


        // Find a number that satisfies a[i] < a[i + 1] - Basically find a smaller digit that can be replaced with higher weight
        int i = 0;

        for (i = nums.length - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

       


        // Find a digit that is greater than at index i (from the end of array), to effectively replace smaller digit with larger digit

        if (i >= 0) {  // example 8 6 5 4 2 or 3 2 1, here i in previous step will be -1

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




//https://leetcode.com/problems/maximum-subarray
// Simple sliding window
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
class MaxSubarray {

    // One pass Kadane's algo
    // Time : O(n), Space O(1)
    public int maxSubArray(int[] nums) {

        //Kadane's algo

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for(int num : nums){

            maxEndingHere += num;

            maxSoFar = Math.max(maxSoFar, maxEndingHere);

            if(maxEndingHere < 0) maxEndingHere = 0; //reset

        }

        return maxSoFar;
        
    }
}






// https://leetcode.com/problems/subarray-sum-equals-k/description/
// LC 560

/*
        [1 2 3] k = 3
    
        Create map of prefix Sum at each index and its occurence. 
        Startign from 0,1 ->  Sum of 0 occurs one time
        // Why ? , When we check if (prefixSum - k) exists, example 3 - 3 exists, then we check if 0 exists
     
        Now similar to 2 sum, find if current prefixSum - K is present in the stored prefixSums.
        
        sum(nums[i,j] = prefixSum[j] -  prefixSum[i - 1])
        prefixSum[i - 1] = prefixSum[j] - k; 

          
    In theory -  [1 2 3] , target k = 3

    prefix sum at 1th index = 3, k = 3 so prefiSum - k = 0 -> this entry is in hashmap prepopulated, 
    So update in map prefixsum and count -> 3 : 1

    Next at index 2 we have prefixSum = 6, k = 3. prefixSum - k = 3. --> check in map, 
     we have an entry made previously 3 : 1, so add to answewr and update map entry to : 6 : 1
  
    
     {0=1, 1=1}
    {0=1, 1=1, 3=1}
    {0=1, 1=1, 3=1, 6=1}
  
  */
    
       
    

class SubArraySumEqualsK {
    
    
        // Time and space : O(N)
        public int subarraySum(int[] nums, int k) {
    
            int prefixSum = 0, count = 0;
    
            Map<Integer, Integer> prefixSumOccurence = new HashMap<>();
            
            // pre-populate
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
            
            if(nums1[i] >= nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }

            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        
        // while i >= 0 is not required as it will be in place as we merger into nums1 array

        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }        
       
    }
}


//https://leetcode.com/problems/maximum-swap/
class MaxSwap {



    public int maximumSwap(int num) {

        char[] nums = Integer.toString(num).toCharArray(); // Pay attention

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
                break; // Pay attention
            }
        }


        return Integer.parseInt(new String(nums)); // Pay attention
        
    }


    private void swap(char[] nums, int i, int j){

        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}

// https://leetcode.com/problems/kth-largest-element-in-an-array

class KthLargestElementInArray {
   
    public int findKthLargest(int[] nums, int k) {

        quickSelect(0, nums.length - 1, nums, k);

        return nums[nums.length  - k];
    }

    // Quick select 
    private void quickSelect(int left, int right, int[] nums, int k){
  
        if(left <= right){
      
            int pivotIndex = left + new Random().nextInt(right - left + 1);
            swap(pivotIndex, right, nums);  // Setting random pIndex for partitioning

            // like mid in binary search
            int pIndex = partition(left,right,nums);

            if(pIndex == nums.length - k) return;

            else if(pIndex < nums.length - k) quickSelect(pIndex + 1,right,nums,k);  

            else  quickSelect(left,pIndex - 1,nums,k);
        }
       
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
    
    /*
        Use a stack to keeop track of start - end of a func.
        Timer to keep track of time
    */
    // Time O(n), space O(n/2) - only start of func is stored
    // Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    // Output: [3,4]
    public int[] exclusiveTime(int n, List<String> logs) {

        // n - num of unique functions
        int[] res = new int[n];    

        Stack<Integer> stack = new Stack<>();

        int timePrev = 0;

        // pre-populate stack
        String[] logPrev = logs.get(0).split(":");
        
        stack.push(Integer.parseInt(logPrev[0])); //id
        
        timePrev = Integer.parseInt(logPrev[2]);


        // Process logs 
        for(int i = 1; i < logs.size(); i++){
                        
            String[] log = logs.get(i).split(":");

            // Process log
            int id = Integer.parseInt(log[0]);
            String action = log[1];
            int timeCur = Integer.parseInt(log[2]);
            
            // optimization
            if(action.equals("start")){
                    
                // Update time cosumed by previpus func. Sort of paused the process                              
                if(!stack.isEmpty()){

                    int idx = stack.peek();    

                    res[idx] += timeCur - timePrev;                    
                } 
                                
                timePrev =  timeCur; 

                stack.push(id); // current id          
            } 
            
            // action is "end" 
            else {
                
                // Update time consumed by current func that ended
                // func runs till end time, so add + 1        

                int idx = stack.pop();

                res[idx] += timeCur + 1 - timePrev;
                
                timePrev = timeCur + 1; // Update previous
            }      
           
        }        
        
        return res;
    }



    // Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    // Output: [3,4]
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
                int idx = stack.peek();
                res[idx]++;
                time++;
            }

            if(action.equals("start")) stack.push(id);
            
            // action is "end" 
            else {
                // func runs till end time, so
                int idx = stack.pop();
                res[idx]++; // + 1 as end process runs till end of that second
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



public class MinimumCostTickets {

    /*
     * There are 2 arrays which denote departing and returning flights with the respective indexes being 
     * time and the values of the array being the cost it takes for the flight.
     *  Return the minimum cost for a round trip provided the return flight can only be taken at a time post departing flight time 
     * (i.e if departing at time i, one can catch a returning flight only from time (i+1) onwards). 
     * For eg departing = [1,2,3,4] and returning = [4,3,2,1], 
     * the minimum cost for round trip will be 2 i.e departing[0] + returning[3]. Solve this is O(n) time
     * 
     * departing = [1,2,3,4]
     * returning = [4,3,2,1]
     */


     public int findMinCost(int[] departing, int[] returning) {

               
        int minReturn = Integer.MAX_VALUE;

        int minCost = Integer.MAX_VALUE;
    
        // Traverse backwards from the end to track minimum returning[i] for j > i
        
        for (int i = returning.length - 1; i > 0; i--) {
           
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
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i < N; i++){
            dp[i] = dp[i - 1] + dp[i - 2];            
        }
        
        return dp[N - 1];        
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
    // PQ
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



// https://leetcode.com/problems/copy-list-with-random-pointer/
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
            
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next); // we cannot assign node.next directly as we need to link to cloned node, not original node
            newNode.random = map.get(cur.random);
                   
            cur = cur.next;
        }         
        
        return map.get(head);
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








public class BasicCalculator2 {


    // LC 227 : https://leetcode.com/problems/basic-calculator-ii/
    // Input: s = "3+2*2"
    // Output: 7
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
           
             // if not for i == s.length() - 1), the code will process last number in step above and wont incluse it in calc
           
            if(!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1){

               if(operator == '+') {
                       
                    res += previousNum;
                    previousNum = number;
                }

                else if(operator == '-') {
                    res += previousNum;
                    previousNum = -number;
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
//Input: s = "(1+(4+5+2)-3)+(6+8)"
//Output: 23

public class BasicCalculator {

    public int calculate(String s) {

        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> operatorStack = new Stack<>();

        int num = 0;
        
        int res = 0;
        
        int operator = 1; // 1 for +, -1 for -

        for (int i = 0; i < s.length(); i++) {
            
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // if not for i == s.length() - 1), the code will process last number in step above and wont incluse it in calc
            if ((!Character.isDigit(ch) && !Character.isWhitespace(ch)) || i == s.length() - 1) {
               
                if (ch == '+') {
                    
                    res += operator * num;

                    
                    operator = 1;
                    num = 0;
                } 
                
                else if (ch == '-') {
                   
                    res += operator * num;

                    
                    operator = -1;
                    num = 0;

                } 
                
                else if (ch == '(') {

                    // Apply before push
                    numStack.push(res);
                    operatorStack.push(operator);
                    
                    res = 0;
                    
                    operator = 1;
                    num = 0;
                } 
                
                else if (ch == ')') {
                    
                    // example 5 + (2 + 4)
                    // 5 in num stack, '+' in operator stack, 2 stored in res.
                    //  res = 2

                    res += operator * num; // res += 1(sign) * 4

                    // numstack.pop() -> 5   operatorStack.pop() -> 1  previous res -> 6
                    res = numStack.pop() + operatorStack.pop() * res;

                    //reset
                    num = 0;

                }
            }
        }

        res += operator * num; // Apply last number if not already

        return res;
    }

  
}



//https://leetcode.com/problems/longest-increasing-subsequence/
// LIS
// Input: nums = [10,9,2,5,3,7,101,18]
// Output: 4
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
//Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//Output: 3
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


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




// https://leetcode.com/problems/making-a-large-island/
// You are allowed to change at most one 0 to be 1.
//Input: grid = [[1,0],[0,1]]
//Output: 3

class MakeALargeIsland {

    /*
        Approach : 

        Use modified dfs num of island count technique to get count of islands.
        The cells of each island should be replaced by the island id.
        Also keep a map to track island id and size.

        Now, visit cells in grid that are 0 and flip then to 1, one at a time.
        Check for neighboring cells - up, right, down, left to see if it hits any island.
        If so, then this flipped cell is a bridge.  

        Count sizes of neighboring islands using map and the current cell itself.    
     */

    // Time O(M X N) and Space - O(M X N) dfs call stack worst case
    
    int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    
    // <ids, island sizes>
    Map<Integer, Integer> map = new HashMap<>(); 

    public int largestIsland(int[][] grid) {

        int maxCount = 0;    

        int ids = 2; // Start with 2 as we have 0 and 1 representing water and island  
        
        // Get each island and add to map
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){
                   
                   if(grid[i][j] == 1){

                       int islandSize =  dfs(grid, i, j, ids);

                       map.put(ids++, islandSize);            

                   }               
            }           
        }  

        //System.out.println("map : " + map);

        // flip zeros and find if islands merge
        for(int i = 0; i < grid.length; i++){

            for(int j = 0; j < grid[0].length; j++){
                   
                   if(grid[i][j] == 0){

                       grid[i][j] = 1;

                       int newCount = findNeighbors(i, j, grid); 
                       
                       maxCount = Math.max(maxCount, newCount);

                       grid[i][j] = 0; // Flip it back

                   }               
            }           
        }  

     return (maxCount > 0) ? maxCount : map.get(2);

    }


    // Find neighbors - up, right, down and left who maybe previously seen island
    /*
            2 2 2 0 3  
            2 2 0 3 3
            2 2 0 0 3

            Flipping 0 in the middle, hits neighbors 2, 3, 0 and 2.
            So ignore repeated 2, just count sizes of 2, 3 and the 0 flipped cell. 

    */

    private int findNeighbors(int row, int col, int[][] grid){

        Set<Integer> seenIsland = new HashSet<>();

        int combinedSize = 0;

        for(int i = 0; i < dirs.length; i++){

            int newRow = row + dirs[i][0];
            int newCol = col + dirs[i][1];

            // Boundary check // skip neighbors outside bound
            if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) continue;    

            int islandId = grid[newRow][newCol];

            if(seenIsland.contains(islandId)) continue;

            seenIsland.add(islandId);

            int size = map.getOrDefault(islandId,0);

            combinedSize += size;
        }    

        return 1 + combinedSize; // Add 1 to count the flipped cell

    }


    // Calculate sizes
    private int dfs(int[][] grid, int row, int col, int islandId){
       
        // Set boundaries
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) return 0;
      
      // All cells of an island should have ids representing island.
      // Makes it easy to detect neighboring islands when later we flip 0s
        grid[row][col] = islandId; // + 1 in the end return
        
        int size = 0;

        for(int i = 0; i < dirs.length; i++){

            int newRow =  row + dirs[i][0];
            int newCol = col + dirs[i][1];
            
            size += dfs(grid, newRow, newCol, islandId);
        }

        return 1 + size;

    }
}





public class MaxRectangle {

    // https://leetcode.com/problems/maximal-rectangle

    // 85. Maximal Rectangle

     // Monotonic stack based solution - Similar to rectangle area from historam

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



    /*
     * Approach - Using monotonic stack
     * For each index, find the closest smaller element to the left and right.   
     * Monotonically increasing stack is used to find left and right boundaries.  
     * Calculate the area between these two elements.  
     * 
     */
    private int findMaxRectAreaOf1s(int[] heights){

       
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        

        for(int i = 0; i <= heights.length; i++){
                        
            // encounter an element in height arr that is smaller than the stack peek - heights[stack.peek()] >= heights[i]) 
            while(!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i])){

                int mid = stack.pop();
                int rightBoundary = i; // right smaller as  heights[stack.peek()] is greater than hei[i]   
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek(); // this is next peek after pop 
               

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


// Same as asterid collision
public class StockSpanner {

    Stack<int[]> stack; //int[] contains price at 0 and span at index 1

    public StockSpanner() {
        stack = new Stack<>();        
    }
    
    // Span can either be 1 (previous price is higher than current) or 1 + span of previous day price. 
    public int next(int price) {
        
        int span = 1; //1 day

        //stack.peek()[0] <= price  . previous conseq days price lesser than today
        // 
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


class ShortestPathBinaryMatrixWithPath {

/* Approach - BFS - Guranteed shortest path in undirected graph
            
 DFS in wort case needs to travel all opaths to finally end at shortest as it does depth first.
*/
    
    static final int[][] dirs = new int[][]{ 
            
            {-1,-1}, {-1,0}, {-1,1}, // Top row
            {0,-1},{0,1}, // Same row
            {1,-1},{1,0},{1,1} // Row below
            };

    // New cell coord : oarent cell coord        
    Map<String,String> path = new HashMap<>();

    String p = null;

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

                p = (grid.length - 1) +""+ (grid[0].length - 1);

                dfs((grid.length - 1) +""+ (grid[0].length - 1));            
            
                System.out.println("path : " + p); 

                System.out.println(path.get(new int[] {grid.length - 2,grid[0].length - 2}));   


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

                path.put(newRow+""+newCol,row+""+col);
                
                //System.out.println(dist + 1);               
                
            }   
           
        }   

        

        return - 1;                          
    }

    private void dfs(String coordinates){

        if(path.containsKey(coordinates)){

           String parentCellCoor = path.get(coordinates);

            p += "," + parentCellCoor ;

            System.out.println(p);

            dfs(parentCellCoor);
        }

        
    }
      
}


// LC 54 https://leetcode.com/problems/spiral-matrix/
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,3,6,9,8,7,4,5]
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

//Input: points = [[1,3],[-2,2]], k = 1
// Output: [[-2,2]]
// k closest points to the origin (0, 0).
public class KClosestPoints {
    
    /*Logic : distance to a point from  origin for (a1,a2) = a1*a1 + a2*a2 .  
    How ? -> D = root( (x2 - x1)^2 + (y2 - y1)^2)  -> Here origin P(x2, y2) = (0,0) -> root( (0 - x1) + (0 - x2)^2) -> x1^2 + x2^2 
              Use priority Queue - max heap to add all points, then pick kth point*/
    
         public int[][] kClosest(int[][] points, int K) {
        
        
        int[][] result = new int[K][2];
                
        //PriorityQueue<Point> pq = new PriorityQueue<>((p,q)-> eucledeanDist(p) - eucledeanDist(q));

        // Optimize by using max heap - Insert as many points as K, then at each iteration remove the top most (max value)
        // and insert a new point. In the end we will have 2 points with min euclid dist to origin
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((p,q)-> q[0] - p[0]); // p[0] -> eucl dist, p[1] -> point index
       

        for(int i = 0; i < points.length; i++){

            int[] entry =  new int[] { eucledeanDist(points[i]), i};


            if(pq.size() < K){

                pq.add(entry);

            }

            else if(pq.peek()[0] > entry[0] ){

                 pq.remove();            
                 pq.add(entry); 
            }
                      
        }
        
        
        int i = 0;
        
        while(K > 0){
            
            int[] entry = pq.poll();
            
            result[i] = points[entry[1]];
            K--;
            i++;
        }
        
        return result;
    }
    
    // eucledean distance from origin (0,0). Can return squaredDist for comparison
    private int eucledeanDist(int[] a){
        // Root( (x1 - x2) ^ 2 + (y1 - y2)^) = Root( (0 - x2) ^ 2 + (0 - y2) ^ 2)
        // = Root(x2 ^ 2 - Y2 ^ 2)
        return (a[0] * a[0]) + (a[1] * a[1]); 
    }
}




// LC : https://leetcode.com/problems/interval-list-intersections/
// LC : 986
//Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
//Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

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


                Note: The below isn't possible as input is in sorted order  
                 s            2-----------3   
                     st   1--------------------6

*/


public class IntervalListIntersections {

    // No priority queue needed as : description: 
    //Each list of intervals is pairwise disjoint and in sorted order

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
            
            // ending sooner or shorter interval
            if(A[1] < B[1]) i++;
            else j++;           

        }

        return overlaps.toArray(new int[overlaps.size()][]); // Pay attention

    }   


    /*
     * 
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], 
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * 
     */
    
}


// meeting scheduler 
//https://leetcode.com/problems/meeting-scheduler
// time : O(MlogM+NlogN)
// space :  O(n) 
class MeetingScheduler  {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {


        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int p = 0, q = 0;


        while (p < slots1.length && q < slots2.length) {
            
            int[] A = slots1[p];
            int[] B = slots2[q];

            // find the boundaries of the intersection, or the common slot
            int low = Math.max(A[0], B[0]);
            
            int high = Math.min(A[1], B[1]);
            
            if (high - low >= duration) {
                return new ArrayList<Integer>(Arrays.asList(low, low + duration)); // Pay attention
            }
            // always move the one that ends earlier
            if (A[1] < B[1]) {
                p++;
            } else {
                q++;
            }
        }
        return new ArrayList<Integer>();
    }


    // Opt heap basesed soln
    // Time : O((M+N)log(M+N)),
    // Space : O(M+N)
    public List<Integer> minAvailableDurationPQ(int[][] slots1, int[][] slots2, int duration) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }
        for (int[] slot: slots2) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }

        while (pq.size() > 1) {
            
            int[] slot1 = pq.poll();
            int[] slot2 = pq.peek();
            
            if (slot1[1] >= slot2[0] + duration) {
                return new ArrayList<Integer>(Arrays.asList(slot2[0], slot2[0] + duration));
            }
        }
        return new ArrayList<Integer>();
    }

}





//

/*Logic : cache - fixed size queue and recently used items are in front and least used are are at end of list. So we maintain a Doubly Linked List.
      Operations: get(key)--> value in O(1). So we maintain a hashmap and when the key is accessed, then we re insert the Node(key,value) back into the front of queue.
                  put(key,value) --> we create a Node and insert into front of D. linked list(Deque), then make an entry/modify entry in hashmap. 

 */


 class LRUCache {


    // <cache key, DLlNode>
    Map<Integer,DLLNode> map;

    DLLNode head = null;
    DLLNode tail = null; 
    int capacity;

    public LRUCache(int capacity) {        
        map = new HashMap<>(capacity);   
        this.capacity = capacity;    
    }
            
    public void put(int key, int value) {

        DLLNode newNode = new DLLNode(key,value);

            
        if(map.containsKey(key)){

            DLLNode node = map.get(key);
           
            findAndRemove(node);            
            
            // Update new value
            addFront(newNode);
            map.put(key,newNode);
        }

        else {
           
            // Do a capacity check and remove node from dll and map if needed
            if(map.size() >= capacity){

                DLLNode removed = removeLast();
                map.remove(removed.key); // Pay attention
            }                  

            addFront(newNode);   
            map.put(key, newNode);                     
        }       

    }


    public int get(int key) {

        if(map.containsKey(key)){
                
            DLLNode node = map.get(key);          
            int val = node.value;
            
            // Update
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



    // Used during capacity check 
    // Return node
    public DLLNode removeLast(){

        if(tail == null) return null;

        else if(head == tail){

            DLLNode d = head; // have to return to remove from map during capacity check 

            head = tail = null;
            return d;
        }
        else {
            
            DLLNode d = tail;  // have to return to remove from map during capacity check  

            tail = tail.prev;
            tail.next = null; 
            return d;
        }            
             
    }


    // 5 possible conditions - head is null, head == tail, node itself is head, node itself is tail, node is inbetween
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
        }
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



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



//https://leetcode.com/problems/sliding-window-median/
// Sliding window median
 

// Not fully optimized, but 90% similar to find median in stream. Just look into window size k;
class SlidingWindowMedian {

    PriorityQueue<Double> lower  = new PriorityQueue<>((x,y)-> Double.compare(y,x)); 
    PriorityQueue<Double> upper   = new PriorityQueue<>();;

    public double[] medianSlidingWindow(int[] nums, int k) {

         if(nums == null || nums.length == 0) return new double[] {};

         double[] medians = new double[nums.length - k + 1];
         
         int r = 0;
      
         int right = 0;
         int left = 0;

         while(right < nums.length){

            insertBalancedHeap((double) nums[right]);
             
            if(right - left + 1 == k ){

                medians[r] = findMedian();
                r++;

                removeFromHeap((double) nums[left]);
                left++;
            }

            right++;

         }  

         return medians;     
    }




    

    private void removeFromHeap(double num){

        if(lower.contains(num)) lower.remove(num);

        else upper.remove(num);         

        rebalance();


    }

    // Apply BST logic 
    private void insertBalancedHeap(double num){
        
        if(lower.isEmpty() || num < lower.peek()){

            lower.add(num);
        }

        else upper.add(num);

        // Rebalance based on size

        rebalance();

        

    }

    private void rebalance(){

        if(lower.size() > upper.size() + 1){

            upper.add(lower.remove());    
        }   

        else if(upper.size() > lower.size()){
            lower.add(upper.remove());
        }
    }


    private double findMedian(){

        
        if(lower.size() > upper.size()){
            return lower.peek();
        }

        else return (lower.peek() + upper.peek()) / 2.0;        
    }
}




 //https://leetcode.com/problems/add-strings/
 class AddStrings {
    
    public String addStrings(String num1, String num2) {

    StringBuilder sb = new StringBuilder();

    int remainder = 0;    

    int len1 = num1.length(), len2 = num2.length();

    int len  =  len1 > len2 ? len1 : len2;    

    for(int i = 0; i < len; i++){

        int l1 =  i >= len1  ? 0 : num1.charAt(len1 - 1 - i) - '0';
        int l2 =  i >= len2 ? 0 : num2.charAt(len2 - 1 - i) - '0';

        System.out.println("l1 : " + l1 + " l2 : " + l2);

        int res = l1 + l2 + remainder;

         System.out.println("res : " + res);

        if(res > 9) {
            sb.append(res % 10);
            remainder = res /10;
        }
        else {
             sb.append(res);
             remainder = 0;
        }

        
    }

    if(remainder > 0) sb.append(remainder);
    return sb.reverse().toString();

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
    
                    leftSum += nums[i]; // basically left window expanding
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


// https://leetcode.com/problems/can-place-flowers/?
// Copied from editorial. Remove it later

class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {


        int count = 0;

        for (int i = 0; i < flowerbed.length; i++) {
            
            // Check if the current plot is empty.
            if (flowerbed[i] == 0) {
                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);

                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                
                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;

                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;  


    }
}



// https://leetcode.com/problems/monotonic-array
class IsMonotonic {

    public boolean isMonotonic(int[] A) {
        boolean increasing = true;
        
        boolean decreasing = true;

        for (int i = 0; i < A.length - 1; i++) {

            if (A[i] > A[i+1])
                increasing = false;
            if (A[i] < A[i+1])
                decreasing = false;
        }

        return increasing || decreasing;
    }
}




// square root is always smaller than x/2 and larger than 0 : 0<a<x/2.
// Hence got to find a number that is between 0 and x/2, that when squared will give x.

// for x < 2, sq root of x -> x, so for x >= 2, start with 2-------x/2.
    


class Sqrt {

    public int mySqrt(int x) {
       
        
        /* 1------------root x ------------------x/2 */

        if (x < 2) return x; // handle 0 and 1 directly

        int low = 0; // just outside the correrct start of 1
        int high = x/2 + 1;
               
        while(low + 1 < high){
            
            int mid = low + (high - low) / 2 ;
            
            if(mid <= x / mid){
              
                low = mid;                 
            }
            
            else  high = mid;                
                              
        }
        
        return low;
        
    }
}


//https://leetcode.com/problems/remove-duplicates-from-sorted-array/

class RemoveDuplicatesfromSortedArray {

    public int removeDuplicates(int[] nums) {
       
        /*Logic - keep a pointer i, and pointer j starting at index 1, if nums[i ]== [j]..keep moving the j..if it is diffcopy j value into i */
        
        if(nums.length == 0) return 0;
                
        int i = 0; // stays at the last unique element, lagging pointer
        
        for(int j = 1; j < nums.length ; j++){
            
            if(nums[i] != nums[j]){
                //move i pointer
                i++;                
                nums[i] = nums[j]; // helps later when i and j diverge due to repeat chars
            }
                        
        }    
        
        //else j will skip repeat nums and i will wait
        return i + 1;
   
              
        
    }   
    
}


// https://leetcode.com/problems/set-matrix-zeroes/?
class SetMatrixZeros {






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








    public void setZeroes(int[][] matrix) {
  
        Set<Integer> rows = new HashSet<Integer>();

        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
            
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                
                // contains row or col
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}