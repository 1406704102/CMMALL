package com.ppjj.cmmall.common.exception;

/*
 * @Author PangJie___
 * @Description  返回码
 *      10:通用
 *          001:参数格式错误
 *      11:商品
 *      12:订单
 *      13:购物车
 *      14:物流
 * @Date 11:30 2023/1/13
 * param
 * return
 */

import lombok.AllArgsConstructor;
import lombok.Data;


public enum BizCodeEnum {
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"参数格式校验错误");

    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
