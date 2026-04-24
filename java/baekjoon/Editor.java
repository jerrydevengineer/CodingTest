package baekjoon;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Editor {

    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        String str = br.readLine();
        for(char c : str.toCharArray()) {
            left.push(c);
        }

        M = Integer.parseInt(br.readLine().trim());
        for(int i = 0; i < M; i++) {
            String command = br.readLine();
            if(command.trim().equals("L")) {
                if(!left.isEmpty()) right.push(left.pop());
            } else if(command.trim().equals("D")) {
                if(!right.isEmpty()) left.push(right.pop());
            } else if(command.trim().equals("B")) {
                if(!left.isEmpty()) left.pop();
            } else {
                left.push(command.charAt(2));
            }
        }

        br.close();

        while(!left.isEmpty()) {
            right.push(left.pop());
        }

        StringBuilder sb = new StringBuilder();
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb.toString());

//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(br.readLine());
//        int ptr = sb.length();
//
//        M = Integer.parseInt(br.readLine().trim());
//
//        for(int i = 0; i < M; i++) {
//            String command = br.readLine();
//            if(command.trim().equals("L")) {
//                if(ptr > 0) ptr--;
//            } else if(command.trim().equals("D")) {
//                if(ptr < sb.length()) ptr++;
//            } else if(command.trim().equals("B")) {
//                if(ptr > 0) {
//                    sb.deleteCharAt(ptr-1);
//                    ptr--;
//                }
//            } else {
//                sb.insert(ptr, command.charAt(2));
//                ptr++;
//            }
//        }
//
//        System.out.println(sb.toString());
    }
}
