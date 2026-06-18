package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JewelShopping {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        for (String s : gems) {
            set.add(s);
        }
        int x = set.size();
        if (x == 1)
            return new int[] {1, 1};
        int l = 0, r = 0, min = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        while (r < gems.length && l < gems.length) {
            while (map.size() == x) {
                if (r - l < min) {
                    min = r - l;
                    answer[0] = l + 1;
                    answer[1] = r + 1;
                }
                map.put(gems[l], map.get(gems[l]) - 1);
                if (map.get(gems[l]) == 0)
                    map.remove(gems[l]);
                l++;
            }
            r++;
        }
        return answer;
    }
}
