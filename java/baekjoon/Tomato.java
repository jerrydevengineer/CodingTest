package baekjoon;

import java.io.*;
import java.util.*;

public class Tomato {

    static int M, N, H;
    static int[][][] karton;
    static int[] dirs = {0, 1, 0, -1, 0};
    static List<int[]> dailyList;
    static int day, remain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        karton = new int[H][N][M];
        dailyList = new ArrayList<>();
        remain = 0;
        day = 0;

        for(int h = 0; h < H; h++) {
            // 🚨 1번 오류 수정: r은 층과 무관하게 무조건 0부터 N-1까지
            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < M; c++) {
                    int cur = Integer.parseInt(st.nextToken());
                    karton[h][r][c] = cur;
                    if(cur == 1) dailyList.add(new int[]{h, r, c});
                    if(cur == 0) remain++;
                }
            }
        }

        while(!dailyList.isEmpty()) {
            List<int[]> tomorrow = new ArrayList<>();
            for(int[] tomato : dailyList) {
                List<int[]> temp = spread(tomato[0], tomato[1], tomato[2]);
                // NPE 방지를 위해 temp 자체를 추가하도록 안전하게 수정
                tomorrow.addAll(temp);
            }
            if(tomorrow.isEmpty()) break;
            day++;
            dailyList = tomorrow;
        }

        // 🚨 2번 오류 수정: 남은 토마토가 있으면 -1, 다 익었으면 day 출력
        if (remain > 0) {
            System.out.println(-1);
        } else {
            System.out.println(day);
        }
    }

    static List<int[]> spread(int h, int r, int c) {
        List<int[]> temp = new ArrayList<>();
        // 이미 1인 것만 들어오므로 굳이 return null 할 필요가 없습니다. (NPE 위험 제거)

        // 1. 상하좌우(2D) 탐색
        for(int i = 0; i < 4; i++) {
            int nr = r + dirs[i];
            int nc = c + dirs[i+1];
            if(checkValid(h, nr, nc)) {
                temp.add(new int[]{h, nr, nc});
                karton[h][nr][nc] = 1;
                remain--;
            }
        }

        // 2. 위층 탐색
        int nh = h + 1;
        if(checkValid(nh, r, c)) {
            temp.add(new int[]{nh, r, c});
            karton[nh][r][c] = 1;
            remain--;
        }

        // 3. 아래층 탐색
        nh = h - 1;
        if(checkValid(nh, r, c)) {
            temp.add(new int[]{nh, r, c});
            karton[nh][r][c] = 1;
            remain--;
        }

        return temp;
    }

    static boolean checkValid(int h, int r, int c) {
        return h >= 0 && r >= 0 && c >= 0 && h < H && r < N && c < M && karton[h][r][c] == 0;
    }
}
