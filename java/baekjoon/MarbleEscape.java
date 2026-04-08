package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class MarbleEscape {
    static int N, M;
    static char[][] board;
    // 빨간 구슬 R, 빨간 구슬 C, 파란 구슬 R, 파란 구슬 C를 담는 4차원 방문 배열
    static boolean[][][][] visited = new boolean[10][10][10][10];
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 상태를 추적하기 위한 객체
    static class State {
        int rx, ry, bx, by, depth;

        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx; this.ry = ry;
            this.bx = bx; this.by = by;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int srx = 0, sry = 0, sbx = 0, sby = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    srx = i; sry = j;
                } else if (board[i][j] == 'B') {
                    sbx = i; sby = j;
                }
            }
        }

        System.out.println(bfs(srx, sry, sbx, sby));
    }

    static int bfs(int srx, int sry, int sbx, int sby) {
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(srx, sry, sbx, sby, 0));
        visited[srx][sry][sbx][sby] = true;

        while (!q.isEmpty()) {
            State curr = q.poll();

            // 10번 움직였는데도 못 찾았다면 더 깊이 들어가지 않음
            if (curr.depth >= 10) continue;

            for (int i = 0; i < 4; i++) {
                // 각각의 구슬을 벽이나 구멍을 만날 때까지 해당 방향으로 굴림
                int[] nextR = move(curr.rx, curr.ry, dr[i], dc[i]);
                int[] nextB = move(curr.bx, curr.by, dr[i], dc[i]);

                // 🚨 파란 구슬이 구멍에 빠진 경우 (무조건 실패이므로 이번 탐색은 버림)
                if (board[nextB[0]][nextB[1]] == 'O') continue;

                // 🎯 빨간 구슬만 구멍에 빠진 경우 (성공!)
                if (board[nextR[0]][nextR[1]] == 'O') {
                    return curr.depth + 1;
                }

                // 구슬이 겹치는 위치에 도달했을 경우의 보정 로직
                if (nextR[0] == nextB[0] && nextR[1] == nextB[1]) {
                    // 더 많이 이동한 구슬이 뒤에 있던 구슬이므로 한 칸 뒤로 뺀다.
                    if (nextR[2] > nextB[2]) {
                        nextR[0] -= dr[i];
                        nextR[1] -= dc[i];
                    } else {
                        nextB[0] -= dr[i];
                        nextB[1] -= dc[i];
                    }
                }

                // 처음 방문하는 상태 배열이라면 큐에 추가
                if (!visited[nextR[0]][nextR[1]][nextB[0]][nextB[1]]) {
                    visited[nextR[0]][nextR[1]][nextB[0]][nextB[1]] = true;
                    q.add(new State(nextR[0], nextR[1], nextB[0], nextB[1], curr.depth + 1));
                }
            }
        }
        return -1; // 큐가 빌 때까지 못 찾으면 실패
    }

    // 한 방향으로 공을 끝까지 굴리는 메서드
    // return 형태: {도착 r좌표, 도착 c좌표, 이동한 칸 수}
    static int[] move(int r, int c, int dr, int dc) {
        int moveCount = 0;
        // 다음 칸이 벽('#')이 아니고, 현재 칸이 구멍('O')이 아닐 때까지 전진
        while (board[r + dr][c + dc] != '#' && board[r][c] != 'O') {
            r += dr;
            c += dc;
            moveCount++;
            if (board[r][c] == 'O') break; // 구멍에 빠지면 그 즉시 멈춤
        }
        return new int[]{r, c, moveCount};
    }
}
