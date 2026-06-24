package programmers;

import java.util.ArrayList;
import java.util.List;

public class SheepAndWolf {
    // 트리의 연결 상태를 저장할 인접 리스트
    ArrayList<Integer>[] childs;
    int[] nodeInfo;
    int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        nodeInfo = info;
        childs = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            childs[i] = new ArrayList<>();
        }

        // 단방향(부모 -> 자식) 간선 연결
        for (int[] edge : edges) {
            childs[edge[0]].add(edge[1]);
        }

        // 다음에 방문할 수 있는 노드들을 담을 리스트
        List<Integer> nextNodes = new ArrayList<>();
        // 루트 노드(0번)부터 탐색 시작
        nextNodes.add(0);

        // DFS 시작: (현재 노드, 현재까지의 양, 현재까지의 늑대, 다음에 갈 수 있는 노드 목록)
        dfs(0, 0, 0, nextNodes);

        return maxSheep;
    }

    private void dfs(int currentNode, int sheep, int wolf, List<Integer> nextNodes) {
        // 1. 현재 노드의 동물 파악
        if (nodeInfo[currentNode] == 0)
            sheep++;
        else
            wolf++;

        // 2. 늑대가 양보다 많거나 같아지면 잡아먹히므로 탐색 종료 (백트래킹)
        if (wolf >= sheep)
            return;

        // 3. 최대 양의 수 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 4. 다음 탐색을 위한 갈림길(상태) 업데이트
        // (기존의 갈 수 있었던 노드 목록을 복사)
        List<Integer> newNextNodes = new ArrayList<>(nextNodes);

        // 현재 방문한 노드는 '다음에 갈 노드' 목록에서 제거 (객체 삭제를 위해 Integer로 캐스팅)
        newNextNodes.remove(Integer.valueOf(currentNode));

        // 현재 노드의 자식 노드들을 '다음에 갈 노드' 목록에 추가
        for (int child : childs[currentNode]) {
            newNextNodes.add(child);
        }

        // 5. 갈 수 있는 모든 노드들에 대해 각각 재귀적으로 DFS 탐색
        // (순서에 따라 결과가 달라지므로 가능한 모든 순열을 탐색하게 됨)
        for (int nextNode : newNextNodes) {
            dfs(nextNode, sheep, wolf, newNextNodes);
        }
    }
}
