package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SharkSchool {

    static int N;
    static int total;
//    static List<Integer>[] students;
    static Set<Integer>[] students;
    static int[][] map;
    static int[] dirs = {0, -1, 0, 1, 0};
    static int[] seq;
    static int satisfaction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        total = N * N;
        map = new int[N+1][N+1];
        seq = new int[total];

        students = new HashSet[total+1];
        for(int i = 0; i <= total; i++) {
            students[i] = new HashSet<>();
        }

        for(int i = 1; i <= total; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            seq[i-1] = n;
            for(int j = 0; j < 4; j++) {
                int s = Integer.parseInt(st.nextToken());
                students[n].add(s);
            }
        }

//        비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
//        1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.

        for(int student : seq) {
            List<int[]> poss = findFirst(student);
            if(poss.size() > 1) {
                poss = findSecond(poss);
                if(poss.size() > 1) {
                    int[] res = findThird(poss);
                    map[res[0]][res[1]] = student;
                } else {
                    int[] res = poss.get(0);
                    map[res[0]][res[1]] = student;
                }
            } else {
                int[] res = poss.get(0);
                map[res[0]][res[1]] = student;
            }
        }

        printMap();

        satisfaction = 0;
        sumSatisfaction();
        System.out.println(satisfaction);
    }

    public static void sumSatisfaction() {
        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= N; c++) {
                int student = map[r][c];
                int temp = 0;
                for(int i = 0; i < 4; i++) {
                    int nr = r + dirs[i];
                    int nc = c + dirs[i+1];
                    if(nr >= 1 && nc >= 1 && nr <= N && nc <= N && students[student].contains(map[nr][nc])) {
                        temp++;
                    }
                }
                if(temp == 0) continue;
                satisfaction += Math.pow(10, temp-1);
            }
        }
    }

    public static List<int[]> findFirst(int student) {
        int max = 0;
        List<int[]> res = new ArrayList<>();
        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= N; c++) {
                if (map[r][c] != 0) continue;
                int temp = 0;
                for(int i = 0; i < 4; i++) {
                    int nr = r + dirs[i];
                    int nc = c + dirs[i+1];
                    if(nr >= 1 && nc >= 1 && nr <= N && nc <= N && students[student].contains(map[nr][nc])) {
                        temp++;
                    }
                }
                if(temp > max) {
                    res.clear();
                    max = temp;
                    res.add(new int[]{r, c});
                } else if(temp == max) {
                    res.add(new int[]{r, c});
                }
            }
        }
        return res;
    }

    public static List<int[]> findSecond(List<int[]> candidates) {
        int max = 0;
        List<int[]> res = new ArrayList<>();
        for(int[] candidate : candidates) {
            int temp = 0;
            int r = candidate[0];
            int c = candidate[1];
            for(int i = 0; i < 4; i++) {
                int nr = r + dirs[i];
                int nc = c + dirs[i+1];
                if(nr >= 1 && nc >= 1 && nr <= N && nc <= N && map[nr][nc] == 0) {
                    temp++;
                }
            }
            if(temp > max) {
                res.clear();
                max = temp;
                res.add(new int[]{r, c});
            } else if(temp == max) {
                res.add(new int[]{r, c});
            }
        }
        return res;
    }

    public static int[] findThird(List<int[]> candidates) {
        candidates.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        return candidates.get(0);
    }

    public static void printMap() {
        System.out.println("=== 맵 상태 확인 ===");
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================");
    }
}
