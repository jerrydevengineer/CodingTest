package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class NormalBackpack {

    static int N, K;
    static class Stuff {
        int weight;
        int value;

        Stuff(int w, int v) {
            weight = w;
            value = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        Stuff[] stuffs = new Stuff[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            stuffs[i] = new Stuff(w, v);
        }

        Arrays.sort(stuffs, new Comparator<Stuff>() {
            @Override
            public int compare(Stuff o1, Stuff o2) {
                return o2.value/o2.weight - o1.value/o1.weight;
            }
        });


    }
}
