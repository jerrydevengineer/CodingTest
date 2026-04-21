package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NandM2 {

    static int N, M;
    static List<int[]> res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        comb(1, 0, temp);

        for(int[] now : res) {
            StringBuffer sb = new StringBuffer();
            for(int i : now) {
                sb.append(i + " ");
            }
            System.out.println(sb.toString());
        }
    }

    public static void comb(int now, int depth, List<Integer> temp) {
        if(depth == M) {
            res.add(temp.stream().mapToInt(Integer::intValue).toArray());
            return;
        }
        if(now > N) return;

        temp.add(now);
        comb(now+1, depth+1, temp);

        temp.remove(temp.size()-1);
        comb(now+1, depth, temp);
    }
}
