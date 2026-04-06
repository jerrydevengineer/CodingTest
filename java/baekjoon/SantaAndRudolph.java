package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SantaAndRudolph {

    static int M, N, H;
    static int[][][] dir;
    
    // 64MB 초경량 비트 패킹 DP 캐시 (메모리 초과 및 GC 폭주 완벽 방지)
    static final int HASH_SIZE = 4000037; 
    static int[] head = new int[HASH_SIZE];
    static int[] next = new int[HASH_SIZE];
    // 상위 32비트: Key(상태), 하위 32비트: Value(경우의 수)를 담는 배열
    static long[] state_kv = new long[HASH_SIZE]; 
    static int state_cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        int[][] idMap = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(idMap[i], -1);

        int hCount = 0;
        int churchR = -1, churchC = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    hCount++;
                    idMap[i][j] = hCount; 
                } else if (grid[i][j] == 2) {
                    churchR = i;
                    churchC = j;
                    idMap[i][j] = 0; 
                }
            }
        }

        H = hCount;
        
        int[] nodesR = new int[H + 1];
        int[] nodesC = new int[H + 1];
        nodesR[0] = churchR; nodesC[0] = churchC;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    nodesR[idMap[i][j]] = i;
                    nodesC[idMap[i][j]] = j;
                }
            }
        }

        // 시야 전처리 (직선상에 보이는 건물 ID 캐싱)
        dir = new int[H + 1][4][];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i <= H; i++) {
            for (int d = 0; d < 4; d++) {
                List<Integer> list = new ArrayList<>();
                int nr = nodesR[i], nc = nodesC[i];
                while (true) {
                    nr += dr[d]; nc += dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
                    if (idMap[nr][nc] != -1) list.add(idMap[nr][nc]);
                }
                dir[i][d] = new int[list.size()];
                for (int k = 0; k < list.size(); k++) dir[i][d][k] = list.get(k);
            }
        }

        System.out.println(dfs(0, 0, 0));
    }

    // 🔥 핵심 무기 1: 순수 도달 가능성만 검사하는 무결점 비트마스킹 BFS
    static boolean isPossible(int u, int visitedMask) {
        // 우리가 도달해야 하는 목표들: 교회(0번 비트) + 남은 미방문 집들
        int targetMask = ((1 << (H + 1)) - 1) ^ visitedMask;
        int reached = (1 << u);
        int newlyReached = (1 << u);

        // 객체 생성 없는 초고속 비트 BFS 탐색
        while (newlyReached != 0) {
            int curr = Integer.numberOfTrailingZeros(newlyReached);
            newlyReached ^= (1 << curr);

            for (int d = 0; d < 4; d++) {
                for (int w : dir[curr][d]) {
                    if (w != 0 && (visitedMask & (1 << w)) != 0) break; // 연기에 가로막힘
                    if ((reached & (1 << w)) == 0) { // 처음 발견한 건물이라면
                        reached |= (1 << w);
                        newlyReached |= (1 << w);
                    }
                }
            }
        }
        
        // 타겟들 중 하나라도 도달하지 못했다면(고립되었다면) 가차없이 가지치기
        return (reached & targetMask) == targetMask;
    }

    static int dfs(int u, int count, int visitedMask) {
        // [종료 조건] 모든 집에 선물을 다 돌린 경우 교회로 복귀 가능한지 확인
        if (count == H) {
            for (int d = 0; d < 4; d++) {
                for (int v : dir[u][d]) {
                    if (v == 0) return 1; 
                    if ((visitedMask & (1 << v)) != 0) break; 
                }
            }
            return 0;
        }

        // 🚀 강력하고 안전한 가지치기: 불가능한 맵 상태라면 탐색 조기 종료
        if (!isPossible(u, visitedMask)) {
            return 0;
        }

        // 🔥 핵심 무기 2: 상태 압축 및 비트 패킹 DP 캐시
        int key = (u << 24) | visitedMask;
        int hash = key % HASH_SIZE;
        
        for (int i = head[hash]; i != 0; i = next[i]) {
            long kv = state_kv[i];
            if ((int)(kv >>> 32) == key) { // 상위 32비트에 저장된 Key가 일치한다면
                return (int)kv;            // 하위 32비트에 저장된 Value 반환
            }
        }

        int sum = 0;
        for (int d = 0; d < 4; d++) {
            for (int v : dir[u][d]) {
                if (v == 0) continue; // 아직 선물을 다 안 돌렸으니 교회는 스킵
                if ((visitedMask & (1 << v)) != 0) break; // 연기 때문에 못 감
                
                sum += dfs(v, count + 1, visitedMask | (1 << v));
            }
        }

        // 여유 공간이 있을 때만 계산 결과를 64비트로 압축하여 저장
        if (state_cnt < HASH_SIZE) {
            long kv = ((long)key << 32) | sum;
            state_kv[state_cnt] = kv;
            next[state_cnt] = head[hash];
            head[hash] = state_cnt++;
        }

        return sum;
    }
}