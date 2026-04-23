package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Calendar {

    static int N;
    static class Plan implements Comparable<Plan> {
        int start;
        int end;

        public Plan(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Plan o) {
            if(this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        List<Plan> plans = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            plans.add(new Plan(s, e));
        }

        Collections.sort(plans);

        int[] days = new int[367];
        for(Plan p : plans) {
            int s = p.start;
            int e = p.end;
            for(int i = s; i <= e; i++) {
                days[i]++;
            }
        }

        int sum = 0;
        int start = 0, maxHeight = 0;
        for(int i = 1; i <=366; i++) {
            if(days[i] == 0) {
                if (maxHeight > 0) {
                    sum += (i - start) * maxHeight;
                    start = 0;
                    maxHeight = 0;
                }
            } else {
                if(start == 0) start = i;
                maxHeight = Math.max(maxHeight, days[i]);
            }
        }

        System.out.println(sum);
    }
}
