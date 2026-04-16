package baekjoon;

import java.io.*;
import java.util.*;

public class MazeExplore {

    static int[][] map;
    static int[][] dist;
    static int[] dirs = {0, 1, 0, -1, 0};
    static class Node implements Comparable<Node> {
        int r;
        int c;
        int cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            String s = br.readLine();
            char[] chars = s.toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
            }
        }

        printMap();

        dist[0][0] = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 1));
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(curr.cost > dist[curr.r][curr.c]) continue;
            if(curr.r == N - 1 && curr.c == M - 1) {
                System.out.println(curr.cost);
                break;
            }
            for(int i = 0; i < 4; i++) {
                int nr = curr.r + dirs[i];
                int nc = curr.c + dirs[i+1];
                if(checkValid(nr, nc)) {
                    int newCost = curr.cost + 1;
                    if(dist[nr][nc] > newCost) {
                        dist[nr][nc] = newCost;
                        pq.add(new Node(nr, nc, newCost));
                    }
                }
            }
        }
    }

    public static boolean checkValid(int r, int c) {
        if(r >= 0 && c >= 0 && r < map.length && c < map[0].length && map[r][c] == 1) return true;
        return false;
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


//import java.io.*;
//import java.util.*;
//
//public class Main {
//    static int[][] map;
//    static int[] dirs = {0, 1, 0, -1, 0};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//
//        for(int i = 0; i < N; i++) {
//            String s = br.readLine();
//            for(int j = 0; j < M; j++) {
//                map[i][j] = s.charAt(j) - '0';
//            }
//        }
//
//        Queue<int[]> q = new ArrayDeque<>();
//
//        q.offer(new int[]{0, 0});
//
//        while(!q.isEmpty()) {
//            int[] curr = q.poll();
//            int r = curr[0];
//            int c = curr[1];
//
//            if (r == N - 1 && c == M - 1) {
//                System.out.println(map[r][c]);
//                break;
//            }
//
//            for(int i = 0; i < 4; i++) {
//                int nr = r + dirs[i];
//                int nc = c + dirs[i+1];
//
//                if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 1) {
//                    map[nr][nc] = map[r][c] + 1;
//                    q.offer(new int[]{nr, nc});
//                }
//            }
//        }
//    }
//}