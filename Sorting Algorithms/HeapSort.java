// credits: https://www.youtube.com/watch?v=5iBUTMWGtIQ
// https://www.youtube.com/watch?v=WsNQuCa_-PU

class HeapSort {

     public void heapSort(int[] arr){
     
       int mid = arr.length/2 ;
       
       for(int i = mid; i >= 0; i--){
            heapify(arr, i);
       }        
     
     }
     
     private void heapify(int[] arr, int i){
         
         int largest = i;
         int left = 2 * i + 1;
         int right = 2 * i + 2;
         
         if(left < arr.length && arr[left] > arr[largest]){
            largest = left;
         }
         
         if(right < arr.length && arr[right] > arr[largest]){
            largest = right;
         }
         
         if(largest != i){
            // perform swap arr[i] and arr[largest]
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            //recursively fix the tree using heapify()
            heapify(arr, largest);
         }
         
     }
     
     
     public static void main(String[] args){
         
         int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
         //int[] arr = {1, 2, 3, 4, 5, 5, 7};    
         
         HeapSort obj = new HeapSort();
            obj.heapSort(arr);
            
         for(int num : arr ){
            System.out.print(num + "-->");
         }   
             
     }

}