// credits: https://www.youtube.com/watch?v=5iBUTMWGtIQ
// https://www.youtube.com/watch?v=WsNQuCa_-PU

class HeapSort {
      
     /*logic : 1) Use heapify to build a max-heap--> max heap does not give sorted output, just satisfies property-- root(parent) > left & right child.

               2) To overcome this, we swap last element and first element in array (largest element at index 0 goes to end). 
               Now we need to exclude this(last element) from the heap, hence we use the end variable to limit the length of array.
               Finally we heapify again from 0 -> end
               (excluding the last element each run, iteratively)

               exceptional cases to try: 
               [5,1,1,2,0,0]
      */    
     
      

     public void heapSort(int[] arr){
     
       int mid = arr.length/2 ;
       
       /* build max-heap step- does not gurantee sorted order*/
       for(int i = mid; i >= 0; i--){
            heapify(arr,i, arr.length);
       }
       
       /*sorting in ascending order*/
       for(int i = nums.length - 1; i >=0; i--){
            /*swap largest element (at index 0) with last index*/
            swap(nums,0,i);
            heapify(nums,0,i); // note- here we consider length of array to be i, as we dont wanna run the algo for entire length of array
        }    
       
               
     
     }
     
     private void heapify(int[] arr, int i, int end){  // end is to specify length of array manually
         
         int largest = i;
         int left = 2 * i + 1;
         int right = 2 * i + 2;
         
         if(left < end && arr[left] > arr[largest]){ // check bounds and compare values
            largest = left;
         }
         
         if(right < end && arr[right] > arr[largest]){
            largest = right;
         }
         
         if(largest != i){
            // perform swap arr[i] and arr[largest]
             swap(arr,largest,i);
            //recursively fix the tree using heapify()
            heapify(arr,largest,end);
         }
         
     }
     
     
      private void swap(int[] arr, int i, int j){
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;        
    }
     
     
     public static void main(String[] args){
         
         //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
         //int[] arr = {1, 2, 3, 4, 5, 5, 7};    
         int[] arr = {5,1,1,2,0,0};
         HeapSort obj = new HeapSort();
            obj.heapSort(arr);
            
         for(int num : arr ){
            System.out.print(num + "-->");
         }   
             
     }

}
