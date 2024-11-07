## Hash

### 1. 기본 해시 테이블 초기화 및 구성 요소
```java
class HashTable {
    class Entry {
        String key;
        String data;

        Entry(String key, String data) {
            this.key = key;
            this.data = data;
        }

        Entry() {
            this.key = null;
            this.data = null;
        }
    }

    static final int MAX_SIZE = 4096;
    Entry[] table;
    int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new Entry();  // 빈 객체로 초기화하여 NullPointerException 방지
        }
    }
}
```

- **Entry 클래스** : key와 data를 포함하는 내부 클래스
  - **key** : 키 값을 저장하는 변수
  - **data** : 키에 대응하는 데이터를 저장하는 변수
- **MAX_SIZE** : 해시 테이블의 최대 크기를 정의하는 상수
- **table** : 해시 테이블로 사용할 Entry 객체 배열
- **capacity** : 해시 테이블의 용량을 저장하는 변수로, MAX_SIZE에 따라 초기화

### 2. 해시 함수
```java
private int hash(String key) {
    int hash = 5381;
    for (int i = 0; i < key.length(); i++) {
        hash = ((hash << 5) + hash) + key.charAt(i);
    }
    return Math.abs(hash) % capacity;  // 음수 방지를 위해 Math.abs 사용
}
```

- **hash(String key)** : 문자열 key를 정수 인덱스로 변환하는 해시 함수.
  - 초기값 5381에서 시작해 각 문자를 더하여 해시 값을 만듬.
  - 해시 값을 Math.abs(hash) % capacity로 배열의 인덱스 내에 맞추어 반환

### 3. 해시 테이블에 데이터 추가(Put)
```java
public boolean put(String key, String data) {
    int index = hash(key);
    while (table[index].key != null) {
        if (table[index].key.equals(key)) {
            return false;  // 중복된 키가 이미 존재할 경우 추가하지 않음
        }
        index = (index + 1) % capacity;  // 선형 탐사로 충돌 해결
    }
    table[index].key = key;
    table[index].data = data;
    return true;
}
```

- **put(String key, string data)** : 해시 테이블에 key와 data를 추가하는 함수
  - 이미 동일한 키가 존재하면 false를 반환하여 추가하지 않음
  - 충돌이 발생하면 선형 탐사를 사용해 다음 빈 자리를 찾아 저장
  - 새로운 키-값 쌍을 추가하면 true로 반환.

### 4. 해시 테이블에 데이터 검색(Get)
```java
public String get(String key) {
    int index = hash(key);
    int attempts = capacity;  // 최대 탐사 횟수를 용량으로 설정

    while (table[index].key != null && attempts-- > 0) {
        if (table[index].key.equals(key)) {
            return table[index].data;  // 키에 해당하는 데이터를 반환
        }
        index = (index + 1) % capacity;  // 선형 탐사로 계속 검색
    }
    return null;  // 키가 존재하지 않으면 null 반환
}
```

- **get(String key)** : 주어진 key를 사용해 데이터르 검색하는 함수.
  - 해시 값에서 시작하여 일치하는 키가 나올 때까지 선형 탐사로 검색
  - 일치하는 키를 찾으면 해당 데이터를 반환하고, 찾지 못하면 Null을 반환.