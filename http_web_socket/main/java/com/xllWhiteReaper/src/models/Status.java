package models;

public class Status {
    private final int code;
    private final String textStatus;
    private final String message;

    public Status(int code, String textStatus, String message) {
        this.code = code;
        this.textStatus = textStatus;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public String getMessage() {
        return message;
    }
}
