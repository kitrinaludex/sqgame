package io.github.kitrinaludex.game;

public abstract class Player {
    protected final char color;

    public Player(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public abstract Move getMove(Board board);

    public abstract boolean isComputer();
}
