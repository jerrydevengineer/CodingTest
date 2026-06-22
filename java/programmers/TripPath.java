package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class TripPath {
    boolean[] visited;
    ArrayList<String> allRoutes;

    public String[] solution(String[][] tickets) {
        // 티켓의 사용 여부를 체크할 배열
        visited = new boolean[tickets.length];
        allRoutes = new ArrayList<>();

        // DFS 탐색 시작 (사용된 티켓 0장, 시작 공항 "ICN", 현재까지의 경로 "ICN")
        dfs(0, "ICN", "ICN", tickets);

        // 완성된 모든 경로(알파벳 순서)를 오름차순 정렬
        Collections.sort(allRoutes);

        // 가장 알파벳 순서가 빠른 첫 번째 경로를 잘라서 배열로 반환
        return allRoutes.get(0).split(" ");
    }

    public void dfs(int depth, String curr, String path, String[][] tickets) {
        // 모든 티켓을 다 사용했다면(종료 조건) 완성된 경로를 리스트에 추가
        if (depth == tickets.length) {
            allRoutes.add(path);
            return;
        }

        // 전체 티켓을 돌면서 다음에 갈 수 있는 공항을 찾음
        for (int i = 0; i < tickets.length; i++) {
            // 아직 사용하지 않은 티켓이고, 현재 공항에서 출발하는 티켓이라면
            if (!visited[i] && tickets[i][0].equals(curr)) {
                visited[i] = true; // 티켓 사용 처리

                // 다음 공항으로 이동 (재귀 호출)
                // 경로에 띄어쓰기를 포함해 다음 공항을 붙여줌 (예: "ICN B")
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);

                // 백트래킹: 다른 경로도 탐색해보기 위해 티켓 사용 취소
                visited[i] = false;
            }
        }
    }
}
