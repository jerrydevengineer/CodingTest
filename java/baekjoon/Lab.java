package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab {
    static int N, M;
    static int[][] map;
    static List<int[]> emptySpaces = new ArrayList<>(); // 빈 칸 좌표 모음
    static List<int[]> viruses = new ArrayList<>();     // 바이러스 좌표 모음
    static int maxSafeArea = 0;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j}); // 빈칸 위치 저장
                } else if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});     // 바이러스 위치 저장
                }
            }
        }

        // 1. 벽 3개를 세우는 모든 조합 찾기 (백트래킹)
        buildWall(0, 0);

        System.out.println(maxSafeArea);
    }

    // 빈칸 중 3개를 골라 벽을 세우는 재귀 함수
    static void buildWall(int depth, int start) {
        // [종료 조건] 벽 3개를 다 세웠다면
        if (depth == 3) {
            spreadVirus(); // 바이러스를 퍼뜨려본다
            return;
        }

        // 빈칸 리스트에서 순서대로 벽을 세워봄 (조합)
        for (int i = start; i < emptySpaces.size(); i++) {
            int[] pos = emptySpaces.get(i);
            map[pos[0]][pos[1]] = 1; // 벽 세우기
            
            buildWall(depth + 1, i + 1); // 다음 벽 세우러 가기
            
            map[pos[0]][pos[1]] = 0; // 원상 복구 (백트래킹)
        }
    }

    // 2 & 3. 바이러스를 퍼뜨리고 안전 영역을 계산하는 함수
    static void spreadVirus() {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] clonedMap = new int[N][M];

        // 원본 맵을 훼손하면 안 되므로 복사본 생성
        for (int i = 0; i < N; i++) {
            clonedMap[i] = map[i].clone();
        }

        // 모든 초기 바이러스 위치를 큐에 삽입
        for (int[] v : viruses) {
            q.add(new int[]{v[0], v[1]});
        }

        // BFS로 바이러스 확산
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 맵 범위를 벗어나지 않고, 빈 칸(0)이라면 감염
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (clonedMap[nr][nc] == 0) {
                        clonedMap[nr][nc] = 2; // 감염 처리
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        // 확산이 끝난 후 안전 영역(0) 개수 세기
        int safeCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (clonedMap[i][j] == 0) {
                    safeCount++;
                }
            }
        }

        // 최댓값 갱신
        maxSafeArea = Math.max(maxSafeArea, safeCount);
    }
}
