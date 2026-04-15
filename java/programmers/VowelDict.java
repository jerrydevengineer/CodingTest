package programmers;

public class VowelDict {
    class Solution {

        static char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        static int index;

        public int solution(String word) {
            int max = 5;
            index = 0;
            for(int i = 1; i <= max; i++) {
                backtracking(vowels[i], 0, index, word);
            }
            return 1;
        }

        public void backtracking(char start, int length, int index, String target) {
//            if(target.equals(word)) return;

        }
    }
}
