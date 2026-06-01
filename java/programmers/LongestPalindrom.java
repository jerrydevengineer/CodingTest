package programmers;

public class LongestPalindrom {
    public int solution(String s) {
        // 예외 처리: 길이가 1 이하면 자기 자신이 최대 팰린드롬
        if (s == null || s.length() < 2) {
            return s.length();
        }

        int maxLength = 1;

        // 문자열을 순회하며 각 위치를 중심으로 지정
        for (int i = 0; i < s.length() - 1; i++) {
            // 1. 홀수 길이 팰린드롬 검사 (중심이 i)
            int len1 = expandAroundCenter(s, i, i);

            // 2. 짝수 길이 팰린드롬 검사 (중심이 i와 i+1 사이)
            int len2 = expandAroundCenter(s, i, i + 1);

            // 두 경우 중 더 긴 값을 구하고, 기존의 최대 길이와 비교하여 갱신
            int maxAtCurrentCenter = Math.max(len1, len2);
            maxLength = Math.max(maxLength, maxAtCurrentCenter);
        }

        return maxLength;
    }

    // 중심(left, right)을 기준으로 양옆으로 확장하며 팰린드롬 길이를 구하는 헬퍼 메서드
    private int expandAroundCenter(String s, int left, int right) {
        // 인덱스가 범위 안에 있고, 양쪽 문자가 같을 때까지 계속 확장
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // while문이 깨졌을 때의 길이를 반환
        // 실제 팰린드롬의 인덱스는 (left + 1) 부터 (right - 1) 까지임
        // 길이는 (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}
