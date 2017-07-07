package SortingAlgorithms;

public class lInsertionSort {

    int[] arr = {2, 5, 1, 4, 7, 1, 10};

    public static void main(String[] args) {
        //Insertion sort
    /*algo 
       1-2-3-4-5-
       i,j
       n--> length of arr
       for i 0---> n-1 
         for j i---> >0
       swap if arr [j} < arr[j-1]
       
   */
        InsertionSort h = new InsertionSort();

        h.insertionSort();
        h.printArray();

    }

    public void insertionSort() {
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j > 0; j--) {

                if (arr[j] < arr[j - 1]) {

                    swap(j, j - 1);
                }

            }

        }

    }

    public void swap(int j, int i) {

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
