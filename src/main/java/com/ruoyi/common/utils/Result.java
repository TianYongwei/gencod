package com.ruoyi.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zlt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private Integer code;
    private String msg;

    public static <T> Result<T> succeed() {
        return of(null, CodeEnum.SUCCESS.getCode(), MsgEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), MsgEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> of(T datas, Integer code, String msg) {
        return new Result<>(datas, code, msg);
    }

    public static <T> Result<T> failed() {
        return of(null, CodeEnum.ERROR.getCode(), MsgEnum.ERROR.getMsg());
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }

    /**
     * @Author: zlt
     */
    public enum CodeEnum {
        SUCCESS(0),
        ERROR(1);

        private Integer code;
        CodeEnum(Integer code){
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum MsgEnum {
        SUCCESS("请求成功！"),
        ERROR("请求失败！");

        private String msg;
        MsgEnum(String msg){
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
}