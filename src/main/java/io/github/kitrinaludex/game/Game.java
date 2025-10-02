package io.github.kitrinaludex.game;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private boolean finished = false;

    public Game(int size,Player p1,Player p2) {
        board = new Board(size);
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;
    }

    public void start() {
        if (currentPlayer.isComputer()) {
            applyMove(currentPlayer.getMove(board));
        }
    }

//    public void applyMove(Move move) {
//        if (finished) {
//            System.out.println("Game already finished");
//            return;
//        }
//
//        if (!board.placePiece(move)) {
//            System.out.println("Incorrect command");
//            return;
//        }
//
//        System.out.println(board); //для наглядности, в тз этого не просили
//
//        if (board.checkWin(move.color)) {
//            System.out.println("Game finished. " + move.color + " wins!");
//            finished = true;
//            return;
//        }
//
//        if (board.isFull()) {
//            System.out.println("Game finished. Draw");
//            finished = true;
//            return;
//        }
//
//        switchPlayer();
//        if (currentPlayer.isComputer()) {
//            applyMove(currentPlayer.getMove(board));
//        }
//    }

    public void applyMove(Move move) {
        if (finished) {
            return;
        }

        if (!board.placePiece(move)) {
            System.out.println("Incorrect command");
            return;
        }

        System.out.println(board); //для наглядности, в тз этого не просили

        if (board.checkWin(move.color)) {
            System.out.println("Game finished. " + move.color + " wins!");
            finished = true;
        } else if (board.isFull()) {
            System.out.println("Game finished. Draw");
            finished = true;
        }

        if (finished) {
            return;
        }

        switchPlayer();
        if (currentPlayer.isComputer()) {
            applyMove(currentPlayer.getMove(board));
        }
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Board getBoard() {
        return board;
    }

}
