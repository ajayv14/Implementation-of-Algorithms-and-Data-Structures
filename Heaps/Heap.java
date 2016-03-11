package DSPractice;

public class Heap {
	// Max heap
	// implemented using an Array
	// array indices start at 1 not 0
	// if the node is k, children are at 2k and 2k+1
	// if k is a child, parent is at k/2

	int size = 20;
	private int[] arr = new int[size];
	int N=0; // no of elements in array
	// Swim - move up

	public static void main(String args[]) {

	
		Heap hp = new Heap();
		hp.insert(12);
		hp.insert(19);
		hp.insert(5);
        hp.insert(16);
            
            
	System.out.println("---"+hp.delete());
	System.out.println("---"+hp.delete());
	
	}

	public void printArray(int[] a){
		
		for(int i = 0;i< a.length; i++){
			System.out.print(a[i]+" ");
		}
		System.out.println("op done");
	}
	
	public boolean insert(int x) {

		arr[++N] = x;
		swim(N);// send index value N
        
		Heap k = new Heap();
        k.printArray(arr);
		return true;
       
	}

	public int delete() {
		// 1) exchange - root and last node value(not key, just values)
		// 2) delete the last node
		// 3) perform swim on first node;

		int max = arr[1]; // to return the deleted value later
		exchange(1,N);
        --N;// decrease array size
        arr[N+1]= 0;// empty the deleted array index 
        sink(1);
        
        Heap p = new Heap();
        p.printArray(arr);
        return max;
	}

	public void swim(int k) {
		// k --. index(key), k>1 coz root need no swim operation
		while (k > 1 && less(k/2, k)) { // check if parent is less that child											// [k/2]
			exchange(k, k / 2);// exchange arr[k]and arr[k/2]
			k = k/2;// set pointer to parent
		}
	}

	// while deleting
	public void sink(int k) {

		while (2 * k <= N) { // to check if children are well within the range
			int m = 2 * k;

			// 1) m< N, to check end of heap.
			// 2) m < m+1 ,the m = m+1,m--> larger among the children
			if (m < N && less(m, m + 1)) {
				m++;
			}
			if (!less(k, m)) {
				break; // element is in proper order
			}
			exchange(k, m);

			k = m;
		}

	}

	public boolean isEmpty() {

		return N == 0;
	}

	private boolean less(int i, int j) {

		if (arr[i] < arr[j])
			return true;
		else
			return false;

	}

	private void exchange(int x, int y) {

		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;

	}

}
