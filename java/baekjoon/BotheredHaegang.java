package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BotheredHaegang {

    static int N, M;
    static int[] nodes;
    static int[] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new int[N+1];
        schedule = new int[N];

        for (int i = 1; i <= N; i++) {
            nodes[i] = i;
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i = 0; i < N-1; i++) {
            if(find(schedule[i]) != find(schedule[i+1])) cnt++;
        }

        System.out.println(cnt);
    }

    public static int find(int x) {
        if(nodes[x] == x) return x;
        return nodes[x] = find(nodes[x]);
    }

    public static void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        nodes[rootx] = rooty;
    }
}
