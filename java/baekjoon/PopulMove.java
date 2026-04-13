package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopulMove {
    static int[][] map;
    static int N, L, R;
    static int sum, eles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());


        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int day = 0;
        boolean con = true;
        while(con) {
            boolean[][] visited = new boolean[N][N];
            boolean[][] changed = new boolean[N][N];
            boolean none = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        sum = 0;
                        eles = 0;
                        bfs(i, j, visited);
                        if(eles > 1) none = true;
                        sum = 0;
                        eles = 0;
                    }
                }
            }
            if(!none) break;
            day++;
        }

        System.out.println(day);
    }

    static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        eles++;
        sum += map[r][c];
        q.offer(new int[]{r, c});
        int[] dir = {0, -1, 0, 1, 0};
        List<int[]> pathes = new ArrayList<>();
        pathes.add(new int[]{r, c});
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for(int i = 0; i < 4; i++) {
                int nextR = curr[0] + dir[i];
                int nextC = curr[1] + dir[i+1];
                if(nextR >= 0 && nextC >= 0 && nextR < N && nextC < N && !visited[nextR][nextC]
                && L <= Math.abs(map[nextR][nextC] - map[curr[0]][curr[1]]) && Math.abs(map[nextR][nextC] - map[curr[0]][curr[1]]) <= R) {
                    visited[nextR][nextC] = true;
                    q.offer(new int[]{nextR, nextC});
                    sum += map[nextR][nextC];
                    eles++;
                    pathes.add(new int[]{nextR, nextC});
                }
            }
        }
        if(eles > 1) {
            int newPopul = sum / eles;
            for (int[] path : pathes) {
                map[path[0]][path[1]] = newPopul;
            }
        }
    }
}