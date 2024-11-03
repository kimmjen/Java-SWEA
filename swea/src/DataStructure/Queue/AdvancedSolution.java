package DataStructure.Queue;

import java.util.Scanner;

public class AdvancedSolution {

    static final int MAX_N = 100;

    static int front;
    static int rear;
    static int queue[] = new int[MAX_N];

    static void queueInit() {
        front = 0;
        rear = 0;
    }

    static boolean queueIsEmpty() {
        return (front == rear);
    }

    static boolean queueIsFull() {
        return ((rear + 1) % MAX_N == front);
    }

    static boolean queueEnqueue(int value) {
        if (queueIsFull()) {
            System.out.println("Queue is full");
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % MAX_N;
        return true;
    }

    static Integer queueDequeue() {
        if (queueIsEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        int value = queue[front];
        front = (front + 1) % MAX_N;
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            // 시간 및 메모리 측정 시작
            long startTime = System.currentTimeMillis();
            long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            queueInit();

            for (int i = 0; i < N; i++) {
                int value = sc.nextInt();
                queueEnqueue(value);
            }

            System.out.print("#" + test_case + " ");

            while (!queueIsEmpty()) {
                Integer value = queueDequeue();
                if (value != null) {
                    System.out.print(value + " ");
                }
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
