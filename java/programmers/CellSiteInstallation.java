package programmers;

public class CellSiteInstallation {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int position = 1; // 현재 탐색 중인 아파트 번호
        int stationIdx = 0; // 설치된 기지국(stations) 배열의 인덱스
        int coverage = 2 * w + 1; // 기지국 1개가 커버하는 총 범위

        while (position <= n) {
            // 1. 현재 위치가 기존에 설치된 기지국의 전파 범위 안에 있는 경우
            // (stationIdx가 배열 범위를 넘지 않았는지 먼저 확인해야 에러가 안 납니다)
            if (stationIdx < stations.length && position >= stations[stationIdx] - w) {
                // 해당 기지국의 전파가 끝나는 바로 다음 아파트로 탐색 위치 점프
                position = stations[stationIdx] + w + 1;
                stationIdx++; // 다음 기지국으로 타겟 변경
            }
            // 2. 현재 위치가 전파 범위 밖인 경우 (여기에 새로 설치해야 함)
            else {
                answer++; // 기지국 설치!

                // 새로 기지국을 설치했으니, 그 커버리지 길이만큼 앞으로 껑충 뜀
                // 질문자님이 작성하셨던 (2 * w + 1)의 나눗셈 논리가 여기서 한 번의 점프로 적용되는 것입니다.
                position += coverage;
            }
        }

        return answer;
    }
}
