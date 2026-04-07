package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game2048 {

    static int N;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 초기 상태의 최댓값도 기록해 둡니다.
                maxBlock = Math.max(maxBlock, board[i][j]); 
            }
        }

        // 깊이 0부터 DFS 탐색 시작
        dfs(0, board);
        System.out.println(maxBlock);
    }

    static void dfs(int depth, int[][] board) {
        // [종료 조건] 5번 이동을 마쳤을 때
        if (depth == 5) {
            return; 
        }

        // 4방향(0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽)으로 이동 시뮬레이션
        for (int dir = 0; dir < 4; dir++) {
            int[][] nextBoard = move(dir, board);
            dfs(depth + 1, nextBoard);
        }
    }

    // 방향에 맞춰 블록을 밀고 합치는 핵심 메서드
    static int[][] move(int dir, int[][] board) {
        int[][] next = new int[N][N];

        if (dir == 0) { // UP (위로 밀기)
            for (int c = 0; c < N; c++) {
                int idx = 0;             // 값이 들어갈 행의 위치
                int lastMerged = -1;     // 마지막으로 병합이 발생한 위치 기록
                
                for (int r = 0; r < N; r++) {
                    if (board[r][c] == 0) continue; // 빈칸 무시
                    
                    // 바로 직전에 들어간 값과 같고, 그 값이 이번 턴에 합쳐진 적이 없다면 병합
                    if (idx > 0 && next[idx - 1][c] == board[r][c] && lastMerged != idx - 1) {
                        next[idx - 1][c] *= 2;
                        maxBlock = Math.max(maxBlock, next[idx - 1][c]); // 최댓값 갱신
                        lastMerged = idx - 1; // 병합되었다고 기록
                    } else {
                        // 다르면 그대로 다음 칸에 삽입
                        next[idx][c] = board[r][c];
                        idx++;
                    }
                }
            }
        } 
        else if (dir == 1) { // DOWN (아래로 밀기)
            for (int c = 0; c < N; c++) {
                int idx = N - 1; // 아래에서부터 위로 올라가며 채움
                int lastMerged = -1;
                for (int r = N - 1; r >= 0; r--) {
                    if (board[r][c] == 0) continue;
                    
                    if (idx < N - 1 && next[idx + 1][c] == board[r][c] && lastMerged != idx + 1) {
                        next[idx + 1][c] *= 2;
                        maxBlock = Math.max(maxBlock, next[idx + 1][c]);
                        lastMerged = idx + 1;
                    } else {
                        next[idx][c] = board[r][c];
                        idx--;
                    }
                }
            }
        } 
        else if (dir == 2) { // LEFT (왼쪽으로 밀기)
            for (int r = 0; r < N; r++) {
                int idx = 0; // 왼쪽에서부터 오른쪽으로 채움
                int lastMerged = -1;
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) continue;
                    
                    if (idx > 0 && next[r][idx - 1] == board[r][c] && lastMerged != idx - 1) {
                        next[r][idx - 1] *= 2;
                        maxBlock = Math.max(maxBlock, next[r][idx - 1]);
                        lastMerged = idx - 1;
                    } else {
                        next[r][idx] = board[r][c];
                        idx++;
                    }
                }
            }
        } 
        else if (dir == 3) { // RIGHT (오른쪽으로 밀기)
            for (int r = 0; r < N; r++) {
                int idx = N - 1; // 오른쪽에서부터 왼쪽으로 채움
                int lastMerged = -1;
                for (int c = N - 1; c >= 0; c--) {
                    if (board[r][c] == 0) continue;
                    
                    if (idx < N - 1 && next[r][idx + 1] == board[r][c] && lastMerged != idx + 1) {
                        next[r][idx + 1] *= 2;
                        maxBlock = Math.max(maxBlock, next[r][idx + 1]);
                        lastMerged = idx + 1;
                    } else {
                        next[r][idx] = board[r][c];
                        idx--;
                    }
                }
            }
        }
        return next;
    }
}
