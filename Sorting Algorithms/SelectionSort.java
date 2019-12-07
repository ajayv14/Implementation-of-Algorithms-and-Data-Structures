public class SelectionSort {

/*https://www.youtube.com/watch?v=cqh8nQwuKNE*/
/* algo
       find min value in each iteration and swap. use ptr j to find min and swap with array value in i                
	         1-2-5-6-7-2
	         n--length of arry
            
	         min, i
	                 for i 0---> n-1
	          min = i;
	        
	         for j  i+1 ----->n-1 
	         
            if arr[j]<min
	             min = j;                 
	              swap(i,j);	         
	         */    
    
    public int[] selectionSort(int[] arr) {
    
        int N = arr.length;
        int min = 0;
        
        for (int i = 0; i < N; i++) {
        
            min = i;
            
            for (int j = i + 1; j < N; j++) {
            
                if (arr[j] < arr[min]) {
                
                    min = j;
                }
            }
            
            swap(i, min, arr);
        }
        return arr;
    }

    public int[] swap(int i, int j, int[] arr) {
        
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        
        return arr;
    }

    public static void main(String[] args) {

	     int[] arr = {2, 5, 1, 4, 7, 1, 10};
        
        SelectionSort h = new SelectionSort();
        
        int[] res = h.selectionSort(arr);
               
        for (int i = 0; i < res.length; i++) {
            System.out.println(arr[i]);
        }
    }

}