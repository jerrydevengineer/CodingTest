package baekjoon;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class HanoiTower {

    static Stack<Integer> t1, t2, t3;
    static int N;
    static int move;
    static List<int[]> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        t1 = new Stack<>();
        t2 = new Stack<>();
        t3 = new Stack<>();

        for(int i = N; i >= 1; i--) {
            t1.push(i);
        }

        recursive();
    }

    static void recursive() {
        if(t3.size() == N) return;
        
    }
}
