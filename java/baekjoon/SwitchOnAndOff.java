package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class SwitchOnAndOff {

    static boolean[] on;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        S = Integer.parseInt(br.readLine()); // 스위치 개수 입력받기 (수정)
        on = new boolean[S + 1];

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

        // 🚨 3번, 4번 오류 수정: 1번부터 시작하며, 20개마다 줄바꿈
        for (int i = 1; i <= S; i++) {
            if (on[i]) {
                bw.write("1 ");
            } else {
                bw.write("0 ");
            }

            // 20번째 스위치마다 줄바꿈
            if (i % 20 == 0) {
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    public static void changeSwitch(int s, int n) {
        if (s == 1) { // 남학생
            // 🚨 1번 오류 수정: 배수 증가를 안전하게 처리
            for (int i = n; i <= S; i += n) {
                toggleSwitch(i);
            }
        } else { // 여학생
            // 🚨 2번 오류 수정: 자기 자신은 무조건 변경
            toggleSwitch(n);

            int l = n - 1;
            int r = n + 1;

            // 좌우로 퍼지며 대칭 검사
            while (l >= 1 && r <= S) {
                if (on[l] == on[r]) { // 대칭이면 양쪽 다 변경
                    toggleSwitch(l);
                    toggleSwitch(r);
                    l--;
                    r++;
                } else {
                    break; // 대칭이 깨지면 즉시 중단
                }
            }
        }
    }

    public static void toggleSwitch(int i) {
        on[i] = !on[i]; // if-else보다 짧고 세련된 토글 방식
    }
}
