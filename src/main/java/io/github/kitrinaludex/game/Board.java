package io.github.kitrinaludex.game;

public class Board {
    private final PieceColor[][] cells;
    private final int size;
    private int turnCount = 0;
    private final int maxTurnCount;

    public Board(int size) {
        this.size = size;
        this.cells = new PieceColor[size][size];
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size;j++) {
                cells[i][j] = null;
            }
        }
        maxTurnCount = size * size;
    }

    public boolean placePiece(Move move) {
        int x = move.x;
        int y = move.y;
        if (x < 0 || x >= size|| y < 0 || y >= size) {
            return false;
        }
        if (cells[x][y] != null) {
            return false;
        }
        cells[x][y] = move.color;
        turnCount++;
        System.out.println(move.color + " (" + x + ", " + y + ")");
        return true;
    }

    public boolean isCellEmpty(int x,int y) {
        return cells[x][y] == null;
    }

    public boolean isFull() {
        return turnCount > maxTurnCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < size;i++) {
            for (int j = 0;j < size;j++) {
                PieceColor c = cells[i][j];
                sb.append((c == null ? "." : c.name()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    public boolean checkWin(PieceColor color) {
        //TODO
        return false;
    }
}
