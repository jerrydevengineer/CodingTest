package programmers;

import java.util.*;

class TransWord {
    public int solution(String begin, String target, String[] words) {
        // 1. target 단어가 words 배열에 없다면 절대 변환할 수 없으므로 0 반환
        boolean containsTarget = false;
        for (String word : words) {
            if (word.equals(target)) {
                containsTarget = true;
                break;
            }
        }
        if (!containsTarget) return 0;

        // 2. BFS 탐색을 위한 큐와 방문 처리 배열 초기화
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        // 3. 시작 단어를 큐에 삽입 (이동 횟수 0)
        queue.offer(new Node(begin, 0));

        // 4. BFS 탐색 시작
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 큐에서 꺼낸 단어가 target과 일치하면 그때의 이동 횟수 반환
            if (current.word.equals(target)) {
                return current.steps;
            }

            // 현재 단어에서 1글자만 다른 단어를 찾아 큐에 삽입
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(current.word, words[i])) {
                    visited[i] = true; // 방문 처리
                    queue.offer(new Node(words[i], current.steps + 1));
                }
            }
        }

        // 큐가 다 빌 때까지 target을 만들지 못했다면 0 반환
        return 0;
    }

    // 두 단어가 정확히 "한 글자"만 다른지 판별하는 헬퍼 메서드
    private boolean canConvert(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
            // 최적화: 2글자 이상 다르면 더 이상 검사할 필요 없이 false 반환
            if (diffCount > 1) return false;
        }
        return diffCount == 1;
    }

    // 큐에 넣을 데이터를 묶어주는 내부 클래스
    class Node {
        String word;
        int steps;

        Node(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
}