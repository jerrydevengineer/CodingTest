package programmers;

import java.util.Arrays;

public class PulseSeqSum {
    public long solution(int[] sequence) {
        long answer = 0;

        // 펄스 A(1, -1, 1, ...)를 적용했을 때의 현재 누적합
        long currentSumA = 0;
        // 펄스 B(-1, 1, -1, ...)를 적용했을 때의 현재 누적합
        long currentSumB = 0;

        // 최대합을 저장할 변수 (최소값을 아주 작은 값으로 초기화)
        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i < sequence.length; i++) {
            // i가 짝수면 펄스 A는 1, 펄스 B는 -1
            // i가 홀수면 펄스 A는 -1, 펄스 B는 1
            long pulseA_val = (i % 2 == 0) ? sequence[i] : -sequence[i];
            long pulseB_val = (i % 2 == 0) ? -sequence[i] : sequence[i];

            // 누적합 계산 (기존 누적합에 현재 펄스값을 더함)
            currentSumA += pulseA_val;
            currentSumB += pulseB_val;

            // 두 펄스의 누적합 중 더 큰 값으로 최대합 갱신
            maxSum = Math.max(maxSum, Math.max(currentSumA, currentSumB));

            // 카다네 알고리즘의 핵심: 누적합이 0보다 작아지면 미련 없이 버리고 0부터 새로 시작!
            if (currentSumA < 0) {
                currentSumA = 0;
            }
            if (currentSumB < 0) {
                currentSumB = 0;
            }
        }

        answer = maxSum;
        return answer;
    }
}
