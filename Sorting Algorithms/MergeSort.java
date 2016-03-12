package SortingAlgorithms;

public class MergeSort {

	public static void main(String args[]) {
		int[] arr = { 0, 1, 1, 1, 4, 3, 2, 6, 7, 5, 9 };
		MergeSort m = new MergeSort();
		arr = m.mergeSort(arr);
		m.printArray(arr);

	}

	private void printArray(int[] a) {

		for (int d = 0; d < a.length; d++) {
			System.out.println(a[d]);
		}
	}

	public int[] mergeSort(int[] arr) {

		int N = arr.length;
		if (N < 2)
			return arr;

		int mid = N / 2;
		int[] left = new int[mid];
		int[] right = new int[N - mid];
		// create left and right array to hold the split array values.
		for (int i = 0; i <= mid - 1; i++) {
			left[i] = arr[i];
		}
		for (int j = mid; j <= N - 1; j++) {
			right[j - mid] = arr[j];
		}

		mergeSort(left);
		mergeSort(right);
		merge(left, right, arr);

		return arr;
	}

	private int[] merge(int[] l, int[] r, int[] arr) {

		int Nl = l.length;
		int Nr = r.length;
		int i = 0;
		int j = 0;
		int k = 0;

		// compre i and j pointers each time
		while (i < Nl && j < Nr) {

			if (l[i] <= r[j]) {
				arr[k] = l[i];
				i++;

			} else {
				arr[k] = r[j];
				j++;
			}
			k++;
		}
		// to Account for elements which may have left in the array without
		// being compared
		while (i < Nl) {
			arr[k] = l[i];
			i++;
			k++;
		}
		while (j < Nr) {
			arr[k] = r[j];
			j++;
			k++;
		}
		return arr;
	}

}
