package com.gisquest.plan.service.common;


public class GlobalServiceException extends RuntimeException {

    private int code;//异常码


    public GlobalServiceException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public GlobalServiceException(ResponseStatus rs) {
        super(rs.getReasonPhrase());
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
