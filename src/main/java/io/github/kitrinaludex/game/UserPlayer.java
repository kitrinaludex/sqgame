package io.github.kitrinaludex.game;

public class UserPlayer extends Player {

    public UserPlayer(char color) {
        super(color);
    }

    public Move getMove(Board board) {
        return null;
    }

    public boolean isComputer() {
        return false;
    }
}
