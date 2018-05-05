package com.amoxu.exception;

public class UnLoginException extends Exception {
    public String message;

    public UnLoginException() {
        super();
        this.message = "请登录后操作。";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
