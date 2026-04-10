package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LadderControl {

    static int N, M, H;
    static boolean[][] map;
    static boolean isFinished = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // map[h][c] = true : h번 높이에서 c번과 c+1번 세로선이 연결됨을 의미
        map = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = true;
        }

        // 추가할 가로선의 개수(target)를 0부터 3까지 늘려가며 탐색
        for (int target = 0; target <= 3; target++) {
            backtracking(1, 1, 0, target);
            if (isFinished) {
                System.out.println(target);
                return;
            }
        }

        // 3개까지 확인했는데도 불가능하다면 -1
        System.out.println("-1");
    }

    // 백트래킹 (조합)
    // h: 현재 탐색 중인 높이, c: 현재 탐색 중인 세로선, depth: 현재까지 추가한 가로선 수, target: 목표 가로선 수
    public static void backtracking(int h, int c, int depth, int target) {
        if (isFinished) return;

        // 목표한 개수만큼 가로선을 추가했다면 사다리 결과 검증
        if (depth == target) {
            if (check()) {
                isFinished = true;
            }
            return;
        }

        // 높이 h부터 끝까지 탐색
        for (int i = h; i <= H; i++) {
            // 같은 높이(h)일 때는 전달받은 c부터, 다음 높이로 넘어가면 1번 세로선부터 탐색
            int startC = (i == h) ? c : 1;

            // N-1번 세로선까지만 가로선을 우측(N번 방향)으로 그을 수 있음
            for (int j = startC; j < N; j++) {
                // 현재 위치에 가로선을 놓을 수 있는지 검사 (자신, 왼쪽, 오른쪽에 선이 없어야 함)
                if (!map[i][j] && !map[i][j - 1] && !map[i][j + 1]) {
                    
                    // 가로선 놓기 (상태 변화)
                    map[i][j] = true;
                    
                    // 연속해서 가로선을 놓을 수 없으므로 다음 탐색은 j + 2부터
                    backtracking(i, j + 2, depth + 1, target);
                    
                    // 가로선 제거 (원상 복구)
                    map[i][j] = false;
                }
            }
        }
    }

    // i번 세로선이 i번으로 도착하는지 사다리를 타보는 로직
    public static boolean check() {
        for (int i = 1; i <= N; i++) {
            int curr = i; // 현재 위치한 세로선 번호
            
            for (int h = 1; h <= H; h++) {
                // 1. 내 위치에서 우측으로 가는 선이 있다면 우측으로 이동
                if (map[h][curr]) {
                    curr++;
                } 
                // 2. 내 바로 왼쪽 세로선에서 나에게 오는 선이 있다면 좌측으로 이동
                else if (map[h][curr - 1]) {
                    curr--;
                }
                // 선이 없으면 그냥 아래(다음 h)로 직진 (for문 진행)
            }
            
            // 끝까지 내려왔는데 출발한 번호(i)와 다르다면 실패
            if (curr != i) return false;
        }
        
        return true;
    }
}
