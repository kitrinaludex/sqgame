package io.github.kitrinaludex.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.github.kitrinaludex.game.Board;
import io.github.kitrinaludex.game.ComputerPlayer;
import io.github.kitrinaludex.game.Move;
import io.github.kitrinaludex.server.dto.BoardDto;
import io.github.kitrinaludex.server.dto.SimpleMoveDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MoveHandler implements HttpHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            BoardDto req;
            try (InputStream is = exchange.getRequestBody()) {
                req = mapper.readValue(is, BoardDto.class);
            }

            Board board = req.toBoard();

            ComputerPlayer ai = new ComputerPlayer(req.getNextPlayerColor().charAt(0));
            Move move = ai.getMove(board);

            SimpleMoveDto resp = move.toDto();
            byte[] bytes = mapper.writeValueAsBytes(resp);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }

        }catch (Exception e) {
            e.printStackTrace();
            String msg = "Error:" + e.getMessage();
            byte[] bytes = msg.getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(500,bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }

    }


}
