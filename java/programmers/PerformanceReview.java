package programmers;

import java.util.Arrays;

public class PerformanceReview {
    public int solution(int[][] scores) {
        // 1. 완호의 점수 미리 저장 (정렬하면 위치가 섞이므로)
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        // 2. 정렬: 근무 태도 내림차순, 같다면 동료 평가 오름차순
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1]; // 오름차순
            }
            return o2[0] - o1[0]; // 내림차순
        });

        int maxPeerScore = 0;
        int rank = 1;

        // 3. 순회하면서 탈락자 확인 및 석차 계산
        for (int[] score : scores) {
            int currentA = score[0];
            int currentB = score[1];

            // 이미 앞사람들(근무태도가 더 높은 사람들) 중 가장 높은 동료 평가 점수보다 내 점수가 낮다면 탈락
            if (currentB < maxPeerScore) {
                // 만약 탈락한 사람이 완호라면 즉시 -1 반환
                if (currentA == wanhoA && currentB == wanhoB) {
                    return -1;
                }
            } else {
                // 탈락하지 않은 경우, 동료 평가 점수 최댓값 갱신
                maxPeerScore = Math.max(maxPeerScore, currentB);

                // 완호보다 점수 합이 높다면 내 앞에 석차가 1명 늘어남
                if (currentA + currentB > wanhoSum) {
                    rank++;
                }
            }
        }

        return rank;
    }
}
