package com.du.bookServer.RESTful.Result;

import java.io.Serializable;

public class RESTfulResponse implements Serializable {
    /** 返回信息码*/
    private String rspCode="000000";
    /** 返回信息内容*/
    private String rspMsg="操作成功";

    public RESTfulResponse() {
    }

    public RESTfulResponse(ExceptionMsg msg){
        this.rspCode=msg.getCode();
        this.rspMsg=msg.getMsg();
    }

    public RESTfulResponse(String rspCode) {
        this.rspCode = rspCode;
        this.rspMsg = "";
    }

    public RESTfulResponse(String rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }
    public String getRspCode() {
        return rspCode;
    }
    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }
    public String getRspMsg() {
        return rspMsg;
    }
    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    @Override
    public String toString() {
        return "RESTfulResponse{" +
                "rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                '}';
    }
}

