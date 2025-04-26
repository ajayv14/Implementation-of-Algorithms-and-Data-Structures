



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
class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();

        int result=0;
        int level = 1;

        for(NestedInteger i : nestedList){
           q.add(i);
        }

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
              NestedInteger item = q.poll();
              
              if(item.isInteger()){
                result+=(item.getInteger()*level);
              }   

              else{
                for(NestedInteger nei : item.getList()){
                    q.add(nei);
                }
              }

            }
            level++;
        }

        return result;
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






class Pow_x_n. {
   
    
    public double myPow(double x, int n) {
        
          
       if(n == 0) return 1.0;
        
       if(n == 1) return x;
        
        if(n < 0){
            
            x = 1 / x;
            n = -n;          
        }
        
        return fastPower(x, n);    
    
    }
    
    
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














