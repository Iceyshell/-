package com.yanagi.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanagi
 * @description 后端统一返回给前端的结果
 * @date 2024-04-19 13:32
 */
@Data
public class Result implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应成功
     * @param msg
     * @param data
     * @return
     */
    public static Result success (String msg, Object data){
        return new Result(200, msg, data);
    }

    public static Result success (String msg){
        return new Result(200, msg);
    }

    /**
     * 响应失败
     * @param msg
     * @return
     */
    public static Result fail (String msg){
        return new Result (500, msg);
    }

    public Result() {
    }
}
