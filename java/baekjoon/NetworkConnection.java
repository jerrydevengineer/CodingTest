package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class NetworkConnection {

    static int V, E;
    static class Edge {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static List<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        for(int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));
        }

        visited = new boolean[V+1];

        int totalCost = 0;
        int usedNodes = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.node]) continue;
            visited[cur.node] = true;
            totalCost += cur.cost;
            usedNodes++;

            if(usedNodes == V) break;

            for(Edge next : graph[cur.node]) {
                if(!visited[next.node]) {
                    pq.add(new Edge(next.node, next.cost));
                }
            }
        }

        System.out.println(totalCost);
    }
}
