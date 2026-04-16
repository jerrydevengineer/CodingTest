package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class SwitchOnAndOff {

    static boolean[] on;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = Integer.parseInt(st.nextToken());
        on = new boolean[S+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= S; i++) {
            if(Integer.parseInt(st.nextToken()) == 1) {
                on[i] = true;
            }
        }

        int P = Integer.parseInt(br.readLine());
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            changeSwitch(s, n);
        }

        br.close();

        for(boolean o : on) {
            if(o) {
                bw.write(1 + " ");
            } else {
                bw.write(0 + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void changeSwitch(int s, int n) {
        if(s == 1){
            while(n <= on.length) {
                toggleSwitch(n);
                n += n;
            }
        } else {
            if(n == 1) return;
            int l = n;
            int r = n;
            int max = Math.min(l-1, S - r);
            int i = 0;
            while(i < max) {
                if(on[l] == on[r]) {
                    toggleSwitch(l);
                    toggleSwitch(r);
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }
    }

    public static void toggleSwitch(int i) {
        if(on[i]) {
            on[i] = false;
        } else {
            on[i] = true;
        }
    }
}
