package io.github.kitrinaludex.game;

import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random rng = new Random();

    public ComputerPlayer(char color) {
        super(color);
    }

    @Override
    public Move getMove(Board board) {
        int size = board.getSize();
        while (true) {
            int x = rng.nextInt(size);
            int y = rng.nextInt(size);
            if (board.isCellEmpty(x, y)) {
                return new Move(super.color, x, y);
            }
        }
    }

    public boolean isComputer() {
        return true;
    }
}
