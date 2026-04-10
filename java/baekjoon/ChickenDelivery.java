package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDelivery {

    static int minChicken;
    static List<int[]> houses;
    static List<int[]> stores;
    static List<int[]> picked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][N+1];
        houses = new ArrayList<>();
        stores = new ArrayList<>();
        picked = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) houses.add(new int[] {i, j});
                if(map[i][j] == 2) stores.add(new int[] {i, j});
            }
        }

        minChicken = Integer.MAX_VALUE;

        for(int i = 1; i <= M; i++) {
            backtracking(i, 0);
        }

        System.out.println(minChicken);
    }

    static void backtracking(int max, int now) {
        if(picked.size() == max) {
            int cityChickenDistance = 0;

            for(int[] house : houses) {
                int minHouseDist = Integer.MAX_VALUE;

                for(int[] store : picked) {
                    int temp = getManhattanDistance(house, store);
                    minHouseDist = Math.min(minHouseDist, temp);
                }
                cityChickenDistance += minHouseDist;
            }
            minChicken = Math.min(minChicken, cityChickenDistance);
            return;
        }

        for(int i = now; i < stores.size(); i++) {
            picked.add(stores.get(i));
            backtracking(max, i+1);
            picked.removeLast();
        }
    }

    static int getManhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
    }
}
