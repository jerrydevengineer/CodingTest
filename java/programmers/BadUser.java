package programmers;

import java.util.HashSet;
import java.util.Set;

public class BadUser {
    // 중복된 제재 아이디 목록을 걸러낼 HashSet
    Set<Set<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        // banned_id의 '*'를 정규식 '.'으로 변환 ("fr*d*" -> "fr.d.")
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        // DFS 탐색 시작 (현재 고른 아이디들을 담을 Set, 유저목록, 제재목록, 현재 제재목록 인덱스)
        dfs(new HashSet<>(), user_id, banned_id, 0);

        // 최종적으로 걸러진 고유한 조합의 개수가 정답
        return resultSet.size();
    }

    private void dfs(Set<String> currentSet, String[] user_id, String[] banned_id, int bIdx) {
        // 모든 banned_id에 대해 매핑을 끝냈다면
        if (bIdx == banned_id.length) {
            // 결과 Set에 현재 조합을 복사해서 넣음 (깊은 복사)
            resultSet.add(new HashSet<>(currentSet));
            return;
        }

        // 현재 매핑해야 할 불량 사용자 패턴
        String regex = banned_id[bIdx];

        // 모든 유저 아이디를 순회하면서 매핑 가능한지 확인
        for (String user : user_id) {
            // 아직 이번 조합에서 선택되지 않은 유저이고, 패턴에 일치한다면
            if (!currentSet.contains(user) && user.matches(regex)) {
                // 선택 (방문 처리)
                currentSet.add(user);

                // 다음 불량 사용자 매핑을 위해 DFS 재귀 호출
                dfs(currentSet, user_id, banned_id, bIdx + 1);

                // 백트래킹 (다른 경우의 수를 찾기 위해 선택 해제)
                currentSet.remove(user);
            }
        }
    }
}
