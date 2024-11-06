package DataStructure.PriorityQueue;

import java.util.Scanner;

public class Solution {

    static final int MAX_SIZE = 100;

    static int[] heap = new int[MAX_SIZE];
    static int heapSize = 0;

    static void heapInit() {
        heapSize = 0;
    }

    static void heapPush(int value) {
        if (heapSize >= MAX_SIZE) {
            return;
        }
        heap[heapSize] = value;

        int current = heapSize;
        while (current > 0 && heap[current] < heap[(current - 1) / 2]) {
            int parent = (current - 1) / 2;
            swap(heap, current, parent);
            current = parent;
        }
        heapSize++;
    }

    static int heapPop() {
        if (heapSize <= 0) {
            return -1;
        }
        int value = heap[0];
        heapSize--;

        heap[0] = heap[heapSize];

        int current = 0;
        while (current < heapSize) {
            int leftChild = current * 2 + 1;
            int rightChild = current * 2 + 2;
            int smallest = current;

            if (leftChild < heapSize && heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }
            if (rightChild < heapSize && heap[rightChild] < heap[smallest]) {
                smallest = rightChild;
            }

            if (smallest == current) break;

            swap(heap, current, smallest);
            current = smallest;
        }
        return value;
    }

    static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    static void heapPrint(int[] heap, int heapSize) {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            heapInit();

            for (int i = 0; i < N; i++) {
                int value = sc.nextInt();
                heapPush(value);
            }
            System.out.println("힙 상태 출력:");
            heapPrint(heap, heapSize);  // 힙 상태 출력


            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                System.out.print(heapPop() + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}