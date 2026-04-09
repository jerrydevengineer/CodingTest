package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cogwheel {

    static class Wheel {
        int[] seq;
        int top = 0;

        public Wheel(int[] seq) {
            this.seq = seq;
        }

        public void turn(int dir) {
            if(dir == 1) { // 시계 방향
                top = (top + 7) % 8;
            } else if (dir == -1) { // 반시계 방향
                top = (top + 1) % 8;
            }
        }
        
        // 3시 방향 극성 확인
        public int getRight() {
            return seq[(top + 2) % 8];
        }
        
        // 9시 방향 극성 확인
        public int getLeft() {
            return seq[(top + 6) % 8];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Wheel[] wheels = new Wheel[4];
        for(int i = 0; i < 4; i++) {
            // Stream 대신 charAt을 사용하여 조금 더 빠르게 파싱
            int[] arr = new int[8];
            String line = br.readLine().trim();
            for(int j = 0; j < 8; j++) {
                arr[j] = line.charAt(j) - '0';
            }
            wheels[i] = new Wheel(arr);
        }

        int K = Integer.parseInt(br.readLine().trim());
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1; // 0-based 인덱스로 맞춤
            int dir = Integer.parseInt(st.nextToken());

            // 1. 각 톱니바퀴가 회전할 방향을 저장하는 배열 (0: 회전 안함, 1: 시계, -1: 반시계)
            int[] dirs = new int[4];
            dirs[num] = dir;

            // 2. 선택된 톱니바퀴의 왼쪽 전파
            for(int j = num - 1; j >= 0; j--) {
                if(wheels[j].getRight() != wheels[j + 1].getLeft()) { // 극이 다르면
                    dirs[j] = -dirs[j + 1]; // 오른쪽 바퀴와 반대 방향
                } else {
                    break; // 극이 같으면 그 이상 전파되지 않음
                }
            }

            // 3. 선택된 톱니바퀴의 오른쪽 전파
            for(int j = num + 1; j < 4; j++) {
                if(wheels[j - 1].getRight() != wheels[j].getLeft()) {
                    dirs[j] = -dirs[j - 1]; // 왼쪽 바퀴와 반대 방향
                } else {
                    break;
                }
            }

            // 4. 결정된 방향으로 한 번에 회전 적용
            for(int j = 0; j < 4; j++) {
                wheels[j].turn(dirs[j]);
            }
        }

        int res = 0;
        for(int i = 0; i < 4; i++) {
            if(wheels[i].seq[wheels[i].top] == 1) {
                res += (1 << i); // Math.pow 대신 비트 시프트 연산으로 점수 최적화
            }
        }
        System.out.println(res);
    }
}
