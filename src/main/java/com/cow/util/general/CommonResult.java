package com.cow.util.general;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @description 自定义统一响应体
 */
@Setter
@Getter
public class CommonResult {
    private Integer code;
    private String message;
    private Object data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult error(String message) {
        return new CommonResult(500, message);
    }

    public static CommonResult error(String message, Object data) {
        return new CommonResult(500, message, data);
    }

    public static CommonResult success(String message) {
        return new CommonResult(200, message);
    }

    public static CommonResult success(String message, Object data) {
        return new CommonResult(200, message, data);
    }
}
