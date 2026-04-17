package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LifeBoat {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0; // 가장 가벼운 사람의 인덱스
        int right = people.length - 1; // 가장 무거운 사람의 인덱스
        int boats = 0;

        while (left <= right) {
            // 가장 가벼운 사람과 무거운 사람이 같이 탈 수 있다면?
            if (people[left] + people[right] <= limit) {
                left++; // 가벼운 사람도 보트에 태움 (인덱스 이동)
            }
            // 무거운 사람은 무조건 보트에 탑승
            right--;
            boats++; // 보트 1대 출발
        }

        return boats;
    }
}
