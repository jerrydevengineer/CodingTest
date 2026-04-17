package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhatIsRecursion {

    static int N;
    static String s1, s2, s3, s4, s5, s6, s7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        s1 = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
        s2 = "\"재귀함수가 뭔가요?\"";
        s3 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
        s4 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
        s5 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
        s6 = "라고 답변하였지.";
        s7 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";

        System.out.println(s1);
        recursion(0, "");
    }

    static void recursion(int n, String prefix) {
        if(n == N) {
            System.out.println(prefix + s2);
            System.out.println(prefix + s7);
            System.out.println(prefix + s6);
            return;
        }

        System.out.println(prefix + s2);
        System.out.println(prefix + s3);
        System.out.println(prefix + s4);
        System.out.println(prefix + s5);

        recursion(n+1, prefix + "____");

        System.out.println(prefix + s6);
    }
}
