package io.github.kitrinaludex.server.dto;

public class GameStatusDto {
    private int status;
    private String color;
    private String message;

    public GameStatusDto() {
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
