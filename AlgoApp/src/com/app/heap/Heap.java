import java.util.*;

// credits https://www.youtube.com/watch?v=LhhRbRXhB40 Sesh Venugopal
// max heap
class Heap{
   
      private ArrayList<Integer> list;
      
      public Heap(){
         list = new ArrayList<Integer>();
      }
      
      @Override
      public String toString(){
         return list.toString();
      }
   

      private void insert(int val){         
         list.add(val);
         siftUp();
      }


      private void siftUp(){
         
         int k = list.size() - 1; // the last element in the tree/ heap        
         
         while(k > 0){   // k is the current node index, p --> parent node index
            int p = (k - 1)/2;
            
            int key = list.get(k);
            int parent = list.get(p);
            
            if(key > parent){ //swap
               swap(k , p, list);
               k = p; // move up one more level to check if key is still < next parent               
            }
            
            else break;       
         }
           
      }
      
      

      private int delete(){
                        
         if(list.size() == 0) return -1;
         
         if(list.size() == 1) {
            return list.remove(0);            
         }
                           
         int deleted = list.get(0);  // keep the root node in a temp variable to return
                 
         //swap root node and the last node in level order traversal (last element in list)
         
         list.set(0, list.remove(list.size() - 1));
         
                        
         siftDown();
         
         return deleted;         
      }     


      
      
      private void siftDown(){
         /*root now contains node k, if value of k is less than greater of its two children, 
           swap it with that child */
           
           int k = 0; // initially its the root, so index == 0
           
           int leftChild = 2*k+1;
           
           while(leftChild < list.size()){  // as long as left child is within boundary
               
               int maxIndex = leftChild;
               int rightChild = 2*k + 2;
                              
               //check if there is a right child
               // check if right child > left child, if so set its index on max
               if( rightChild < list.size()) {
                                 
                  if( list.get(rightChild) > list.get(leftChild)) maxIndex++;
                                    
               }
               
               // check the node k value with the max of right and left child computed above
               
               if(list.get(k) < list.get(maxIndex)){
                  
                  swap(k, maxIndex, list);
                  
                  k = maxIndex; // now move the k to reflect the swapped node 
                  leftChild = 2*k + 1; // recompute left child
                  
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
