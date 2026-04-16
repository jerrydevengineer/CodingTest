package baekjoon;

import java.io.*;
import java.util.*;

public class DFSandBFS {

    static boolean[] visitedD;
    static boolean[] visitedB;
    static List<Integer>[] graph;
    static List<Integer> routeD, routeB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for(int i = 0; i <= N; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }

        visitedB = new boolean[N+1];
        visitedD = new boolean[N+1];
        routeB = new ArrayList<>();
        routeD = new ArrayList<>();

        dfs(V);
        bfs(V);

        for(int d : routeD) {
            bw.write(d + " ");
        }
        bw.flush();
        System.out.println();
        for(int b : routeB) {
            bw.write(b + " ");
        }
        bw.flush();
        bw.close();
    }

    static void dfs(int V) {
        visitedD[V] = true;
        routeD.add(V);
        for(int n : graph[V]) {
            if(!visitedD[n]) dfs(n);
        }
    }

    static void bfs(int V) {
        visitedB[V] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(V);
        routeB.add(V);
        while(!q.isEmpty()) {
            int n = q.poll();
            for(int i : graph[n]) {
                if(!visitedB[i]) {
                    visitedB[i] = true;
                    q.offer(i);
                    routeB.add(i);
                }
            }
        }
    }
}
