package com.du.book.RESTful.result;

public enum ExceptionMsg {
    SUCCESS("200", "operation success"),
    FAILED("999999", "operation failed");

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private String code;
    private String msg;
    private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
