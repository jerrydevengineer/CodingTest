package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RouteOnGrid {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1. 오버플로우를 방지하기 위한 파스칼의 삼각형(조합) DP 테이블 미리 세팅
        // 최대 이동 횟수는 (15-1) + (15-1) = 28번이므로 30칸 정도면 충분합니다.
        int[][] comb = new int[31][31];
        for (int i = 0; i <= 30; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        if (K == 0) {
            // [K가 없을 때] (1,1)에서 (N,M)까지
            // 총 이동 횟수: (N-1) + (M-1)
            // 그 중 아래로 가는 횟수: N-1
            int totalSteps = (N - 1) + (M - 1);
            int downSteps = N - 1;
            
            System.out.println(comb[totalSteps][downSteps]);
            
        } else {
            // [K가 있을 때] 
            // K의 0-indexed 좌표 구하기 (r: 아래로 이동해야 할 횟수, c: 오른쪽으로 이동해야 할 횟수)
            int r = (K - 1) / M;
            int c = (K - 1) % M;

            // 경로 1: 시작점 -> K
            int stepsToK = r + c;
            int downToK = r;
            long path1 = comb[stepsToK][downToK];

            // 경로 2: K -> 도착점
            // 도착점까지 남은 이동 횟수: 전체 이동 횟수에서 K까지 온 횟수를 뺌
            int remainR = (N - 1) - r;
            int remainC = (M - 1) - c;
            int stepsToEnd = remainR + remainC;
            int downToEnd = remainR;
            long path2 = comb[stepsToEnd][downToEnd];

            // 두 사건이 연속해서 일어나야 하므로 '곱하기' 연산
            System.out.println(path1 * path2);
        }
    }
}
