public class BubbleSort {

    int[] arr = {2, 5, 1, 4, 1, 7, 10};
    int N = arr.length;

    public static void main(String[] args) {

	  /*Bubble sort
	   	-push the largest element to the end of array-
	    take element i, check if i+1 is less that, if so then swap
	    repeat this iteration for N number of times.
	  */

        BubbleSort h = new BubbleSort();
        h.bubbleSort();
        h.printArray();


    }

    public void bubbleSort() {
        int flag = 0;
        for (int i = 0; i < N; i++) { // outer loop

            for (int j = 0; j < N - i - 1; j++) {
                // stops 2 indices before the end of array
                // because i+1 will exceed end of array
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    //optimization- if the iteration passes without swaps,
                    //meaning array is already sorted, so check if flag is 0
                    // set flag to 1 , if there is a swap
                    flag = 1;
                }


            }

            if (flag == 0) break; // array is sorted
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
