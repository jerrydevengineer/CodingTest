package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MST {

    static int V, E;
    static int[] parent;
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        for(int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        Collections.sort(edges, (e1, e2) -> e1.cost - e2.cost);

        int totalCost = 0;
        int usedEdges = 0;

        for(Edge e : edges) {
            if(union(e.from, e.to)) {
                totalCost += e.cost;
                usedEdges++;
            }

            if(usedEdges == V - 1) break;
        }

        System.out.println(totalCost);
    }

    public static int find(int i) {
        if(i == parent[i]) return i;

        return parent[i] = find(parent[i]);
    }

    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        parent[px] = py;
        return true;
    }
}
