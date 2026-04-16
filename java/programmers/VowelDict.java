package programmers;

import java.util.ArrayList;
import java.util.List;

public class VowelDict {
    class Solution {

        public int solution(String word) {
            int answer = 0;

            // 자릿수별 가중치 (각 자리가 바뀔 때 건너뛰는 단어의 수)
            int[] weights = {781, 156, 31, 6, 1};
            String vowels = "AEIOU";

            for (int i = 0; i < word.length(); i++) {
                // 현재 문자가 모음 중 몇 번째인지 인덱스 구하기 (A:0, E:1, I:2, O:3, U:4)
                int index = vowels.indexOf(word.charAt(i));

                // 인덱스 * 가중치 + 1(자기 자신)
                answer += index * weights[i] + 1;
            }

            return answer;
        }

//
//        List<String> dictionary = new ArrayList<>();
//        String[] vowels = {"A", "E", "I", "O", "U"};
//
//        public int solution(String word) {
//            // 빈 문자열부터 시작해서 사전을 모두 완성함
//            dfs("", 0);
//
//            // indexOf는 리스트에서 해당 단어의 인덱스(0부터 시작)를 반환함
//            // 맨 처음 들어간 빈 문자열("")이 0번 인덱스이므로, "A"는 1번이 되어 문제 조건과 완벽히 일치!
//            return dictionary.indexOf(word);
//        }
//
//        void dfs(String str, int len) {
//            // 단어 목록에 현재 문자열 추가
//            dictionary.add(str);
//
//            // 길이가 5가 되면 더 이상 깊이 들어가지 않고 리턴
//            if (len == 5) return;
//
//            // 5개의 모음을 하나씩 붙여가며 다음 깊이로 재귀 호출
//            for (int i = 0; i < 5; i++) {
//                dfs(str + vowels[i], len + 1);
//            }
//        }
    }
}
