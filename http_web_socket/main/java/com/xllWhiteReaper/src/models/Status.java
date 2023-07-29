package models;

public class Status {
    private final int code;
    private final String textStatus;
    private final String message;

    Status(int code, String textStatus, String message) {
        this.code = code;
        this.textStatus = textStatus;
        this.message = message;
    }
}
