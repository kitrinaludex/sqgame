package io.github.kitrinaludex.game;

public class Move {
    public int x;
    public int y;
    public PieceColor color;

    public Move(PieceColor color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
