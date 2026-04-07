package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Sorting3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // int[] arr = new int[N];
        // for(int i = 0; i < N; i++) {
        //     int x = Integer.parseInt(br.readLine().trim());
        //     arr[i] = x;
        // }

        // Arrays.sort(arr);

        // for(int i = 0; i < N; i++) {
        //     bw.write(arr[i] + "\n");
        // }

        // 1. 숫자의 범위(1 ~ 10,000)를 커버하는 카운팅 배열 생성
        // index 1부터 사용하기 위해 크기를 10001로 잡습니다.
        int[] count = new int[10001];

        // 2. 입력받은 숫자를 인덱스로 삼아 해당 숫자의 출현 횟수를 1씩 증가
        for (int i = 0; i < N; i++) {
            count[Integer.parseInt(br.readLine().trim())]++;
        }
        
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 3. 1부터 10000까지 배열을 순회하며, 기록된 횟수만큼 해당 숫자를 출력
        for (int i = 1; i <= 10000; i++) {
            while (count[i] > 0) {
                bw.write(i + "\n");
                count[i]--;
            }
        }

        bw.flush();
        bw.close();
    }
}
