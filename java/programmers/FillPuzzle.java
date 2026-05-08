package programmers;

import java.util.*;

class FillPuzzle {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        boolean[][] visitedBoard = new boolean[n][n];
        boolean[][] visitedTable = new boolean[n][n];

        List<List<int[]>> emptySpaces = new ArrayList<>();
        List<List<int[]>> puzzles = new ArrayList<>();

        // 1. game_board에서 빈 공간(0) 추출
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (game_board[r][c] == 0 && !visitedBoard[r][c]) {
                    emptySpaces.add(extractAndNormalize(game_board, r, c, visitedBoard, 0));
                }
            }
        }

        // 2. table에서 퍼즐 조각(1) 추출
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (table[r][c] == 1 && !visitedTable[r][c]) {
                    puzzles.add(extractAndNormalize(table, r, c, visitedTable, 1));
                }
            }
        }

        // 3. 빈 공간에 퍼즐 맞추기
        int answer = 0;
        boolean[] usedPuzzle = new boolean[puzzles.size()];

        for (List<int[]> empty : emptySpaces) {
            for (int i = 0; i < puzzles.size(); i++) {
                if (usedPuzzle[i]) continue; // 이미 사용한 퍼즐 패스

                List<int[]> puzzle = puzzles.get(i);
                if (empty.size() != puzzle.size()) continue; // 크기가 다르면 애초에 패스

                boolean matched = false;

                // 기본 모양 포함하여 총 4번(360도) 회전하며 비교
                for (int rot = 0; rot < 4; rot++) {
                    if (isMatch(empty, puzzle)) {
                        matched = true;
                        break;
                    }
                    puzzle = rotate(puzzle); // 안 맞으면 90도 회전
                }

                // 퍼즐이 맞았다면 사용 처리하고 정답에 조각 크기 누적
                if (matched) {
                    usedPuzzle[i] = true;
                    answer += empty.size();
                    break; // 해당 빈 공간은 채웠으므로 다음 빈 공간으로 넘어감
                }
            }
        }
        return answer;
    }

    // BFS로 도형을 추출하고 (0,0) 기준으로 정규화하여 반환
    private List<int[]> extractAndNormalize(int[][] board, int r, int c, boolean[][] visited, int target) {
        int n = board.length;
        List<int[]> shape = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            shape.add(curr);

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (!visited[nr][nc] && board[nr][nc] == target) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return normalize(shape);
    }

    // 좌표 영점 맞추기 및 정렬
    private List<int[]> normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        // 가장 작은 행과 열 찾기
        for (int[] p : shape) {
            minR = Math.min(minR, p[0]);
            minC = Math.min(minC, p[1]);
        }

        // 모든 좌표에서 최솟값을 빼서 (0,0)에 바짝 붙임
        List<int[]> normalized = new ArrayList<>();
        for (int[] p : shape) {
            normalized.add(new int[]{p[0] - minR, p[1] - minC});
        }

        // 질문자님이 작성하셨던 훌륭한 다중 정렬 로직 적용
        normalized.sort(Comparator.comparingInt((int[] p) -> p[0]).thenComparingInt(p -> p[1]));

        return normalized;
    }

    // 도형을 90도 시계방향으로 회전
    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] p : shape) {
            rotated.add(new int[]{p[1], -p[0]}); // (r, c) -> (c, -r)
        }
        return normalize(rotated); // 회전 후 다시 영점 맞추기
    }

    // 두 도형이 완벽히 일치하는지 확인
    private boolean isMatch(List<int[]> empty, List<int[]> puzzle) {
        for (int i = 0; i < empty.size(); i++) {
            if (empty.get(i)[0] != puzzle.get(i)[0] || empty.get(i)[1] != puzzle.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
}