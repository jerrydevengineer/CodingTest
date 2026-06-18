package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ReturnToBase {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int[] dists = new int[n + 1];
        Arrays.fill(dists, -1);
        boolean[] visited = new boolean[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        visited[destination] = true;
        queue.add(destination);
        int dist = 0;
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                dists[cur] = dist;
                tempList.add(cur);
                visited[cur] = true;
            }

            for (Integer temp : tempList) {
                List<Integer> candidates = graph[temp];
                for (Integer candidate : candidates) {
                    if (!visited[candidate]) {
                        queue.add(candidate);
                    }
                }
            }
            dist++;
        }

        int i = 0;
        for (int source : sources) {
            answer[i] = dists[source];
            i++;
        }

        return answer;
    }
}
