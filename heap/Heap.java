import java.util.*;

/**
 * Implementation of a Max Heap data structure.
 * In a max heap, the value of each node is greater than or equal to the values
 * of its children.
 * 
 * Credits: Based on implementation by Sesh Venugopal
 * (https://www.youtube.com/watch?v=LhhRbRXhB40)
 */
class Heap {

   private ArrayList<Integer> list;

   /**
    * Constructs an empty max heap.
    */
   public Heap() {
      list = new ArrayList<Integer>();
   }

   /**
    * Inserts a value into the heap.
    * 
    * @param val The value to insert
    */
   public void insert(int val) {
      list.add(val);
      siftUp();
   }

   /**
    * Restores the heap property by moving the last inserted element up.
    */
   private void siftUp() {

      int currentIdx = list.size() - 1; // the last element in the tree/heap

      while (currentIdx > 0) {

         int parentIdx = (currentIdx - 1) / 2;

         int currentVal = list.get(currentIdx);
         int parentVal = list.get(parentIdx);

         if (currentVal > parentVal) { // swap if current value is greater than parent
            swap(currentIdx, parentIdx, list);
            currentIdx = parentIdx; // move up one more level
         } else {
            break;
         }
      }
   }

   /**
    * Removes and returns the maximum element (root) from the heap.
    * 
    * @return The maximum element, or -1 if the heap is empty
    */
   public int delete() {
      if (isEmpty())
         return -1;

      if (list.size() == 1) {
         return list.remove(0);
      }

      int maxValue = list.get(0); // keep the root node in a temp variable to return

      // Replace root with the last node and then restore heap property
      list.set(0, list.remove(list.size() - 1));
      siftDown();

      return maxValue;
   }

   /**
    * Restores the heap property by moving the root element down to its correct
    * position.
    */
   private void siftDown() {

      int currentIdx = 0; // initially it's the root
      int leftChildIdx = 2 * currentIdx + 1;

      while (leftChildIdx < list.size()) { // as long as left child is within boundary
         
         int maxChildIdx = leftChildIdx;
         
         int rightChildIdx = 2 * currentIdx + 2;

         // Check if right child exists and is greater than left child
         if (rightChildIdx < list.size() && list.get(rightChildIdx) > list.get(leftChildIdx)) {
            maxChildIdx = rightChildIdx;
         }

         // If current node is less than the max child, swap them
         if (list.get(currentIdx) < list.get(maxChildIdx)) {
            swap(currentIdx, maxChildIdx, list);
            
            currentIdx = maxChildIdx; // move down to the swapped position
            leftChildIdx = 2 * currentIdx + 1; // recompute left child index
         } else {
            break;
         }
      }
   }

   /**
   * Swaps two elements in the list.
   */
   private void swap(int i, int j, List<Integer> list) {
      int temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
   }

   
   public boolean isEmpty() {
      return list.size() == 0;
   }
   
   public int size() {
      return list.size();
   }

   
   public int peek() {
      if (isEmpty())
         return -1;
      return list.get(0);
   }

   @Override
   public String toString() {
      return list.toString();
   }

   public static void main(String[] args) {
      Heap maxHeap = new Heap();
      maxHeap.insert(20);
      maxHeap.insert(30);
      maxHeap.insert(10);
      maxHeap.insert(15);
      maxHeap.insert(5);

      System.out.println("Heap after insertions: " + maxHeap);
      System.out.println("Deleted max element: " + maxHeap.delete());
      System.out.println("Heap after deletion: " + maxHeap);
      System.out.println("Deleted max element: " + maxHeap.delete());
      System.out.println("Heap after deletion: " + maxHeap);
      System.out.println("Current heap size: " + maxHeap.size());
      System.out.println("Is heap empty? " + maxHeap.isEmpty());
   }
}
