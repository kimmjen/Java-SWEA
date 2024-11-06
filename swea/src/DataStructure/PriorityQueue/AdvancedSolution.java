package DataStructure.PriorityQueue;

import java.util.Scanner;

public class AdvancedSolution {

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

            // 시간 및 메모리 측정 시작
            long startTime = System.currentTimeMillis();
            long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            heapInit();

            for (int i = 0; i < N; i++) {
                int value = sc.nextInt();
                heapPush(value);
            }

            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                System.out.print(heapPop() + " ");
            }
            System.out.println();

            // 메모리 측정 종료
            long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long usedMemory = afterMemory - beforeMemory;

            // 시간 측정 종료
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // 결과 출력
            System.out.println("실행 시간: " + executionTime + "ms / 시간 제한: 1000ms");
            System.out.println("사용된 메모리: " + usedMemory / 1024 + "KB / 메모리 제한: 256MB");
            System.out.println();
        }
        sc.close();
    }
}