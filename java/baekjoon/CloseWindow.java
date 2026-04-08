package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloseWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N이 최대 21억이므로 int 범위에 안전하게 들어갑니다.
        int N = Integer.parseInt(br.readLine().trim());

        // 단 1번의 연산으로 제곱수의 개수(열린 창문의 수)를 바로 도출
        System.out.println((int) Math.sqrt(N));
    }
}
