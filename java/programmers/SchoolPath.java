package programmers;

import java.util.Arrays;

public class SchoolPath {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;

        // n이 세로(행), m이 가로(열)이므로 dp[n+1][m+1]로 생성
        int[][] dp = new int[n + 1][m + 1];

        // 물웅덩이 표시 (-1로 설정)
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        // 시작점 설정
        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 시작점이거나 물웅덩이면 건너뜀
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                // 위쪽에서 오는 경우
                if (i > 1) dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                // 왼쪽에서 오는 경우
                if (j > 1) dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
            }
        }

        return dp[n][m];
    }
}
