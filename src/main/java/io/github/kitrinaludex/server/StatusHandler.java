package io.github.kitrinaludex.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.github.kitrinaludex.game.Board;
import io.github.kitrinaludex.server.dto.BoardDto;
import io.github.kitrinaludex.server.dto.GameStatusDto;

import java.io.IOException;
import java.io.OutputStream;

public class StatusHandler implements HttpHandler {
        private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        GameStatusDto status = new GameStatusDto();
    try {
        BoardDto req = mapper.readValue(exchange.getRequestBody(), BoardDto.class);
        Board board = req.toBoard();


        status.setColor(req.getNextPlayerColor());
        status.setMessage("Ok");

        if (board.checkWin('W')) {
            status.setStatus(1);
            status.setColor("w");
        } else if (board.checkWin('B')) {
            status.setStatus(1);
            status.setColor("b");
        } else if (board.isFull()) {
            status.setStatus(2);
        }

        byte[] bytes = mapper.writeValueAsBytes(status);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }

    }catch (Exception e) {
        e.printStackTrace();

        status.setStatus(-1);
        status.setMessage("Error:" + e.getMessage());
        status.setColor("");

        byte[] bytes = mapper.writeValueAsBytes(status);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(500,bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
    }

}
