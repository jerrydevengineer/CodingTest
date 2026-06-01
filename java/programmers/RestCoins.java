package programmers;

public class RestCoins {
    public int solution(int n, int[] money) {
        int MOD = 1000000007;

        // dp[i] : 금액 i를 거슬러 줄 수 있는 방법의 수
        int[] dp = new int[n + 1];

        // 0원을 만드는 방법은 1가지 (아무 동전도 주지 않는 경우)
        dp[0] = 1;

        // 각 동전 단위마다 순회
        for (int coin : money) {
            // 현재 동전 금액부터 목표 금액(n)까지 dp 배열 갱신
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }

        // n원을 거슬러 줄 수 있는 총 방법의 수 반환
        return dp[n];
    }
}
