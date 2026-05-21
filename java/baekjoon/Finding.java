package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Finding {

    static String T, P;
    static int[] skip;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = br.readLine();
        P = br.readLine();
        skip = new int[P.length() + 1];

//        makeSkip(P);
//        kmpSearch(T, P);
    }

    public static void makeSkip(String pattern) {
        int idx1 = 1, idx2 = 0;

        while(idx1 != pattern.length()) {
            if(pattern.charAt(idx1) == pattern.charAt(idx2)) {
                skip[++idx1] = ++idx2;
            } else if(idx2 == 0) {
                skip[++idx1] = idx2;
            } else {
                idx2 = skip[idx2];
            }
        }
    }
}
