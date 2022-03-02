package com.yayanovel.util;

import java.io.Serializable;

/**
 * 返回工具类
 */
public class ResponseVO<T> implements Serializable {
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
    public static ResponseVO response(Object data, String message, int code){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(code);
        responseVO.setData(data);
        responseVO.setMsg(message);
        return responseVO;
    }
    public static ResponseVO success(Object data, String message){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setMsg(message);
        responseVO.setCode(200);
        responseVO.setData(data);
        return responseVO;
    }
    public static ResponseVO failure(Object data, String message){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setMsg(message);
        responseVO.setCode(400);
        responseVO.setData(data);
        return responseVO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
