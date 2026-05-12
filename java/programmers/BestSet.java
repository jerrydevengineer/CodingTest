package programmers;

import java.util.ArrayList;
import java.util.List;

public class BestSet {
    public int[] solution(int n, int s) {

        if(n > s) return new int[]{-1};

        int[] res = new int[n];
        int r = s % n;
        int q = s / n;
        for(int i = 0; i < n - r; i++) {
            res[i] = q;
        }
        q++;
        for(int i = n - r; i < n; i++) {
            res[i] = q;
        }
        return res;

//        List<Integer> res = new ArrayList<>();
//        int r = s % n;
//        int q = s / n;
//        for(int i = 0; i < n - r; i++) {
//            res.add(q);
//        }
//        q++;
//        for(int i = 0; i < r; i++) {
//            res.add(q);
//        }
//        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
