package io.github.kitrinaludex;

import io.github.kitrinaludex.game.CommandHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.run();
    }
}