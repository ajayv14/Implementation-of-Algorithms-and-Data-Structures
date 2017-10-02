public class SelectionSort {

/*https://www.youtube.com/watch?v=cqh8nQwuKNE*/

    int[] arr = {2, 5, 1, 4, 7, 1, 10};
    int N = arr.length;

    public static void main(String[] args) {

	         /*selection sort algo

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

        SelectionSort h = new SelectionSort();
        h.selectionSort();
        h.printArray();


    }


    public void selectionSort() {

        int min = 0;
        for (int i = 0; i < N; i++) {
            min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(i, min);
        }
    }

    public void swap(int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public void printArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
