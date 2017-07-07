// pivot , Pindex

public class quickSort {
    static int[] arr = {3, 5, 2, 1, 9, 7, 0};

    public static void main(String[] args) {

        quickSort q = new quickSort();
        q.qSort(arr, 0, arr.length - 1);
        q.printArray(arr);
       //q.swap(0,5);
      //q.printArray(arr);
    }

    public void qSort(int[] arr, int startIndex, int endIndex) {

        if (startIndex <= endIndex) {

            int partitionIndex = partition(startIndex, endIndex);
            qSort(arr, startIndex, partitionIndex - 1);
            qSort(arr, partitionIndex + 1, endIndex);

        }

    }

    // [1, 4, 5, 6,7 ,8 ,9,3,4]
    public int partition(int startIndex, int endIndex) {

        int pivot = arr[endIndex];
        int pIndex = startIndex;
        for (int i = startIndex; i <= endIndex - 1; i++) {

            if (arr[i] <= pivot) {
                swap(i, pIndex);
                pIndex++;
            }
        }
        swap(pIndex, endIndex);
        return pIndex;
    }


    public void swap(int pIndex, int endIndex) {
        int tmp;
        tmp = arr[pIndex];
        arr[pIndex] = arr[endIndex];
        arr[endIndex] = tmp;
    }


    public void printArray(int[] array) {
        for (int val : array) {
            System.out.println(val);
        }
    }
}
