package programmers;

import java.util.Arrays;

public class ConnIslands {

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    // 각 섬의 부모(그룹 대표)를 저장할 배열
    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        // 1. 간선 배열 초기화 및 비용 기준 오름차순 정렬
        Edge[] edges = new Edge[costs.length];
        for(int i = 0; i < costs.length; i++) {
            edges[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
        }
        Arrays.sort(edges);

        // 2. 부모 배열 초기화 (처음엔 자기 자신이 부모)
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 3. 크루스칼 알고리즘 수행
        for(Edge e : edges) {
            // 두 섬의 최상위 부모가 다를 때만 다리 건설 (사이클 방지)
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                answer += e.cost;
            }
        }

        return answer;
    }

    // 최상위 부모를 찾는 메서드 (경로 압축 최적화 적용)
    static int find(int i) {
        if (parent[i] == i) return i;
        // 재귀를 빠져나오면서 부모를 최상위 부모로 갱신하여 탐색 속도 향상
        return parent[i] = find(parent[i]);
    }

    // 두 그룹을 하나로 합치는 메서드
    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        // 더 작은 번호를 부모로 삼도록 합침
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}
