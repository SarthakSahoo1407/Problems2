package Unknown;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapPrblm {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int[] arr = { 1, 2, 10, 8, 7, 3, 4, 6, 5, 9 };

        for (int i : arr) {
            pq.add(i);
        }
        System.out.println("Printing Priority Queue Heap : " + pq);
        //kthsmallest
        int k = 5;
        System.out.println("Kth smallest element in Priority Queue Heap : " + kthSmallest2(arr, arr.length, k));

        //kthlargest
        System.out.println("Kth largest element in Priority Queue Heap : " + kthLargest(arr, arr.length, k));

        //isminheap
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        System.out.println("Is Min Heap : " + isMinHeap(arr1, arr1.length));
        int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        System.out.println("Is Max Heap : " + isMaxHeap(arr2, arr2.length));
        //product of k smallest
        int[] arr3 = { 1, 2, 3, 4, 5, 6 };
        System.out.println("Product of K smallest numbers : " + KSmallestProduct(arr3, arr3.length, 3));


        System.out.print("Dequeue elements of Priority Queue ::");
        while (pq.isEmpty() == false) {
            System.out.print(" " + pq.remove());
        }
        System.out.println();
    }
    public static int kthSmallest2(int[] arr, int size, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < size; i++)
            pq.add(arr[i]);
        for (int i = 0; i < k - 1; i++)
            pq.remove();
        return pq.peek();
    }
    public static int kthLargest(int[] arr, int size, int k) {
        int value = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < size; i++)
            pq.add(arr[i]);
        for (int i = 0; i < k; i++)
            value = pq.remove();
        return value;
    }

    public static boolean isMinHeap(int[] arr, int size) {
        int lchild, rchild;
        // last element index size - 1
        for (int parent = 0; parent < (size / 2 + 1); parent++) {
            lchild = parent * 2 + 1;
            rchild = parent * 2 + 2;
            // heap property check.
            if (((lchild < size) && (arr[parent] > arr[lchild])) || ((rchild < size) && (arr[parent] > arr[rchild])))
                return false;
        }
        return true;
    }

    public static boolean isMaxHeap(int[] arr, int size) {
        int lchild, rchild;
        // last element index size - 1
        for (int parent = 0; parent < (size / 2 + 1); parent++) {
            lchild = parent * 2 + 1;
            rchild = lchild + 1;
            // heap property check.
            if (((lchild < size) && (arr[parent] < arr[lchild])) || ((rchild < size) && (arr[parent] < arr[rchild])))
                return false;
        }
        return true;
    }
    public static int KSmallestProduct(int[] arr, int size, int k) {
        Arrays.sort(arr);// , size, 1);
        int product = 1;
        for (int i = 0; i < k; i++)
            product *= arr[i];
        return product;
    }
}
