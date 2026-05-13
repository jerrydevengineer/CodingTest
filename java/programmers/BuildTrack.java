package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BuildTrack {

    class Track implements Comparable<Track> {
        int r, c, dir, cost;

        Track(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir; // 0: 상, 1: 우, 2: 하, 3: 좌
            this.cost = cost;
        }

        @Override
        public int compareTo(Track o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        // 상, 우, 하, 좌
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // 3차원 최단 거리 배열: dist[r][c][방향]
        int[][][] dist = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Track> pq = new PriorityQueue<>();
        // 출발점 초기화 (방향이 아직 없으므로 -1로 설정, 비용은 0)
        pq.offer(new Track(0, 0, -1, 0));

        for (int i = 0; i < 4; i++) {
            dist[0][0][i] = 0;
        }

        while (!pq.isEmpty()) {
            Track curr = pq.poll();

            // 목적지 도착 시 최솟값 리턴 (PQ를 쓰므로 처음 도달한 것이 무조건 최소 비용)
            if (curr.r == n - 1 && curr.c == n - 1) {
                return curr.cost;
            }

            // 이미 더 싼 비용으로 해당 방향을 통해 도착한 적이 있다면 스킵 (시간 최적화)
            if (curr.dir != -1 && dist[curr.r][curr.c][curr.dir] < curr.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && board[nr][nc] == 0) {
                    int nextCost = curr.cost;

                    // 처음 출발(-1)이거나 같은 방향(i)이면 직선도로(100)
                    if (curr.dir == -1 || curr.dir == i) {
                        nextCost += 100;
                    } else {
                        // 방향이 꺾이면 코너(500) + 직선(100) = 600
                        nextCost += 600;
                    }

                    // 계산된 비용이 기존에 기록된 해당 방향의 최소 비용보다 싸다면 갱신 후 큐에 삽입
                    if (nextCost < dist[nr][nc][i]) {
                        dist[nr][nc][i] = nextCost;
                        pq.offer(new Track(nr, nc, i, nextCost));
                    }
                }
            }
        }
        return 0;
    }
}
