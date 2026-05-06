package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepresentingN {

    public int solution(int N, int number) {
        // 1. N과 number가 같으면 N을 1번 사용해서 바로 만들 수 있음
        if (N == number) {
            return 1;
        }

        // 2. DP를 위한 Set 배열(또는 리스트) 생성
        // dp.get(i)는 N을 i번 사용해서 만들 수 있는 수들의 집합
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        // dp.get(1)은 N을 1번 사용한 경우 (자기 자신)
        dp.get(1).add(N);

        // 3. N을 2번부터 8번까지 사용하는 경우를 순차적으로 계산
        for (int i = 2; i <= 8; i++) {
            // 3-1. N을 i번 연달아 이어 붙인 수 추가 (예: 55, 555)
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append(N);
            }
            dp.get(i).add(Integer.parseInt(sb.toString()));

            // 3-2. dp.get(j) 와 dp.get(i-j) 의 사칙연산 결과 조합
            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int x : dp.get(j)) {
                    for (int y : dp.get(k)) {
                        dp.get(i).add(x + y);
                        dp.get(i).add(x - y);
                        dp.get(i).add(x * y);
                        // 0으로 나누는 에러(ArithmeticException) 방지
                        if (y != 0) {
                            dp.get(i).add(x / y);
                        }
                    }
                }
            }

            // 3-3. 이번 i번 사용해서 만든 집합에 목표 숫자(number)가 있다면 i 반환
            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        // 4. 8번까지 사용해도 목표 숫자를 만들지 못했다면 -1 반환
        return -1;
    }
}
