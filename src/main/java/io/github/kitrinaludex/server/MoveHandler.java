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
import java.io.OutputStream;
import java.util.Map;

public class MoveHandler implements HttpHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            final int MAX_SIZE = 10000;

            byte[] body = exchange.getRequestBody().readNBytes(MAX_SIZE + 1);
            if (body.length > MAX_SIZE) {
                sendJsonError(exchange,413,"Payload too large");
            }

            BoardDto req = mapper.readValue(body,BoardDto.class);
            Board board = req.toBoard();

            if (board.isFull()) {
                throw new IllegalArgumentException();
            }

            ComputerPlayer ai = new ComputerPlayer(req.getNextPlayerColor().charAt(0));
            Move move = ai.getMove(board);

            SimpleMoveDto resp = move.toDto();
            byte[] bytes = mapper.writeValueAsBytes(resp);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }

        }catch (IOException | IllegalArgumentException e) {
            sendJsonError(exchange,400,"Bad Request");
        }catch (Exception e) {
            sendJsonError(exchange,500,"Internal Server Error");
        }
    }

    private void sendJsonError(HttpExchange exchange,int statusCode,String message) throws IOException {
        Map<String,String> error = Map.of("error",message);
        byte[] bytes = mapper.writeValueAsBytes(error);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode,bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
