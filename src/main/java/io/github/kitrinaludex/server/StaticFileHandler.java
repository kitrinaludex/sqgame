package io.github.kitrinaludex.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StaticFileHandler implements HttpHandler {
    private final ClassLoader classLoader = getClass().getClassLoader();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) {
            path = "/static/index.html";
        } else {
            path = "/static" + path;
        }

        try (InputStream is = classLoader.getResourceAsStream(path.substring(1))) {
            if (is == null) {
                exchange.sendResponseHeaders(404, -1);
                System.out.println("is is null");
                return;
            }

            byte[] bytes = is.readAllBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }

        }
    }
}


