package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class TreeTraversal {

    static int N;
    static Map<String, String[]> trees;
    static class Tree {
        String id;
        String leftChild;
        String rightChild;

        public Tree(String id, String l, String r) {
            this.id = id;
            this.leftChild = l;
            this.rightChild = r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());
        trees = new HashMap<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String id = st.nextToken();
            String l = st.nextToken();
            String r = st.nextToken();
            trees.put(id, new String[]{l, r});
        }

        String root = "A";
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        inorder(root, sb);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        postorder(root, sb);
        System.out.println(sb.toString());
    }

    public static void preorder(String now, StringBuilder sb) {
        if(now.equals(".")) return;
        sb.append(now);
        String lc = trees.get(now)[0];
        String rc = trees.get(now)[1];
        preorder(lc, sb);
        preorder(rc, sb);
    }

    public static void inorder(String now, StringBuilder sb) {
        if(now.equals(".")) return;
        String lc = trees.get(now)[0];
        String rc = trees.get(now)[1];
        inorder(lc, sb);
        sb.append(now);
        inorder(rc, sb);
    }

    public static void postorder(String now, StringBuilder sb) {
        if(now.equals(".")) return;
        String lc = trees.get(now)[0];
        String rc = trees.get(now)[1];
        postorder(lc, sb);
        postorder(rc, sb);
        sb.append(now);
    }
}
