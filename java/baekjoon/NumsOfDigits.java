package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NumsOfDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int A = Integer.parseInt(br.readLine().trim());
        int B = Integer.parseInt(br.readLine().trim());
        int C = Integer.parseInt(br.readLine().trim());

        int res = A * B * C;

        String str = String.valueOf(res);
        char[] chars = str.toCharArray();

        int[] answer = new int[10];

        for(char c : chars) {
            answer[c-'0']++;
        }

        for(int ans : answer) {
            System.out.println(ans);
        }
    }
}
