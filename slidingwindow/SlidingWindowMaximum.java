package com.app.slidingwindow;

import java.util.*;

class SlidingWindowMaximum {

    // LC 239

/*
    Sliding window - fixed size of k. 
     - Need to keep track of max value in window. 
     - Need evict left most and add righ most element when window slides.
     - Remove elements that are smaller than the one inserted

    Priority queue can keep track of max element, but cant evict elements smaller than current.

    Hence use ArrayDeque and store index values of max element. Add elements from back, remove smaller elements than current from back.
 */

// Similar to monotonic stack
 public int[] maxSlidingWindow(int[] nums, int k) {
        
    List<Integer> res = new ArrayList<>();
                      
    Deque<Integer> q = new ArrayDeque<Integer>();

    for(int right = 0 ; right < nums.length; right++){

        // Remove elements in front of q if it goes out of window size
        if(!q.isEmpty() && q.peekFirst() <= right - k){
            q.pollFirst();
        }     

        
        // Remove elements smaller than current from back of queue - Maintain descending order
        // Note : This is a feature that isnt available in PriorityQueue 
        while(!q.isEmpty() && nums[q.peekLast()] < nums[right]){
            q.pollLast();    
        }

        // Add current element to queue
        q.offer(right);

        // Include max element of current window to res                                  
        if(right >= k - 1){                
            res.add(nums[q.peekFirst()]);                
        }                   
    }     
    
    return res.stream().mapToInt(Integer::intValue).toArray();
    
}




    /* Using priority Queue */
    // //credits : https://www.youtube.com/watch?v=LiSdD3ljCIE


    public int[] maxSlidingWindow2(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1]; // num of possible windows = N - k + 1
        int r = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(k, (x, y) -> y.val - x.val); // max heap

        for (int i = 0; i < nums.length; i++) {

            // remove head element if it is out of window range

            while (!pq.isEmpty() && pq.peek().index <= (i - k)) {

                pq.poll();
            }

            pq.offer(new Node(nums[i], i));

            if (i >= k - 1) {

                result[r++] = pq.peek().val;
            }

        }

        return result;

    }

    class Node {

        int val;
        int index;

        public Node(int val, int index) {

            this.val = val;
            this.index = index;
        }

    }

    
}