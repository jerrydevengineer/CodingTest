package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ChristmasGift {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st;
        PriorityQueue<Integer> gifts = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 0) {
                if(!gifts.isEmpty()) {
                    bw.write(gifts.poll() + "\n");
                } else {
                    bw.write("-1\n");
                }
            } else {
                for(int j = 0; j < a; j++) {
                    gifts.add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
