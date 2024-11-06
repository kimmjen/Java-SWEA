## Queue

### 1. 기본 큐 초기화 및 구성 요소
```java
static final int MAX_SIZE = 100;
static int[] heap = new int[MAX_SIZE];
static int heapSize = 0;
```

- **MAX_N** : 우선순위 큐의 최대 크기를 정의하는 상수
- **heap** : 우선순위 큐 역할을 할 정수형 배열로, MAX_SIZE 만큼의 고정 크기로 선언
- **heapSize** : 현재 큐에 저장된 요소의 개수를 추적하는 변수

### 2. 우선순위 큐 초기화
```java
static void heapInit() {
  heapSize = 0;
}
```

- **heapInit()** : heapSize를 0으로 설정하여 큐를 초기화, 이 함수는 새로운 테스트 케이스마다 큐를 비우고 초기 상태로 되돌리기 위해 호출

### 3. 우선순위 큐에 데이터 추가 (Enqueue)
```java
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
```

- **heapPush(int value)** : 우선순위 큐에 데이터를 추가
  - 큐가 가득 찼는지 확인하고, 가득 차면 아무 작업도 하지 않고 종료
  - heap[heapSize] 위치에 새로운 값을 추가하고, heapSize를 1증가
  - 새로 추가된 요소를 부모 노드와 비교하여 최소 힙 조건을 만족하도록 교환을 반복


### 4. 우선순위 큐에서 데이터 제거 (Dequeue)
```java
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
```

- **heapPop()** : 최우선 순위 요소(가장 작은 값)를 큐에서 제거하고 반환합니다.
  - heap[0]의 최상위 값을 반환하기 전에 저장하고, 힙의 마지막 요소를 heap[0]에 배치한 후 heapSize를 감소
  - 루트 노드부터 자식 노드들과 비교하여 최소 힙 조건을 만족할 때까지 자리를 교환
  - 최종적으로 최소값이었던 요소가 반환


### 5. 요소 교환 (Swap)
```java
static void swap(int[] heap, int i, int j) {
  int temp = heap[i];
  heap[i] = heap[j];
  heap[j] = temp;
}
```

- **swap(int[] heap, int i, int j)** : 힙 배열에서 두 인덱스 i와 j의 값을 교환합니다.
  - heapPush와 heapPop에서 요소를 재배치할 때 사용

### 6. 우선순위 큐 내용 출력 (Print)
```java
static void heapPrint(int[] heap, int heapSize) {
  for (int i = 0; i < heapSize; i++) {
    System.out.print(heap[i] + " ");
  }
  System.out.println();
}
```

- **heapPrint(int[] heap, int heapSize)** : 현재 힙의 내용을 출력, 주로 디버깅 목적으로 힙의 상태를 확인하는데 사용

### 7. 메인 함수
```java
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

    System.out.println("#" + test_case);
    for (int i = 0; i < N; i++) {
      System.out.print(heapPop() + " ");
    }
    System.out.println();
  }
  sc.close();
}
```
- **큐 초기화 및 연산**
  - `queueInit()`으로 큐를 초기화 한후, 사용자 입력 값을 `queueEnqueue()`를 통해 큐에 삽입
  - 큐에서 `queueDequeue()`를 통해 값을 하나씩 꺼내고, 값이 null이 아닌 경우 출력