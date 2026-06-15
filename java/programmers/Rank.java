package programmers;

public class Rank {
    public int solution(int n, int[][] results) {
        // 승패 관계를 저장할 2차원 배열 (wins[i][j]가 true면 i가 j를 이겼다는 의미)
        boolean[][] wins = new boolean[n + 1][n + 1];

        // 1. 주어진 경기 결과 기록
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            wins[winner][loser] = true;
        }

        // 2. 플로이드-워셜 알고리즘 수행 (거쳐가는 노드 k를 가장 바깥쪽 루프로!)
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // i가 k를 이기고, k가 j를 이겼다면 -> i는 j를 이긴 것!
                    if (wins[i][k] && wins[k][j]) {
                        wins[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;

        // 3. 순위를 정확히 알 수 있는 선수 카운트
        for (int i = 1; i <= n; i++) {
            int knownMatchCount = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;

                // i가 j를 이겼거나, j가 i를 이겼다면 (승패 관계를 아는 경우)
                if (wins[i][j] || wins[j][i]) {
                    knownMatchCount++;
                }
            }

            // 본인을 제외한 모든 선수(n-1)와의 승패를 안다면 순위 확정
            if (knownMatchCount == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
