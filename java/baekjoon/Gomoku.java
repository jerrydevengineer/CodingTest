package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gomoku {

    static int[][] map;
    static int[] dirr = {-1, 0, 1, 1};
    static int[] dirc = {1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[19][19];
        for (int r = 0; r < 19; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 19; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                if (map[r][c] == 1 || map[r][c] == 2) {
                    if (checkWin(r, c, map[r][c])) {
                        System.out.println(map[r][c]);
                        System.out.println((r + 1) + " " + (c + 1));
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

    public static boolean checkWin(int r, int c, int color) {
        for (int i = 0; i < 4; i++) {
            int count = 1;
            int nr = r + dirr[i];
            int nc = c + dirc[i];

            while (nr >= 0 && nc >= 0 && nr < 19 && nc < 19 && map[nr][nc] == color) {
                count++;
                nr += dirr[i];
                nc += dirc[i];
            }

            if (count == 5) {
                int prevR = r - dirr[i];
                int prevC = c - dirc[i];

                if (prevR >= 0 && prevC >= 0 && prevR < 19 && prevC < 19 && map[prevR][prevC] == color) {
                    continue;
                }

                return true;
            }
        }
        return false;
    }
}
