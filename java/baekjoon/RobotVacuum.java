package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotVacuum {
    
    static int N, M;
    static int[][] map;
    // 0: 북, 1: 동, 2: 남, 3: 서
    static int[] dirR = {-1, 0, 1, 0}; 
    static int[] dirC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int heading = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;

        while(true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 청소한다.
            if(map[r][c] == 0) {
                map[r][c] = 2; // 청소 완료 표시 (-1도 좋지만 맵 밖으로 안 나가게 양수가 디버깅 시 유리합니다)
                res++;
            }

            boolean cleaned = false; // 주변 4칸 중 빈 칸이 있는지 확인하는 플래그
            
            // 2. 주변 4칸 탐색 (반시계 방향으로 4번 회전하며 앞쪽 칸 확인)
            for(int i = 0; i < 4; i++) {
                // 🚨 수정된 부분 1: 반시계 방향으로 90도 회전
                heading = (heading + 3) % 4; 
                
                int nR = r + dirR[heading];
                int nC = c + dirC[heading];

                // 바라보는 방향의 앞쪽 칸이 청소되지 않은 빈 칸인 경우 전진
                if(nR >= 0 && nC >= 0 && nR < N && nC < M && map[nR][nC] == 0) {
                    r = nR;
                    c = nC;
                    cleaned = true;
                    break; // 한 칸 전진 후 1번으로 돌아감
                }
            }

            // 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우 (cleaned가 false)
            if(!cleaned) {
                // 후진할 좌표 계산 (방향은 유지한 채 좌표만 반대로)
                int nR = r - dirR[heading];
                int nC = c - dirC[heading];

                // 🚨 수정된 부분 2: 격자 안이면서 '벽(1)이 아닐 때만' 후진 가능
                if(nR >= 0 && nC >= 0 && nR < N && nC < M && map[nR][nC] != 1) {
                    r = nR;
                    c = nC;
                } else {
                    // 뒤쪽 칸이 벽이라 후진할 수 없다면 작동 정지
                    break;
                }
            }
        }

        System.out.println(res);
    }
}
