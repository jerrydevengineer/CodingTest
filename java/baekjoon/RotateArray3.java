package baekjoon;

import java.io.*;
import java.util.*;

public class RotateArray3 {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 연산 명령어 입력 및 실행
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int op = Integer.parseInt(st.nextToken());
            switch (op) {
                case 1: op1(); break;
                case 2: op2(); break;
                case 3: op3(); break;
                case 4: op4(); break;
                case 5: op5(); break;
                case 6: op6(); break;
            }
        }

        // 최종 배열 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 1. 상하 반전
    static void op1() {
        int[][] temp = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[N - 1 - r][c] = map[r][c];
            }
        }
        map = temp;
    }

    // 2. 좌우 반전
    static void op2() {
        int[][] temp = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[r][M - 1 - c] = map[r][c];
            }
        }
        map = temp;
    }

    // 3. 오른쪽 90도 회전 (🔥 N, M 변경 주의)
    static void op3() {
        int[][] temp = new int[M][N]; // 크기가 바뀜!
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[c][N - 1 - r] = map[r][c];
            }
        }
        // 전역 변수 N, M 스왑
        int tmp = N;
        N = M;
        M = tmp;
        map = temp;
    }

    // 4. 왼쪽 90도 회전 (🔥 N, M 변경 주의)
    static void op4() {
        int[][] temp = new int[M][N]; // 크기가 바뀜!
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                temp[M - 1 - c][r] = map[r][c];
            }
        }
        // 전역 변수 N, M 스왑
        int tmp = N;
        N = M;
        M = tmp;
        map = temp;
    }

    // 5. 4등분 후 시계방향 이동
    static void op5() {
        int[][] temp = new int[N][M];
        int halfN = N / 2;
        int halfM = M / 2;

        for (int r = 0; r < halfN; r++) {
            for (int c = 0; c < halfM; c++) {
                temp[r][c + halfM] = map[r][c];                 // 1번(좌상) -> 2번(우상)
                temp[r + halfN][c + halfM] = map[r][c + halfM]; // 2번(우상) -> 3번(우하)
                temp[r + halfN][c] = map[r + halfN][c + halfM]; // 3번(우하) -> 4번(좌하)
                temp[r][c] = map[r + halfN][c];                 // 4번(좌하) -> 1번(좌상)
            }
        }
        map = temp;
    }

    // 6. 4등분 후 반시계방향 이동
    static void op6() {
        int[][] temp = new int[N][M];
        int halfN = N / 2;
        int halfM = M / 2;

        for (int r = 0; r < halfN; r++) {
            for (int c = 0; c < halfM; c++) {
                temp[r + halfN][c] = map[r][c];                 // 1번(좌상) -> 4번(좌하)
                temp[r + halfN][c + halfM] = map[r + halfN][c]; // 4번(좌하) -> 3번(우하)
                temp[r][c + halfM] = map[r + halfN][c + halfM]; // 3번(우하) -> 2번(우상)
                temp[r][c] = map[r][c + halfM];                 // 2번(우상) -> 1번(좌상)
            }
        }
        map = temp;
    }
}