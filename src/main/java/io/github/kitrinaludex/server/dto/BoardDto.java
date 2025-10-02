package io.github.kitrinaludex.server.dto;

import io.github.kitrinaludex.game.Board;
import io.github.kitrinaludex.game.Move;

public class BoardDto {
    private int size;
    private String data;
    private String nextPlayerColor;

    public BoardDto() {
    }

    public Board toBoard() {
        validate();

        Board board = new Board(size);
        char[] chars = data.toCharArray();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                char c = chars[y * size + x];
                if (c == 'w') {
                    board.placePiece(new Move('W', x, y));
                } else if (c == 'b') {
                    board.placePiece(new Move('B', x, y));
                }
            }
        }

        return board;
    }

    public void validate() {
        if (size < 3 || data == null || nextPlayerColor == null) {
            throw new IllegalArgumentException();
        }
        if (data.length() != size * size) {
            throw new IllegalArgumentException();
        }
        if (!nextPlayerColor.equals("b") && !nextPlayerColor.equals("w")) {
            throw new IllegalArgumentException();
        }
    }

    public String getNextPlayerColor() {
        return nextPlayerColor;
    }

    public void setNextPlayerColor(String nextPlayerColor) {
        this.nextPlayerColor = nextPlayerColor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
