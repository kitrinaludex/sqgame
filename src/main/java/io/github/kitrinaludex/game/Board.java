package io.github.kitrinaludex.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private final char[][] cells;
    private final int size;
    private final int maxTurnCount;
    private int turnCount = 1;

    public Board(int size) {
        this.size = size;
        this.cells = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = ' ';
            }
        }
        maxTurnCount = size * size;
    }

    public boolean placePiece(Move move) {
        int x = move.x;
        int y = move.y;
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        if (cells[x][y] != ' ') {
            return false;
        }
        cells[x][y] = move.color;
        turnCount++;
        System.out.println(move.color + " (" + x + ", " + y + ")");
        return true;
    }

    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    public boolean isFull() {
        return turnCount > maxTurnCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = cells[i][j];
                sb.append((c == ' ' ? " " : c));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    public boolean checkWin(char color) {
        // ничего умнее я не придумал ):
        List<int[]> points = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (cells[y][x] == color) {
                    points.add(new int[]{x, y});
                }
            }
        }

        int n = points.size();
        if (n < 4) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int[][] quad = {points.get(i), points.get(j), points.get(k), points.get(l)};
                        if (isSquare(quad)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean isSquare(int[][] pts) {
        List<Integer> dists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int dx = pts[i][0] - pts[j][0];
                int dy = pts[i][1] - pts[j][1];
                dists.add((dx * dx) + (dy * dy)); //квадрат расстояния
            }
        }
        Collections.sort(dists);

        return dists.get(0) > 0 &&
               dists.get(0).equals(dists.get(1)) &&
               dists.get(1).equals(dists.get(2)) &&
               dists.get(2).equals(dists.get(3)) &&
               dists.get(4).equals(dists.get(5)) &&
               dists.get(4) == 2 * dists.get(0);
    }
}
