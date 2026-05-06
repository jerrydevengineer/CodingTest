package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Z {
    static int N, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        int cnt = count(0, 0, (int)Math.pow(2, N));
        System.out.println(cnt);
    }

    static int count(int x, int y, int size) {
        if(size == 1) return 0;

        int half = size / 2;
        int area = half * half;

        if(R < x + half && C < y + half) {
            return count(x, y, half);
        } else if(R < x + half && C >= y + half) {
            return area + count(x, y + half, half);
        } else if(R >= x + half && C < y + half) {
            return (area * 2) + count(x + half, y + half, half);
        } else {
            return (area * 3) + count(x + half, y + half, half);
        }
    }
}
