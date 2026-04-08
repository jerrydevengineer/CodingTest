package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class RollDice {

    static class Dice {
        int up;
        int front;
        int east;
        int[] nums;

        public Dice(int up, int front, int east) {
            this.up = up;
            this.front = front;
            this.east = east;
            this.nums = new int[7];
        }

        public void copy(int num) {
            this.nums[7-up] = num;
        }

        public void roll(int dir) {
            if(dir == 1) {
                int down = east;
                east = up;
                up = 7-down;
            } else if(dir == 2) {
                int west = up;
                up = east;
                east = 7-west;
            } else if(dir == 3) {
                int down = front;
                front = up;
                up = 7-down;
            } else if(dir == 4) {
                int back = up;
                up = front;
                front = 7-back;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[] commands = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++) commands[k] = Integer.parseInt(st.nextToken());
        int[] dirY = {0, 1, -1, 0, 0};
        int[] dirX = {0, 0, 0, -1, 1};
        Dice dice = new Dice(1, 5, 3);

        for(int command : commands) {
            int nX = x + dirX[command];
            int nY = y + dirY[command];

            if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;

            x = nX;
            y = nY;

            dice.roll(command);

            int num = map[x][y];
            if(num == 0) {
                map[x][y] = dice.nums[7-dice.up];
            } else {
                dice.copy(num);
                map[x][y] = 0;
            }

            bw.write(dice.nums[dice.up] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
