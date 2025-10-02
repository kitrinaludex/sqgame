package io.github.kitrinaludex.server.dto;

import io.github.kitrinaludex.game.Board;
import io.github.kitrinaludex.game.Move;

public class BoardDto {
    public int size;
    public String data;
    public String nextPlayerColor;

    public BoardDto(String data, String nextPlayerColor, int size) {
        this.data = data;
        this.nextPlayerColor = nextPlayerColor;
        this.size = size;
    }

    public BoardDto() {
    }

    public Board toBoard() {
        Board board = new Board(size);
        char[] chars = data.toCharArray();

        if (chars.length != size * size) {
            throw new IllegalArgumentException();
        }


        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size;x++) {
                char c = chars[y * size + x];
                if (c == 'w') {
                    board.placePiece(new Move('W',x,y));
                }else if (c == 'b') {
                    board.placePiece(new Move('B',x,y));
                }
            }
        }
        return board;
    }

    public int getSize() {
        return size;
    }

    public String getNextPlayerColor() {
        return nextPlayerColor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNextPlayerColor(String nextPlayerColor) {
        this.nextPlayerColor = nextPlayerColor;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
