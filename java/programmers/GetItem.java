package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class GetItem {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 좌표를 2배로 늘릴 것이므로 50 * 2 = 100, 넉넉하게 102 사이즈의 맵 배열 생성
        int[][] map = new int[102][102];

        // 1. 모든 직사각형의 좌표를 2배로 확대
        for (int i = 0; i < rectangle.length; i++) {
            rectangle[i][0] *= 2;
            rectangle[i][1] *= 2;
            rectangle[i][2] *= 2;
            rectangle[i][3] *= 2;
        }

        // 2. 전체 맵을 돌면서 "가장 바깥쪽 테두리"만 1로 칠하기
        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                boolean isBorder = false;
                boolean isInside = false;

                // 현재 좌표(x, y)가 어떤 직사각형에 속하는지 판별
                for (int[] r : rectangle) {
                    int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3];

                    // 직사각형의 "내부"에 완전히 들어가는 경우
                    if (x > x1 && x < x2 && y > y1 && y < y2) {
                        isInside = true;
                    }
                    // 직사각형의 "테두리(경계선)"에 있는 경우
                    else if ((x == x1 || x == x2) && y >= y1 && y <= y2) {
                        isBorder = true;
                    }
                    else if ((y == y1 || y == y2) && x >= x1 && x <= x2) {
                        isBorder = true;
                    }
                }

                // 💡 핵심: 어떤 직사각형의 내부에도 속하지 않으면서, 테두리인 곳이 바로 '외곽선'
                if (isBorder && !isInside) {
                    map[x][y] = 1; // 이동 가능한 길로 표시
                }
            }
        }

        // 3. 2배 확대된 맵에서 BFS 탐색 시작
        int cx = characterX * 2;
        int cy = characterY * 2;
        int ix = itemX * 2;
        int iy = itemY * 2;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[102][102];

        q.offer(new int[]{cx, cy, 0}); // [현재 x, 현재 y, 이동 거리]
        visited[cx][cy] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];

            // 목적지에 도착했다면, 늘려놨던 거리를 다시 2로 나누어 반환!
            if (x == ix && y == iy) {
                return dist / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                    // 테두리(1)이면서 방문하지 않은 곳만 이동
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return 0;
    }
}