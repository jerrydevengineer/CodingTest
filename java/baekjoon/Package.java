package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Package {

    static class Route implements Comparable<Route>{

        int from;
        int to;
        int amount;

        public Route(int from, int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public int compareTo(Route o) {
            if(this.to == o.to) {
                return this.from - o.from;
            }
            return this.to - o.to;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Route> pq = new PriorityQueue<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            pq.add(new Route(from, to, amount));
        }

        int res = 0;
        int[] having = new int[N+1];

        while(!pq.isEmpty()) {
            Route curr = pq.poll();
            int from = curr.from;
            int to = curr.to;
            int amount = curr.amount;

            int max = 0;
            for(int i = from; i < to; i++) {
                max = Math.max(max, having[i]);
            }

            int poss = C - max;
            if(poss > 0) {
                poss = Math.min(poss, amount);
            } else {
                continue;
            }

            for(int i = from; i < to; i++) {
                having[i] += poss;
            }

            res += poss;
        }

        System.out.println(res);
    }
}
