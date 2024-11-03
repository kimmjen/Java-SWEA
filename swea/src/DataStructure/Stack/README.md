## Stack

### 1. 기본 스택 초기화 및 구성 요소
```java
static final int MAX_N = 100;
static int top;
static int stack[] = new int[MAX_N];
```

- **MAX_N** : 스택의 최대 크기를 정의하는 상수
- **top** : 스택의 가장 위(top) 위치를 나타내는 인덱스, 데이터가 들어오거나 나갈 때 이값이 조정
- **stack[]** : 데이터를 저장할 정수형 배열

### 2. 스택 초기화
```java
static void stackInit() {
    top = 0;
}
```

- **stackInit()** : 스택을 초기화하는 메서드로, top을 0으로 설정하여 스택을 비운다

### 3. 스택이 가득 찼는지 확인
```java
static boolean stackIsFull() {
    return (top == MAX_N);
}
```

- **stackIsFull()** : 스택이 가득 찼는지 확인, top이 MAX_N과 같다면 스택이 가득 찬 상태이므로 true를 반환

### 4. 스택이 비었는지 확인
```java
static boolean stackIsEmpty() {
    return (top == 0);
}
```

- **stackIsEmpty()** : 스택이 비어 있는지 확인, top이 0이면 스택에 요소가 없는 상태이므로 true를 반환

### 5. 스택에 데이터 추가 (Push)
```java
static boolean stackPush(int value) {
    if (stackIsFull()) {
        System.out.println("Stack overflow!!");
        return false;
    }
    stack[top] = value;
    top++;
    return true;
}
```

- **stackPush()**: 스택의 top 위치에 새로운 데이터를 추가하는 메서드
  - 스택이 가득 찼다면 “Stack overflow!!” 메시지를 출력하고 false를 반환하여 데이터를 더 이상 추가할 수 없음을 나타냄.
  - 가득 차지 않았다면, top 위치에 값을 저장하고 top을 1 증가시킨다.


### 6. 스택에서 데이터 제거(Pop)
```java
static Integer stackPop() {
    if (stackIsEmpty()) {
        System.out.println("Stack is empty!!");
        return null;
    }
    top--;
    return stack[top];
}
```
- **stackPop()** : 스택의 가장 위에 있는 데이터를 제거하고 반환하는 메서드
  - 스택이 비어 있다면 "Stack is empty!" 메시지를 출력하고 null을 반환
  - 비어 있지 않다면 top을 1감소시키고, 감소된 top 위치의 값을 반환하여 데이터를 꺼낸다.


### 메인 함수
```java
public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
        int N = sc.nextInt();

        stackInit();

        for (int i = 0; i < N; i++) {
            int value = sc.nextInt();
            stackPush(value);
        }
        System.out.println("#" + test_case + " ");

        while (!stackIsEmpty()) {
            Integer value = stackPop();
            if (value != null) {
                System.out.print(value + " ");
            }
        }
        System.out.println();
    }
    sc.close();
}
```