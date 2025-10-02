package io.github.kitrinaludex.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebService {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/squares/nextMove",new MoveHandler());
        server.createContext("/api/squares/gameStatus",new StatusHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Сервер запущен на порте 8080");
    }
}
