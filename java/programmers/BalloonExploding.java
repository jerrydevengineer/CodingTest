package programmers;

public class BalloonExploding {
    public int solution(int[] a) {
        int n = a.length;

        // 풍선이 1개나 2개면 모두 최후까지 남길 수 있으므로 그대로 반환
        if (n <= 2)
            return n;

        // 1. 오른쪽 최솟값들을 미리 구해 배열에 저장 (우측에서 좌측으로 스캔)
        int[] rightMin = new int[n];
        rightMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        // 맨 왼쪽, 맨 오른쪽 풍선은 무조건 살아남음
        int answer = 2;

        // 왼쪽 최솟값을 추적할 변수 초기화 (인덱스 0의 값)
        int currentLeftMin = a[0];

        // 2. 인덱스 1부터 n-2까지 순회하며 생존 여부 판별
        for (int i = 1; i < n - 1; i++) {
            // 현재 풍선이 왼쪽 최솟값보다도 크고, 오른쪽 최솟값보다도 크다면 사망!
            if (a[i] > currentLeftMin && a[i] > rightMin[i + 1]) {
                // 살아남지 못하므로 패스
            } else {
                answer++; // 그 외의 경우는 모두 생존 가능
            }

            // 다음 풍선을 위해 왼쪽 최솟값 갱신
            currentLeftMin = Math.min(currentLeftMin, a[i]);
        }

        return answer;
    }
}
