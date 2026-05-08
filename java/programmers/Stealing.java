package programmers;

public class Stealing {
    public int solution(int[] money) {
        int n = money.length;

        // 집이 3개인 경우, 서로 모두 인접해 있으므로 가장 돈이 많은 집 1개만 털 수 있음
        if (n == 3) {
            return Math.max(money[0], Math.max(money[1], money[2]));
        }

        // 1. 첫 번째 집을 터는 경우 (마지막 집은 털 수 없음)
        int prev2_case1 = money[0]; // 전전 집 (dp[0])
        int prev1_case1 = money[0]; // 이전 집 (dp[1])

        for (int i = 2; i < n - 1; i++) {
            int curr = Math.max(prev1_case1, prev2_case1 + money[i]);
            prev2_case1 = prev1_case1;
            prev1_case1 = curr;
        }
        int max1 = prev1_case1; // case 1의 최댓값

        // 2. 첫 번째 집을 털지 않는 경우 (마지막 집 털 수 있음)
        int prev2_case2 = 0;        // 전전 집 (dp[0])
        int prev1_case2 = money[1]; // 이전 집 (dp[1])

        for (int i = 2; i < n; i++) {
            int curr = Math.max(prev1_case2, prev2_case2 + money[i]);
            prev2_case2 = prev1_case2;
            prev1_case2 = curr;
        }
        int max2 = prev1_case2; // case 2의 최댓값

        // 두 경우 중 더 큰 값 반환
        return Math.max(max1, max2);
    }
}
