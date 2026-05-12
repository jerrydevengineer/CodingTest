package programmers;

import java.util.ArrayList;
import java.util.List;

public class BestSet {
    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};
        List<Integer> res = new ArrayList<>();
        int r = s % n;
        int q = s / n;
        for(int i = 0; i < n - r; i++) {
            res.add(q);
        }
        q++;
        for(int i = 0; i < r; i++) {
            res.add(q);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
