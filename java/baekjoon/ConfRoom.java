package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConfRoom {
    static class Conf implements Comparable<Conf> {
        int s;
        int e;

        public Conf(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo (Conf o) {
            if(this.e == o.e) return this.s - o.s;
            return this.e - o.e;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Conf[] confList = new Conf[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            Conf c = new Conf(s, e);
            confList[i] = c;
        }

        Arrays.sort(confList);

        int now = 0;
        int res = 0;
        for(Conf c : confList) {
            if(c.s >= now) {
                res++;
                now = c.e;
            }
        }

        System.out.println(res);
    }
}
