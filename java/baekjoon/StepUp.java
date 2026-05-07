package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StepUp {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] scores = new int[N+1];
        for(int i = 1; i <= N; i++) {
            int score = Integer.parseInt(br.readLine());
            scores[i] = score;
        }

        int[] dp = new int[N+1];
        dp[1] = scores[1];
        if(N >= 2) dp[2] = dp[1] + scores[2];
        if(N >= 3) dp[3] = Math.max(scores[1], scores[2]) + scores[3];

        for(int i = 4; i <= N; i++) {
            int A = dp[i-2] + scores[i];
            int B = dp[i-3] + scores[i-1] + scores[i];
            dp[i] = Math.max(A, B);
        }

        System.out.println(dp[N]);
    }
}
