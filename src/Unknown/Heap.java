package Unknown;

public class Heap {
    private static final int CAPACITY = 32;
    private int size; // Number of elements in Heap
    private int[] arr; // The Heap array
    boolean isMinHeap;

    public Heap(boolean isMin) {
        arr = new int[CAPACITY];
        size = 0;
        isMinHeap = isMin;
    }

    public Heap(int[] array, boolean isMin) {
        size = array.length;
        arr = array;
        isMinHeap = isMin;
        // Build Heap operation over array
        for (int i = (size / 2); i >= 0; i--) {
            percolateDown(i);
        }
    }

    boolean compare(int[] arr, int first, int second) {
        if (isMinHeap)
            return (arr[first] - arr[second]) > 0; // Min heap compare
        else
            return (arr[first] - arr[second]) < 0; // Max heap compare
    }
    // Other Methods.

    private void percolateDown(int parent) {
        int lChild = 2 * parent + 1;
        int rChild = lChild + 1;
        int child = -1;

        if (lChild < size) {
            child = lChild;
        }
        if (rChild < size && compare(arr, lChild, rChild)) {
            child = rChild;
        }
        if (child != -1 && compare(arr, parent, child)) {
            int temp = arr[parent];
            arr[parent] = arr[child];
            arr[child] = temp;
            percolateDown(child);
        }
    }

    private void percolateUp(int child) {
        int parent = (child - 1) / 2;
        if (parent >= 0 && compare(arr, parent, child)) {
            int temp = arr[child];
            arr[child] = arr[parent];
            arr[parent] = temp;
            percolateUp(parent);
        }
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    public int length() {
        return size;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return arr[0];
    }

    public void add(int value) {
        if (size == arr.length) {
            doubleSize();
        }
        arr[size++] = value;
        percolateUp(size - 1);
    }

    private void doubleSize() {
        int[] old = arr;
        arr = new int[arr.length * 2];
        System.arraycopy(old, 0, arr, 0, size);
    }


    public static void main(String[] args) {

    }
}
