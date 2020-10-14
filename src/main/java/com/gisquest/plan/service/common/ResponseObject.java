package com.gisquest.plan.service.common;


import java.util.HashMap;

public class ResponseObject extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static ResponseObject ok(ResponseStatus responseStatus) {
        ResponseObject r = new ResponseObject();
        r.put("code", responseStatus.value());
        r.put("msg", responseStatus.getReasonPhrase());
        return r;
    }

    public static ResponseObject error(ResponseStatus responseStatus) {
        ResponseObject r = new ResponseObject();
        r.put("code", responseStatus.value());
        r.put("msg", responseStatus.getReasonPhrase());
        return r;
    }

    public static ResponseObject other_error(String reasonPhrase) {
        ResponseObject r = new ResponseObject();
        r.put("code", 9);
        r.put("msg", reasonPhrase);
        return r;
    }
}
