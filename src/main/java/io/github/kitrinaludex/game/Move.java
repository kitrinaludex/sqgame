package io.github.kitrinaludex.game;

import io.github.kitrinaludex.server.dto.SimpleMoveDto;

public class Move {
    public int x;
    public int y;
    public char color;

    public Move(char color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public SimpleMoveDto toDto() {
        return new SimpleMoveDto(x,y,color);
    }
}
