package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DragonCurve {

    // 100x100 격자이므로 점의 좌표는 0~100까지 존재합니다 (크기 101)
    static boolean[][] map = new boolean[101][101];

    // 방향: 0(우), 1(상), 2(좌), 3(하)
    // ※ 문제에서 y축이 아래로 갈수록 증가(↓)한다고 했으므로 1(상)은 y가 감소(-1)합니다.
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            drawDragonCurve(x, y, d, g);
        }

        // 모든 드래곤 커브를 그린 후, 1x1 정사각형의 개수를 셉니다.
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 네 꼭짓점이 모두 true(드래곤 커브의 일부)인지 확인
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    // 드래곤 커브를 생성하고 맵에 그리는 메서드
    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d); // 0세대 시작 방향 추가

        // 세대(g)만큼 반복하며 방향 리스트를 확장
        for (int i = 1; i <= g; i++) {
            // "뒤집어서 +1" 규칙 적용
            for (int j = dirs.size() - 1; j >= 0; j--) {
                dirs.add((dirs.get(j) + 1) % 4);
            }
        }

        // 시작점 맵에 표시
        map[y][x] = true;

        // 완성된 방향 리스트를 순회하며 맵에 커브 그리기
        for (int dir : dirs) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }
}
