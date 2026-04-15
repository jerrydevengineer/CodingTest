package programmers;

import java.util.Arrays;

public class GymClothes {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        // 1. 인덱스 초과 에러 방지를 위해 크기를 n + 2 로 설정
        int[] s = new int[n + 2];
        Arrays.fill(s, 1);

        for(int rsv : reserve) {
            s[rsv]++;
        }

        for(int lst : lost) {
            s[lst]--;
        }

        // 2. 1번 학생부터 n번 학생까지 탐색
        for(int i = 1; i <= n; i++) {
            if(s[i] == 0) {
                // 🚨 중요: 반드시 앞번호(i-1)부터 먼저 확인해야 합니다!
                if(s[i-1] > 1) {
                    s[i]++;
                    s[i-1]--;
                }
                // 앞번호가 없으면 뒷번호(i+1) 확인
                else if(s[i+1] > 1) {
                    s[i]++;
                    s[i+1]--;
                }
            }
        }

        // 3. 실제 존재하는 1번부터 n번 학생까지만 체육복 보유 여부 확인
        for(int i = 1; i <= n; i++) {
            if(s[i] > 0) {
                answer++;
            }
        }

        return answer;
    }
}
