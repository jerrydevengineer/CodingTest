package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class PrintStar2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for(int i = 0; i<N; i++){
            int j = 1;
            while(j<N-i){
                bw.write(" ");
                j++;
            }
            while(j<=N){
                bw.write("*");
                j++;
            }
            bw.write("\n");
        }
        bw.close();
    }
}
