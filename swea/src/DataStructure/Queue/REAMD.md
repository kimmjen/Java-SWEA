## Queue

### 1. 기본 큐 초기화 및 구성 요소
```java
static final int MAX_N = 100;
static int front;
static int rear;
static int queue[] = new int[MAX_N];
```

- **MAX_N** : 큐의 최대 크기를 정의하는 상수
- **front**, **rear** : 큐의 앞(front)과 뒤(rear)를 나타내는 인덱스, front는 큐에서 데이터를 제거할 위치를 가리키고, rear는 데이터를 삽입할 위치를 가리킨다.
- **queue[]** : 데이터를 저장할 정수형 배열, 코정 크기로 선언

### 2. 큐 초기화
```java
static void queueInit() {
    front = 0;
    rear = 0;
}
```

- **queueInit()** : 큐를 초기화하여 front와 rear를 0으로 설정

### 3. 큐가 비었는지 확인
```java
public boolean queueIsEmpty() {
    return (front == rear);
}
```

- **queueIsEmpty()** : front와 rear가 같다면 큐가 비어 있는 상태로 간주하고 true를 반환


### 4. 큐가 가득 찼는지 확인
```java
static boolean queueIsFull() {
    return ((rear + 1) % MAX_N == front);
}
```

- **queueIsFull()** : 원형 큐로 구현되어 있으므로, `(rear + 1) % MAX_N`가 front와 같을 때 큐가 가득 찬 상태로 간주


### 5. 큐에 데이터 추가(Enqueue)
```java
static boolean queueEnqueue(int value) {
    if (queueIsFull()) {
        System.out.println("Queue is Full!");
        return false;
    }
    queue[rear] = value;
    rear = (rear + 1) % MAX_N;
    return true;
}
```

- **queueEnqueue()** : 큐에 새 데이터를 삽입
  - 큐가 가득 찼다면, "Queue is Full" 메시지를 출력하고 false를 반환하여 더 이상 데이터를 삽입 할 수 없음을 알린다.
  - 가득 차지 않았다면 rear 위치에 값을 저장하고 rear를 다음 위치로 이동, 원형 큐이므로 (read + 1) % MAX_N 연산을 사용해 배열 끝에 도달했을 때 인덱스를 다시 0으로 돌린다.

### 6. 큐에서 데이터 제거(Dequeue)
```java
static Integer queueDequeue() {
    if (queueIsEmpty()) {
        System.out.println("Queue is empty!");
        return null;
    }
    int value = queue[front];
    front = (front + 1) % MAX_N;
    return value;
}
```

- **queueDequeue()** : 큐에서 데이터를 제거하고 반환
  - 큐가 비어 있다면, "Queue is empty" 메시지 출력하고 null을 반환
  - 비어 있지 않다면, front 위치의 데이터를 저장하고, front를 다음 위치로 이동, 원형 큐로 구현했으므로 (front + 1) % MAX_N 연산을 통해 다시 처음으로 돌아갈 수 있도록 함.

### 7. 메인 함수
```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
        int N = sc.nextInt();

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
        
    }
    sc.close();
}
```
- **큐 초기화 및 연산**
  - `queueInit()`으로 큐를 초기화 한후, 사용자 입력 값을 `queueEnqueue()`를 통해 큐에 삽입
  - 큐에서 `queueDequeue()`를 통해 값을 하나씩 꺼내고, 값이 null이 아닌 경우 출력