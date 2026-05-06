package baekjoon;

import java.io.*;
import java.util.*;

public class BabyShark {

    static int N, M;
    static int[][] map;
    static int size;
    static int[] now;
    static Map<Integer, Set<int[]>> remains;
    static int[] dirr = {-1, 0, 1, 0};
    static int[] dirc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = 0;
        size = 2;
        remains = new HashMap<>();

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                int a = Integer.parseInt(st.nextToken());
                map[r][c] = a;
                if(a!=0 && a!=9) {
                    M++;
                    remains.computeIfAbsent(a, k -> new HashSet<>()).add(new int[]{r,c});
                }
                if(a==9) now = new int[]{r,c};
            }
        }

        int time = 0;
        while(M > 0) {
            for(int i = 0; i < size; i++) {
                List<int[]> temp = new ArrayList<>();
                for(int s = 1; s < size; s++) {
                    if(remains.get(s).size() != 0) {
                        for(int[] t : remains.get(s)) {
                            temp.add(t);
                        }
                    }
                }
                boolean stopped = findNearest();
            }
        }
    }

    public static boolean findNearest() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(now);
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dirr[i];
                int nc = cur[1] + dirc[i];
                if(nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] <= size) {
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
