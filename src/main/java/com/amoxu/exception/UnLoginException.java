package com.amoxu.exception;

import com.amoxu.util.StaticEnum;

public class UnLoginException extends Exception {
    public String message;

    public UnLoginException() {
        super();
        this.message = StaticEnum.OPT_UNLOGIN + ",请登录后操作。";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
