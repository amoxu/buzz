package com.amoxu.exception;

import com.amoxu.entity.AjaxResult;

/**
 * 系统自定义异常类，针对预期异常，需要在程序中抛出此类的异常
 */
public class CustomException extends Exception {

    //异常信息
    public String message;

    CustomException(String message) {
        AjaxResult<String> ajaxResult = new AjaxResult<>();
        ajaxResult.failed();
        ajaxResult.setMsg(message);

        this.message = ajaxResult.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}


