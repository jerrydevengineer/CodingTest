package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InsertOperator {
    static int N;
    static int[] numbers;
    static int[] operators = new int[4]; // 0: +, 1: -, 2: *, 3: /
    
    // 결괏값이 최대 10억, 최소 -10억이므로 int 범위 내에서 안전합니다.
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine().trim());
        numbers = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        // DFS 시작: 첫 번째 숫자를 결괏값으로 넣고, 두 번째 숫자(인덱스 1)부터 탐색
        dfs(1, numbers[0]);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    // depth: 다음에 계산할 숫자의 인덱스
    // currentVal: 현재까지 누적된 계산 결과
    static void dfs(int depth, int currentVal) {
        // [종료 조건] 모든 숫자를 다 계산했을 때
        if (depth == N) {
            MAX = Math.max(MAX, currentVal);
            MIN = Math.min(MIN, currentVal);
            return;
        }

        // 4가지 연산자를 순회
        for (int i = 0; i < 4; i++) {
            // 해당 연산자가 아직 남아있다면
            if (operators[i] > 0) {
                // 연산자 사용
                operators[i]--;

                // 선택된 연산자에 맞춰 계산 후 다음 재귀 호출
                if (i == 0) {
                    dfs(depth + 1, currentVal + numbers[depth]);
                } else if (i == 1) {
                    dfs(depth + 1, currentVal - numbers[depth]);
                } else if (i == 2) {
                    dfs(depth + 1, currentVal * numbers[depth]);
                } else if (i == 3) {
                    dfs(depth + 1, currentVal / numbers[depth]); // 자바는 음수 나눗셈을 문제 조건대로 자동 처리함
                }

                // 다른 경우의 수를 탐색하기 위해 연산자 개수 복구 (백트래킹)
                operators[i]++;
            }
        }
    }
}
