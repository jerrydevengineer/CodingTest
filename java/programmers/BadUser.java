package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadUser {
    static Ss[] sList;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        sList = new Ss[26];
        for (String id : user_id) {
            char[] temp = id.toCharArray();
            Ss cur = null;
            Ss[] curList = sList;
            for (char t : temp) {
                if (curList[t - 'a'] != null) {
                    cur = curList[t - 'a'];
                } else {
                    cur = new Ss(t);
                    curList[t - 'a'] = cur;
                }
                curList = cur.next;
            }
            if (cur != null)
                cur.finished = true;
        }

        List<List<String>> ids = new ArrayList<>();
        for (String id : banned_id) {
            char[] temp = id.toCharArray();
            List<String> resList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            backtracking(null, temp, 0, sb, resList);
            ids.add(resList);
        }

        for (int i = 0; i < ids.size() - 1; i++) {
            int i1 = ids.get(i).size();
            int i2 = ids.get(i + 1).size();
            Set<String> set = new HashSet<>(ids.get(i));
            set.addAll(ids.get(i + 1));
            answer += i1 * i2 - (i1 + i2 - set.size());
        }

        return answer;
    }

    public void backtracking(Ss ss, char[] cArr, int idx, StringBuilder sb, List<String> res) {
        if (idx == cArr.length - 1) {
            if (ss.finished) {
                sb.append(ss.c);
                res.add(sb.toString());
            } else {
                return;
            }
        }

        char c = cArr[idx];

        if (idx == 0) {
            if (c == '*') {
                for (Ss first : sList) {
                    sb.append(first.c);
                    backtracking(first, cArr, idx + 1, sb, res);
                    sb.deleteCharAt(0);
                }
            } else {
                ss = sList[c - 'a'];
                if (ss == null)
                    return;
                sb.append(ss.c);
                backtracking(ss, cArr, idx + 1, sb, res);
            }
            return;
        }

        if (c == '*') {
            for (Ss s : ss.next) {
                sb.append(s.c);
                backtracking(s, cArr, idx + 1, sb, res);
                sb.deleteCharAt(sb.length());
            }
        } else {
            if (ss.next[c - 'a'] == null)
                return;
            backtracking(ss.next[c - 'a'], cArr, idx + 1, sb.append(c), res);
        }
    }

    class Ss {

        char c;
        Ss[] next;
        boolean finished;

        public Ss(char c) {
            this.c = c;
            next = new Ss[26];
        }
    }
}
