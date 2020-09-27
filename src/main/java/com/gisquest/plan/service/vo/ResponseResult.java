package com.gisquest.plan.service.vo;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public class ResponseResult<T> {
    private final static String SUCCESS = "success";
    // 状态码
    private int code;
    // 返回信息
    private String msg;
    // 返回数据
    private T data;

    public int getCode() {
        return code;
    }

    public ResponseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseResult<T> setCode(ResponseCode responseCode) {
        this.code = responseCode.code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T>  setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T>  setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 返回成功信息，无结果体
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<T>().setCode(ResponseCode.SUCCESS).setMsg(SUCCESS);
    }

    /**
     * 返回成功信息，有结果体
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<T>().setCode(ResponseCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    /**
     * 自定义状态码，信息
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok(int code,String msg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg);
    }

    /**
     * 自定义状态码，信息 重载
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok(ResponseCode code,String msg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg);
    }

    /**
     * 自定义状态码,信息，结果体
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok(int code,String msg,T data) {
        return new ResponseResult<T>().setCode(code).setMsg(msg).setData(data);
    }

    /**
     * 返回错误信息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult<T>().setCode(ResponseCode.FAIL).setMsg(msg);
    }
}
