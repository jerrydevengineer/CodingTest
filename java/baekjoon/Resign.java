package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Resign {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] plans = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int period = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            plans[i][0] = period;
            plans[i][1] = price;
        }

        int[] dp = new int[N+2];
        for(int d = N; d > 0; d--) {
            if(plans[d-1][0] + d > N + 1) {
                dp[d] = dp[d+1];
            } else {
                int notToday = dp[d+1];
                int today = plans[d-1][1] + dp[plans[d-1][0] + d];
                dp[d] = Math.max(today, notToday);
            }
        }

        System.out.println(dp[1]);
    }
}
