package io.github.kitrinaludex.game;

import java.util.Scanner;

public class CommandHandler {
    private Game currentGame;

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();
            String[] parts = input.split("[ ,]+");
            String command = parts[0].toUpperCase();

            switch (command) {
                case "GAME":
                    if (parts.length != 6) {
                        System.out.println("Incorrect command");
                        break;
                    }

                    int n = Integer.parseInt(parts[1]);

                    try {
                        Player p1 = parsePlayer(parts[2], parts[3]);
                        Player p2 = parsePlayer(parts[4], parts[5]);
                        if (p1.color == p2.color) {
                            throw new IllegalArgumentException();
                        }
                        currentGame = new Game(n, p1, p2);
                        System.out.println("New game started");
                        currentGame.start();
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Incorrect command");
                        break;
                    }

                case "MOVE":
                    if (currentGame == null) {
                        System.out.println("Game not started");
                        break;
                    }
                    if (parts.length != 3) {
                        System.out.println("Incorrect command");
                        break;
                    }

                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    currentGame.applyMove(new Move(currentGame.getCurrentPlayer().getColor(), x, y));
                    break;

                case "HELP":
                    printHelp();
                    break;

                case "EXIT":
                    return;

                default:
                    System.out.println("Incorrect command");
            }
        }
    }


    public Player parsePlayer(String playerType, String playerColor) {
        char pc = playerColor.toUpperCase().charAt(0);
        if (pc != 'W' && pc != 'B') {
            throw new IllegalArgumentException();
        }
        if (playerType.equals("user")) {
            try {
                return new UserPlayer(pc);
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect command");
            }
        }
        return new ComputerPlayer(pc);
    }

    private void printHelp() {
        System.out.println("Доступные комманды:");
        System.out.println("  GAME N, TYPE1 C1, TYPE2 C2");
        System.out.println("    Начать новую игру.");
        System.out.println("    N      - размер доски (>2)");
        System.out.println("    TYPE   - вид игрока : 'user' или 'comp'");
        System.out.println("    C      - цвет фигрур: 'W' (белые) или 'B' (чёрные)");
        System.out.println("    Пример: GAME 5, user W, comp B");
        System.out.println();
        System.out.println("  MOVE X, Y");
        System.out.println("    Сделать ход: (X,Y).");
        System.out.println("    Пример: MOVE 0, 1");
        System.out.println();
        System.out.println("  HELP");
        System.out.println("    Показать это сообщение.");
        System.out.println();
        System.out.println("  EXIT");
        System.out.println("    Выйти.");
    }
}
