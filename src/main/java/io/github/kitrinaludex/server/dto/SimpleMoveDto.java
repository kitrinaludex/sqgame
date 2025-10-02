package io.github.kitrinaludex.server.dto;

public class SimpleMoveDto {
    private int x;
    private int y;
    private String color;

    public SimpleMoveDto() {
    }

    public SimpleMoveDto(int x, int y, char color) {
        this.color = String.valueOf(color);
        this.x = x;
        this.y = y;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
