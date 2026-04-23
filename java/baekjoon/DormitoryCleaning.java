package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DormitoryCleaning {

    static int N, M, K, t;

    // [0: 짝수 날짜 방문 여부, 1: 홀수 날짜 방문 여부][r][c]
    static boolean[][][] visited;
    static List<int[]> points;
    static int[] dirr = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dirc = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // 💡 1번 에러 해결: 인덱스 초과를 막기 위해 N+1 크기로 선언
        visited = new boolean[2][N + 1][N + 1];
        points = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] initMold = new boolean[N + 1][N + 1];

        // 1. 초기 곰팡이 위치 큐에 삽입
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(!initMold[r][c]) {
                initMold[r][c] = true;
                // [행, 열, 진행된 날짜]
                queue.add(new int[]{r, c, 0});

                // 💡 중요: visited[0][r][c] = true; 를 의도적으로 하지 않습니다!
                // 시작하자마자 갇혀서 움직일 수 없는 곰팡이(예: 3x3 정중앙)는
                // 다음 짝수 날짜에 돌아올 수 없어야 하기 때문입니다.
            }
        }

        // 2. 검사할 좌표들 저장
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            points.add(new int[]{r, c});
        }

        // 3. 홀짝 BFS 탐색
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int day = curr[2];

            // 목표 날짜 t에 도달했다면 더 이상 확산할 필요가 없음
            if(day == t) continue;

            int nextDay = day + 1;
            int nextParity = nextDay % 2; // 다음 날이 홀수인지 짝수인지 계산

            for(int i = 0; i < 8; i++) {
                int nr = r + dirr[i];
                int nc = c + dirc[i];

                if(nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
                    // 다음 칸을 '해당 홀짝성'으로 방문한 적이 없을 때만 큐에 추가!
                    if(!visited[nextParity][nr][nc]) {
                        visited[nextParity][nr][nc] = true;
                        queue.add(new int[]{nr, nc, nextDay});
                    }
                }
            }
        }

        // 4. 정답 판별
        int targetParity = t % 2; // t일이 홀수인지 짝수인지
        for(int[] point : points) {
            // 목표 날짜(t)와 홀짝성이 같은 날에 해당 좌표를 방문한 적이 있다면?
            // 왕복을 통해 무조건 t일에 그 자리에 곰팡이가 있을 수밖에 없음!
            if(visited[targetParity][point[0]][point[1]]) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
