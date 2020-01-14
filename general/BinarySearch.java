class BinarySearch {


/*recursive*/
public int search(int[] arr, int key, int start, int end){
  
   
     
    if(start <= end ){  // check if index is within range
    
    int midIndex = start + (end - start)/2; // to prevent overflow
    
    
    // search on left half
    if(key < arr[midIndex]){
         return search(arr, key, 0, midIndex - 1);
    }
   
    // search in right half
    else if(key > arr[midIndex]){
         return search(arr, key, midIndex + 1, end);
    }
   
    else if (key == arr[midIndex]) return midIndex;
  }
   
  return -1;   
 } 
 
 
 

/*iterative*/ 
    
 public int search(int[] arr, int key){
  
  int start = 0, end = arr.length - 1;      
     
  while(start <= end ){  
    
    int midIndex = start + (end - start)/2; // to prevent overflow
    
    
    // search on left half
    if(key < arr[midIndex]){
         end = midIndex - 1;
    }
   
    // search in right half
    else if(key > arr[midIndex]){
         start = midIndex + 1;
    }
   
    else if (key == arr[midIndex]) return midIndex;
          
  } 
  
  return -1;   
 } 


  public static void main(String args[]){
         
      BinarySearch obj = new BinarySearch();
         
         int[] arr = {3,4,6,8,10,15};
              
         int recursiveRes = obj.search(arr, 157, 0, arr.length - 1);   
         int iterativeRes = obj.search(arr,400);   
         
         System.out.println(recursiveRes); 
         System.out.println(iterativeRes);  
  } 
}