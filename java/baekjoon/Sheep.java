package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sheep {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cases = Integer.parseInt(st.nextToken());

        for(int c = 0; c < cases; c++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            char[][] grid = new char[H][W];

            for(int h = 0; h < H; h++) {
                String s = br.readLine();
                for(int w = 0; w < W; w++) {
                    grid[h][w] = s.charAt(w);
                }
            }
            int res = bfs(grid);
            bw.write(res+"\n");
        }

        bw.flush();
        bw.close();
    }

    static int bfs(char[][] grid) {
        int res = 0;
        int[] dir = {0, -1, 0, 1, 0};
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == '#' && !visited[r][c]) {
                    res++;
                    visited[r][c] = true;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{r,c});
                    while(!q.isEmpty()) {
                        int[] curr = q.poll();
                        int curR = curr[0];
                        int curC = curr[1];
                        for(int i = 0; i < 4; i++) {
                            int nR = curR + dir[i];
                            int nC = curC + dir[i+1];
                            if(nR >= 0 && nC >= 0 && nR < grid.length && nC < grid[0].length) {
                                if(grid[nR][nC] == '.') {
                                    visited[nR][nC] = true;
                                } else {
                                    if(!visited[nR][nC]) {
                                        visited[nR][nC] = true;
                                        q.add(new int[]{nR, nC});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}
