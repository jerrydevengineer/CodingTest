package programmers;

public class TargetNumber {
    public int solution(int[] numbers, int target) {
        // 백트래킹의 최종 반환값이 곧 정답이 됩니다.
        return backtracking(0, numbers, target, 0);
    }

    public int backtracking(int index, int[] numbers, int target, int temp) {
        // 인덱스가 배열 끝까지 도달했을 때
        if (index == numbers.length) {
            // 타겟 넘버를 만들었다면 1을 반환, 아니면 0을 반환
            if (temp == target) {
                return 1;
            }
            return 0;
        }

        // 현재 숫자를 더한 경우의 수 + 뺀 경우의 수를 합산하여 반환
        return backtracking(index + 1, numbers, target, temp + numbers[index])
                + backtracking(index + 1, numbers, target, temp - numbers[index]);
    }
}
