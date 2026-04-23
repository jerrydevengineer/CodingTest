package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotateArray1 {

    static int N;
    static int M;
    static int R;
    static int[][] map;
    static int std;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                int t = Integer.parseInt(st.nextToken());
                map[r][c] = t;
            }
        }

        std = Math.min(N, M) / 2;

        for(int i = 0; i < R; i++) {
            rotate();
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate() {
        for(int i = 0; i < std; i++) {
            int tmp = map[i][i];
            int c = 0;
            while(c < M - 1 - 2*i) {
                map[i][i+c] = map[i][i+c+1];
                c++;
            }
            int r = 0;
            while(r < N - 1 - 2*i) {
                map[i+r][M - 1 - i] = map[i+r+1][M - 1 - i];
                r++;
            }
            while(c > 0) {
                map[i+r][i+c] = map[i+r][i+c-1];
                c--;
            }
            while(r > 0) {
                map[i+r][i+c] = map[i+r-1][i+c];
                r--;
            }
            map[i+1][i] = tmp;
        }
    }
}
