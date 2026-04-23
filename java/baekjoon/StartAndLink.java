package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class StartAndLink {

    static int N;
    static int[][] table;
    static int min = Integer.MAX_VALUE;
    static int tsum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tsum = 0;

        table = new int[N][N];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                int score = Integer.parseInt(st.nextToken());
                table[r][c] = score;
                tsum += score;
            }
        }

        backtracking(0, new ArrayList<Integer>());
        System.out.println(min);
    }

    public static void backtracking(int now, List<Integer> members) {
        if(members.size() == N/2) {
            Set<Integer> memberSet = new HashSet<>();
            for(int i = 0; i < N; i++) {
                memberSet.add(i);
            }

            for(Integer member : members) {
                memberSet.remove(member);
            }
            List<Integer> member2 = memberSet.stream().collect(Collectors.toList());

            int sum1 = findSum(members.stream().mapToInt(Integer::intValue).toArray());
            int sum2 = findSum(member2.stream().mapToInt(Integer::intValue).toArray());

            min = Math.min(min, Math.abs(sum1 - sum2));
        }

        if(now >= N) return;

        members.add(now);
        backtracking(now + 1, members);

        members.remove(members.size() -1);
        backtracking(now + 1, members);
    }

    public static int findSum(int[] members) {
        int sum = 0;
        for(int i = 0; i < members.length; i++) {
            for(int j = i + 1; j < members.length; j++) {
                sum += table[members[i]][members[j]];
                sum += table[members[j]][members[i]];
            }
        }
        return sum;
    }
}
