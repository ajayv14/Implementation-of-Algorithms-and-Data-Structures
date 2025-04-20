
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

import java.util.PriorityQueue;

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