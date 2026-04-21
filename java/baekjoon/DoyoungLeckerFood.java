package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DoyoungLeckerFood {

    static int N;
    static int[] sour, salty;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        sour = new int[N];
        salty = new int[N];
        res = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sour[i] = a;
            salty[i] = b;
        }

        for(int i = 1; i <= N; i++) {
            backtracking(0, 0, i, 1, 0);
        }

        System.out.println(res);
    }

    public static void backtracking(int now, int depth, int max, int mul, int sum) {
        if(depth == max) {
            int temp = Math.abs(mul - sum);
            if(temp < res) {
                res = temp;
            }
        }

        if(now >= N) return;

        mul = mul * sour[now];
        sum = sum + salty[now];
        backtracking(now+1, depth+1, max, mul, sum);

        mul = mul / sour[now];
        sum = sum - salty[now];
        backtracking(now+1, depth, max, mul, sum);
    }
}
