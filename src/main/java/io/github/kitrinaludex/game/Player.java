package io.github.kitrinaludex.game;

public abstract class Player {
    protected final PieceColor color;

    public Player(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract Move getMove(Board board);
    public abstract boolean isComputer();
}
