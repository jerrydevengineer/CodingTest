package programmers;

import java.util.HashMap;
import java.util.Map;

public class MLMToothbrushSell {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        // 정답(각 판매원의 이익)을 담을 배열
        int[] profit = new int[n];

        // 내 부모(추천인)의 인덱스를 저장할 배열
        int[] parent = new int[n];

        // 이름(String)을 인덱스(int)로 빠르게 찾기 위한 맵
        Map<String, Integer> indexMap = new HashMap<>();

        // 1. 모든 판매원의 이름을 인덱스로 매핑
        for (int i = 0; i < n; i++) {
            indexMap.put(enroll[i], i);
        }

        // 2. 부모(추천인) 관계를 인덱스로 배열에 저장
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1; // 추천인이 없으면(센터 직속) -1로 표시
            } else {
                parent[i] = indexMap.get(referral[i]);
            }
        }

        // 3. 판매 기록을 하나씩 처리
        for (int i = 0; i < seller.length; i++) {
            int curr = indexMap.get(seller[i]); // 현재 이익을 얻은 사람의 인덱스
            int money = amount[i] * 100; // 발생한 총 이익 (1개당 100원)

            // 💡 핵심: 부모가 존재하고, 상납할(남은) 돈이 1원 이상일 때만 위로 올라감
            while (curr != -1 && money > 0) {
                int tax = money / 10; // 10%는 부모에게 상납
                int mine = money - tax; // 90%는 내가 가짐

                profit[curr] += mine; // 내 이익금 누적

                curr = parent[curr]; // 부모 노드로 이동
                money = tax; // 부모가 처리해야 할 돈은 내가 떼어준 10%
            }
        }

        return profit;
    }
}
