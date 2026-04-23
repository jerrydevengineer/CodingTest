package baekjoon;

import java.io.*;
import java.util.Stack;

public class KeyLogger {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String s = br.readLine();

            // 커서를 기준으로 왼쪽, 오른쪽 스택 생성
            Stack<Character> leftStack = new Stack<>();
            Stack<Character> rightStack = new Stack<>();

            for(char c : s.toCharArray()) {
                if(c == '<') {
                    // 커서를 왼쪽으로 이동: 왼쪽 스택에서 뽑아 오른쪽 스택으로
                    if(!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                } else if(c == '>') {
                    // 커서를 오른쪽으로 이동: 오른쪽 스택에서 뽑아 왼쪽 스택으로
                    if(!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                } else if(c == '-') {
                    // 백스페이스: 왼쪽 스택의 맨 위 글자 삭제
                    if(!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                } else {
                    // 일반 문자: 커서 왼쪽(왼쪽 스택)에 추가
                    leftStack.push(c);
                }
            }

            // 출력 만들기
            StringBuilder sb = new StringBuilder();

            // 1. 왼쪽 스택의 글자들은 스택 특성상 거꾸로 나오므로 나중에 한 번에 뒤집기 위해 순서대로 append
            for(char c : leftStack) {
                sb.append(c);
            }

            // 2. 오른쪽 스택은 이미 LIFO 구조로 뒤집혀 있으므로 pop 하면서 append
            while(!rightStack.isEmpty()) {
                sb.append(rightStack.pop());
            }

            bw.write(sb.toString() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
