package programmers;

public class GatheringStickers {
    public int solution(int sticker[]) {
        int n = sticker.length;

        // 예외 처리: 스티커가 1장 또는 2장밖에 없을 때
        if (n == 1)
            return sticker[0];
        if (n == 2)
            return Math.max(sticker[0], sticker[1]);

        // Case 1: 첫 번째 스티커(인덱스 0)를 뜯는 경우
        // 배열의 길이를 n-1까지만 봅니다. (마지막 스티커는 못 뜯음)
        int[] dp1 = new int[n];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0]; // 첫 번째를 뜯었으니 두 번째는 못 뜯음 -> 첫 번째 값 유지

        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        // Case 2: 첫 번째 스티커(인덱스 0)를 뜯지 않는 경우
        // 배열을 1부터 끝(n-1)까지 봅니다.
        int[] dp2 = new int[n];
        dp2[0] = 0; // 첫 번째는 안 뜯음
        dp2[1] = sticker[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

        // 두 가지 케이스 중 더 큰 값을 반환
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}
