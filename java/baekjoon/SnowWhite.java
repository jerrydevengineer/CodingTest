package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SnowWhite {

    static int[] nums;
    static List<Integer> res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nums = new int[9];
        for(int i = 0; i < 9; i++) {
            int num = Integer.parseInt(br.readLine().trim());
            nums[i] = num;
        }

        List<Integer> temp = new ArrayList<>();
        res = new ArrayList<>();
        comb(0, 0, temp);
    }

    public static void comb(int now, int depth, List<Integer> temp) {
        if(depth == 7) {
            if(temp.stream().mapToInt(Integer::intValue).sum() == 100) {
                res = temp;
                res.stream().forEach(System.out::println);
                return;
            }
            return;
        }

        if(now >= 9) return;

        temp.add(nums[now]);
        comb(now+1, depth+1, temp);

        temp.remove(temp.size() - 1);
        comb(now+1, depth, temp);
    }
}
