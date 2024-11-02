package DataStructure.Stack;

import java.util.Scanner;

public class AdvanceSolution {
    static final int MAX_N = 100;

    static int top;
    static int stack[] = new int[MAX_N];

    static void stackInit() {
        top = 0;
    }

    static boolean stackIsFull() {
        return (top == MAX_N);
    }

    static boolean stackIsEmpty() {
        return (top == 0);
    }

    static boolean stackPush(int value) {
        if (stackIsFull()) {
            System.out.println("Stack overflow!!");
            return false;
        }
        stack[top++] = value;
        top++;
        return true;
    }
    static Integer stackPop() {
        if (stackIsEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        top--;
        return stack[top];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            // 시간 및 메모리 측정 시작
            long startTime = System.currentTimeMillis();
            long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            stackInit();

            for (int i = 0; i < N; i++) {
                int value = sc.nextInt();
                stackPush(value);
            }
            System.out.print("#" + test_case + " ");

            while (!stackIsEmpty()) {
                Integer value = stackPop();
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
