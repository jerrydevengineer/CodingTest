package programmers;

public class FourBasicOperations {
    public int solution(String arr[]) {
        // 숫자 개수는 연산자보다 1개 더 많음 (배열 길이 / 2 + 1)
        int n = arr.length / 2 + 1;

        // dp[i][j] : i번째 숫자부터 j번째 숫자까지 연산했을 때의 최댓값/최솟값
        int[][] max_dp = new int[n][n];
        int[][] min_dp = new int[n][n];

        // 숫자와 연산자를 분리할 배열
        int[] nums = new int[n];
        String[] ops = new String[n - 1];

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) nums[i / 2] = Integer.parseInt(arr[i]);
            else ops[i / 2] = arr[i];
        }

        // DP 배열 초기화 (초기값 설정)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 언더플로우 방지를 위해 충분히 큰/작은 수로 초기화
                max_dp[i][j] = Integer.MIN_VALUE / 2;
                min_dp[i][j] = Integer.MAX_VALUE / 2;
            }
            // 자기 자신(숫자 하나)만 있을 때의 최댓값, 최솟값은 자기 자신
            max_dp[i][i] = nums[i];
            min_dp[i][i] = nums[i];
        }

        // 구간 길이를 2부터 n까지 점차 늘려가며 탐색 (구간 DP)
        for (int len = 2; len <= n; len++) { // len: 피연산자(숫자)의 개수
            for (int i = 0; i <= n - len; i++) { // i: 시작 피연산자 인덱스
                int j = i + len - 1; // j: 끝 피연산자 인덱스

                // i부터 j 사이를 k를 기준으로 두 부분으로 나눔
                for (int k = i; k < j; k++) {
                    // 더하기(+) 연산일 때
                    if (ops[k].equals("+")) {
                        // 최댓값 = 최댓값 + 최댓값
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] + max_dp[k + 1][j]);
                        // 최솟값 = 최솟값 + 최솟값
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] + min_dp[k + 1][j]);
                    }
                    // 빼기(-) 연산일 때
                    else if (ops[k].equals("-")) {
                        // 최댓값 = 최댓값 - 최솟값 (앞은 제일 크고, 뒤는 제일 작아야 함)
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] - min_dp[k + 1][j]);
                        // 최솟값 = 최솟값 - 최댓값 (앞은 제일 작고, 뒤는 제일 커야 함)
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] - max_dp[k + 1][j]);
                    }
                }
            }
        }

        // 0번째 숫자부터 마지막(n-1)번째 숫자까지 연산한 결과 중 최댓값 반환
        return max_dp[0][n - 1];
    }
}
