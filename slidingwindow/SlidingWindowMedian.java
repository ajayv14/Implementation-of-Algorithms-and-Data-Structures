public class SlidingWindowMedian {


    // LC 480 https://leetcode.com/problems/sliding-window-median

    /*
        Approach - 

        Build on median from data stream LC 295

        1. Use two heaps - lower and upper. 
        2. Lower heap will have all the lower numbers in sorted order. 
        3. Upper heap will have all the higher numbers in sorted order. 
        4. If the size of lower and upper heap is equal, then the median is the average of the top of both heaps. 
        5. If the size of lower heap is greater than upper heap, then the median is the top of lower heap. 
        6. If the size of upper heap is greater than lower heap, then the median is the top of upper heap.

        Also keep moving the window of size k, remove the element from heap that is outside window.
     
        */


     // Non - Optimized solution
     // Missing optimization - lazy delete from heap   

    PriorityQueue<Double> lower; // max heap
    PriorityQueue<Double> upper; // min heap

    public double[] medianSlidingWindow(int[] nums, int k) {

         if(nums == null || nums.length == 0) return new double[] {};

         double[] medians = new double[nums.length - k + 1];
         int r = 0;

         upper = new PriorityQueue<>();
         lower = new PriorityQueue<>((x,y)-> Double.compare(y,x));   

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


// Lazy delete optimized version 

class OptimizedSlidingWindowMedian {

    PriorityQueue<Double> lower;
    PriorityQueue<Double> upper;

    Map<Double, Integer> lazyDeleteMap = new HashMap<>();

    int lowerCount =0, upperCount = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
       
        if (nums == null || nums.length == 0) return new double[] {};

        double[] medians = new double[nums.length - k + 1];
        int r = 0;

        upper = new PriorityQueue<>();
        lower = new PriorityQueue<>((x, y) -> Double.compare(y, x));

        int right = 0, left = 0;

        while (right < nums.length) {
            addToHeap((double) nums[right]);

            if (right - left + 1 == k) {
                medians[r++] = findMedian();
                removeFromHeap((double) nums[left]);
                left++;
            }

            right++;
        }

        return medians;
    }

    private void removeFromHeap(double num) {
        // Marked to be deleted
        lazyDeleteMap.put(num, lazyDeleteMap.getOrDefault(num, 0) + 1);
        
        if(!lower.isEmpty() && num <= lower.peek()){
            lowerCount--;
        }

        else upperCount--;

        rebalance();
    }

    private void addToHeap(double num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.add(num);
            lowerCount++;

        } else {
            upper.add(num);
            upperCount++;
        }

        rebalance();
    }

    private void rebalance() {
       

        while (lowerCount > upperCount + 1) {
            upper.add(lower.poll());
            prune(lower);
            lowerCount--;
            upperCount++;
        }

        while (upperCount > lowerCount) {
            lower.add(upper.poll());
            prune(upper);
            upperCount--;
            lowerCount++;    
        }

        prune(lower);
        prune(upper);
    }

    private void prune(PriorityQueue<Double> pq) {
        while (!pq.isEmpty()) {
            double top = pq.peek();
            if (lazyDeleteMap.containsKey(top)) {
                lazyDeleteMap.put(top, lazyDeleteMap.get(top) - 1);
                if (lazyDeleteMap.get(top) == 0) {
                    lazyDeleteMap.remove(top);
                }
                pq.poll(); // Remove the element
            } else {
                break;
            }
        }
    }

    private double findMedian() {
        prune(lower);
        prune(upper);

        if (lowerCount > upperCount) {
            return lower.peek();
        } else {
            return (lower.peek() + upper.peek()) / 2.0;
        }
    }
}
