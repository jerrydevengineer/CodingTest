package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ComplexNum {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirs = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int r = 0; r < N; r++) {
            String s = br.readLine();
            char[] chars = s.toCharArray();
            for(int c = 0; c < N; c++) {
                int a = chars[c] - '0';
                map[r][c] = a;
            }
        }
        br.close();

        int now = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] == 1 && !visited[r][c]) {
                    now++;
                    visited[r][c] = true;
                    int s = bfs(r, c);
                    pq.add(new int[]{now, s});
                }
            }
        }

        System.out.println(now);
        if(now == 0) return;
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            System.out.println(curr[1]);
        }
    }

    static int bfs(int a, int b) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{a, b});
        int res = 1;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for(int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i+1];
                if(checkValid(nr, nc)) {
                    res++;
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return res;
    }

    static boolean checkValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N && map[r][c] == 1 && !visited[r][c];
    }
}
