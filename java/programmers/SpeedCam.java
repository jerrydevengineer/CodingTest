package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class SpeedCam {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int now = Integer.MIN_VALUE;
        for(int[] route : routes) {
            if(now < route[0]) {
                answer++;
                now = route[1];
            }
        }
        return answer;
    }
}
