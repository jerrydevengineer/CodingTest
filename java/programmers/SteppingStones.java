package programmers;

import java.util.Arrays;

public class SteppingStones {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        // 1. 바위를 순서대로 방문하기 위해 오름차순 정렬
        Arrays.sort(rocks);

        // 2. 이분 탐색 범위 설정 (최소 거리는 1, 최대 거리는 distance)
        long left = 1;
        long right = distance;

        while (left <= right) {
            long mid = (left + right) / 2; // 우리가 가정하는 '바위 사이의 최소 거리'

            int removedRocks = 0; // 제거한 바위의 수
            int currentPos = 0; // 현재 위치 (출발지점 0부터 시작)

            // 3. 각 바위를 순회하며 거리 측정
            for (int rock : rocks) {
                if (rock - currentPos < mid) {
                    // 바위 사이의 거리가 가정한 최솟값(mid)보다 작으면 제거!
                    removedRocks++;
                } else {
                    // 거리가 충분하다면 바위를 살려두고, 현재 위치를 해당 바위로 이동
                    currentPos = rock;
                }
            }

            // 4. 마지막으로 살려둔 바위와 도착지점(distance) 사이의 거리 확인
            if (distance - currentPos < mid) {
                removedRocks++;
            }

            // 5. 이분 탐색 조건 분기
            if (removedRocks > n) {
                // n개보다 더 많이 제거해야 한다면 -> 가정한 거리(mid)가 너무 김 -> 거리를 줄임
                right = mid - 1;
            } else {
                // n개 이하로 제거해서 조건을 만족했다면 -> 정답 후보에 올리고 거리를 더 늘려봄
                answer = (int) mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}
