package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DormitoryCleaning {

    static int N, M, K, t;
    static int[][] map;
    static List<int[]> points;
    static int[] dirr = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dirc = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        points = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            points.add(new int[]{r, c});
        }

        for(int i = 0; i < t; i++) {
            Queue<int[]> queue = new ArrayDeque<>();
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(map[r][c] == 1) {
                        map[r][c] = 0;
                        queue.add(new int[]{r, c});
                    }
                }
            }
            while(!queue.isEmpty()) {
                int[] temp = queue.poll();
                spread(temp);
            }
        }

        for(int[] point : points) {
            if(map[point[0]][point[1]] == 1) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    public static void spread(int[] temp) {
        int r = temp[0];
        int c = temp[1];

        for(int i = 0; i < 8; i++) {
            int nr = r + dirr[i];
            int nc = c + dirc[i];
            if(nr > 0 && nc > 0 && nr <= N && nc <= N) {
                map[nr][nc] = 1;
            }
        }
    }
}
