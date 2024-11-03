package DataStructure.Queue;

import java.util.Scanner;

public class Solution {

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
        if ((rear + 1) % MAX_N == front) {
            return true;
        } else {
            return false;
        }
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



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            queueInit();

            for (int i = 0; i < N; i++) {
                int value = sc.nextInt();
                queueEnqueue(value);
            }
            System.out.println("#" + test_case + " ");

            while (!queueIsEmpty()) {
                Integer value = queueDequeue();

                if (value != null) {
                    System.out.print(value + " ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
