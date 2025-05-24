import java.util.*;

// credits https://www.youtube.com/watch?v=LhhRbRXhB40 Sesh Venugopal
// max heap
class Heap{
   
      private ArrayList<Integer> list;
      
      public Heap(){
         list = new ArrayList<Integer>();
      }
      
   

      private void insert(int val){         
         list.add(val); // Add to end of list - leaf node
         siftUp();
      }


      private void siftUp(){
         
         int curIdx = list.size() - 1; // the last element in the tree/ heap        
         
         while(curIdx > 0){   // k is the current node index, p --> parent node index
            
            int parentIdx = (curIdx - 1)/2;
            
            int curVal = list.get(curIdx);
            int parent = list.get(parentIdx);
            
            if(curVal > parent){ //swap
               swap(curIdx , parentIdx, list);
               curIdx = parentIdx; // move up one more level to check if key is still < next parent               
            }
            
            else break;       
         }
           
      }
      
      

      private int delete(){
                        
         if(list.size() == 0) return -1;
         
         if(list.size() == 1) {
            return list.remove(0);            
         }
                           
         int deletedNodeVal = list.get(0);  // keep the root node in a temp variable to return
                 
         //swap root node and the last node in level order traversal (last element in list)
         int lastNode = list.remove(list.size() - 1);   

         list.set(0, lastNode);         
                        
         siftDown();
         
         return deletedNodeVal;         
      }     


      
      
      private void siftDown(){
        
         /*root now contains node cur. If value of cur is less than greater of its two children, 
           swap it with that child */
           
           int curIdx = 0; // initially its the root, so index == 0
           
           int leftChild = 2 * curIdx + 1;
           
           while(leftChild < list.size()){  // as long as left child is within boundary
               
               int maxIndex = leftChild;

               int rightChild = 2 * curIdx + 2;
                              
               //check if there is a right child
               // check if right child > left child, if so set its index on max
               if( rightChild < list.size()) {
                                 
                  if( list.get(rightChild) > list.get(leftChild)) maxIndex++;
                                    
               }
               
               // check the node k value with the max of right and left child computed above
               
               if(list.get(curIdx) < list.get(maxIndex)){
                  
                  swap(curIdx, maxIndex, list);
                  
                  curIdx = maxIndex; // now move the k to reflect the swapped node 
                  leftChild = 2 * curIdx + 1; // recompute left child
                  
               }
                              
               else break;
           }                  
                                 
      }
      
      
      
      
      /* utility function to swap list values based on index */
      private void swap(int k, int p, List<Integer> list){
           int temp = list.get(k); 
           list.set(k, list.get(p));
           list.set(p, temp);   
      }    
      

      @Override
      public String toString(){
         return list.toString();
      }
      
      
      public static void main(String[] args){
         
         Heap maxHeap = new Heap();
            maxHeap.insert(20);
            maxHeap.insert(30);
            maxHeap.insert(10);
            maxHeap.insert(15);
            maxHeap.insert(5);
            
            System.out.println(maxHeap);
            System.out.println(maxHeap.delete());

            System.out.println(maxHeap);
            System.out.println(maxHeap.delete());
        
            System.out.println(maxHeap);
         
      }      
   
}
