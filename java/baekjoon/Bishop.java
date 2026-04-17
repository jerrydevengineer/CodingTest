package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bishop {

    static int N;
    static int[][] map;

    // O(1) 대각선 체크를 위한 배열
    static boolean[] diag1; // 우상향 대각선 (/)
    static boolean[] diag2; // 우하향 대각선 (\)

    // 흑/백 분리 리스트
    static List<int[]> blackPoss = new ArrayList<>();
    static List<int[]> whitePoss = new ArrayList<>();
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 대각선의 개수는 최대 2*N 개입니다.
        diag1 = new boolean[N * 2];
        diag2 = new boolean[N * 2];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 1) {
                    // 체스판의 (r+c)가 짝수면 흑색, 홀수면 백색으로 분리해서 담습니다.
                    if((r + c) % 2 == 0) {
                        blackPoss.add(new int[]{r, c});
                    } else {
                        whitePoss.add(new int[]{r, c});
                    }
                }
            }
        }

        int totalMax = 0; // 최종 정답

        // 1. 흑색 칸들만 백트래킹 진행
        max = 0;
        backtracking(blackPoss, 0, 0);
        totalMax += max;

        // 2. 백색 칸들만 백트래킹 진행
        max = 0;
        backtracking(whitePoss, 0, 0);
        totalMax += max;

        // 두 독립적인 결과의 합이 전체의 최댓값!
        System.out.println(totalMax);
    }

    // List를 매개변수로 받아 흑색/백색을 공통으로 처리합니다.
    static void backtracking(List<int[]> poss, int i, int count) {
        if(i == poss.size()) {
            max = Math.max(max, count);
            return;
        }

        int r = poss.get(i)[0];
        int c = poss.get(i)[1];

        // 1. 현재 자리에 안 두고 넘어가는 경우 (항상 실행)
        backtracking(poss, i + 1, count);

        // 2. 현재 자리에 둘 수 있어서 두는 경우
        // 두 대각선 모두에 다른 비숍이 없다면?
        if(!diag1[r + c] && !diag2[r - c + N - 1]) {

            // 대각선 방문 처리 (비숍 배치)
            diag1[r + c] = true;
            diag2[r - c + N - 1] = true;

            // 다음 자리 탐색
            backtracking(poss, i + 1, count + 1);

            // 원상 복구 (백트래킹)
            diag1[r + c] = false;
            diag2[r - c + N - 1] = false;
        }
    }
}
