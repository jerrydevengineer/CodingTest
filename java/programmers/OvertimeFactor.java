package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class OvertimeFactor {
    public long solution(int n, int[] works) {
        // 1. 내림차순 정렬되는 우선순위 큐 (Max Heap) 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 2. 모든 작업량을 큐에 삽입
        for (int work : works) {
            pq.offer(work);
        }

        // 3. n시간 동안, 가장 큰 작업량을 꺼내서 1 줄이고 다시 넣기
        while (n > 0) {
            int maxWork = pq.poll();

            // 가장 큰 작업량이 0이면 남은 일이 없는 것이므로 즉시 종료
            if (maxWork == 0) break;

            pq.offer(maxWork - 1);
            n--;
        }

        // 4. 남은 작업량들의 제곱의 합 계산
        long answer = 0;
        while (!pq.isEmpty()) {
            long work = pq.poll();
            answer += work * work; // int 범위를 넘을 수 있으므로 long 사용
        }

        return answer;
    }
}
