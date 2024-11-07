package DataStructure.Hash;

import java.util.Scanner;

class Hashtable {
    class Hash {
        String key;
        String data;

        Hash(String key, String data) {
            this.key = key;
            this.data = data;
        }

        Hash() {
            this.key = null;
            this.data = null;
        }
    }

    int capacity;
    Hash[] tb;

    public Hashtable(int capacity) {
        this.capacity = capacity;
        tb = new Hash[capacity];
        for (int i = 0; i < capacity; i++) {
            tb[i] = new Hash();  // 빈 객체로 초기화하여 NullPointerException 방지
        }
    }

    private int hash(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }
        return Math.abs(hash) % capacity;  // 음수 방지를 위해 Math.abs 사용
    }

    public String find(String key) {
        int h = hash(key);
        int cnt = capacity;

        while (tb[h].key != null && (--cnt) != 0) {
            if (tb[h].key.equals(key)) {
                return tb[h].data;
            }
            h = (h + 1) % capacity;
        }
        return null;
    }

    public boolean add(String key, String data) {
        int h = hash(key);
        while (tb[h].key != null) {
            if (tb[h].key.equals(key)) {
                return false;  // 이미 존재하는 키는 추가하지 않음
            }
            h = (h + 1) % capacity;
        }
        tb[h].key = key;
        tb[h].data = data;
        return true;
    }
}


public class Solution {
    final static int MAX_TABLE = 4096;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int T = sc.nextInt();
            sc.nextLine();  // 다음 줄로 이동하여 줄바꿈이나 주석 제거

            for (int test_case = 1; test_case <= T; test_case++) {
                Hashtable tb = new Hashtable(MAX_TABLE);
                int N = sc.nextInt();
                sc.nextLine();  // 줄바꿈 이동

                for (int i = 0; i < N; i++) {
                    String line = sc.nextLine();
                    String[] parts = line.split(" ");
                    String key = parts[0];
                    String data = parts[1];
                    tb.add(key, data);
                }

                System.out.printf("#%d\n", test_case);
                int Q = sc.nextInt();
                sc.nextLine();  // 줄바꿈 이동

                for (int i = 0; i < Q; i++) {
                    String queryKey = sc.nextLine().trim();
                    String result = tb.find(queryKey);
                    if (result != null) {
                        System.out.printf("%s\n", result);
                    } else {
                        System.out.printf("not find\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }
}