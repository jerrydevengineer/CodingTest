package programmers;

import java.util.PriorityQueue;

public class ShuttleBus {
    public String solution(int n, int t, int m, String[] timetable) {
        // 1. 모든 크루의 도착 시간을 분(minute) 단위로 변환하여 우선순위 큐에 삽입 (자동 오름차순 정렬)
        PriorityQueue<Integer> crewQueue = new PriorityQueue<>();
        for (String time : timetable) {
            String[] split = time.split(":");
            int minutes = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            crewQueue.add(minutes);
        }

        int shuttleTime = 9 * 60; // 첫 셔틀 도착 시간 (09:00)
        int lastBoardTime = 0; // 마지막으로 버스에 탑승한 크루의 시간
        int boardedCount = 0; // 현재 버스에 탑승한 인원 수

        // 2. 셔틀버스 운행 시뮬레이션 (n번 운행)
        for (int i = 0; i < n; i++) {
            boardedCount = 0; // 새 버스가 올 때마다 탑승 인원 초기화

            // 대기열에 크루가 있고, 그 크루가 버스 도착 시간 이전에 왔으며, 버스에 자리가 있다면 탑승
            while (!crewQueue.isEmpty() && crewQueue.peek() <= shuttleTime && boardedCount < m) {
                lastBoardTime = crewQueue.poll();
                boardedCount++;
            }

            // 마지막 버스가 아니라면 다음 버스 도착 시간으로 갱신
            if (i < n - 1) {
                shuttleTime += t;
            }
        }

        // 3. 콘의 도착 시간 결정
        int conTime = 0;

        // Case 1: 마지막 버스에 자리가 남는 경우 -> 마지막 버스 도착 시간에 딱 맞춰 온다.
        if (boardedCount < m) {
            conTime = shuttleTime;
        }
        // Case 2: 마지막 버스가 꽉 찬 경우 -> 마지막에 탄 사람보다 1분 일찍 온다.
        else {
            conTime = lastBoardTime - 1;
        }

        // 4. 계산된 분(minute)을 다시 "HH:MM" 포맷의 문자열로 변환
        return String.format("%02d:%02d", conTime / 60, conTime % 60);
    }
}
