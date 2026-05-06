package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree {

    static int N;
    static int[][] quadTree;
    static StringBuilder strBuild = new StringBuilder();

//    private static void cut(int x, int y, int size) {
//        int sum = 0;
//        for(int i = x; i < x + size; i++) {
//            for(int j = y; j < y + size; j++) {
//                sum += quadTree[i][j];
//            }
//        }
//
//        if(sum == 0) {
//            strBuild.append("0");
//        } else if(sum == size * size) {
//            strBuild.append("1");
//        } else {
//            strBuild.append("(");
//            cut(x, y, size/2);
//            cut(x, y + size/2, size/2);
//            cut(x + size/2, y, size/2);
//            cut(x + size/2, y + size/2, size/2);
//            strBuild.append(")");
//        }
//    }

    private static void cut(int x, int y, int size) {
        int firstValue = quadTree[x][y];
        boolean sameYN = true;
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(quadTree[i][j] != firstValue) {
                    sameYN = false;
                    break;
                }
            }
            if(!sameYN) break;
        }

        if(sameYN) {
            strBuild.append(firstValue);
            return;
        }

        strBuild.append("(");
        cut(x, y, size/2);
        cut(x, y + size/2, size/2);
        cut(x + size/2, y, size/2);
        cut(x + size/2, y + size/2, size/2);
        strBuild.append(")");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        quadTree = new int[N][N];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                quadTree[i][j] = str.charAt(j) - '0';
            }
        }

        br.close();

        cut(0, 0, N);

        System.out.println( strBuild.toString() );
    }
}
