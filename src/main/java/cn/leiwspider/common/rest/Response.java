package cn.leiwspider.common.rest;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应模版类
 */
@ApiModel(description = "响应模版类")
public class Response<T> implements Serializable {

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;
    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String message;

    /**
     * 结果集
     */
    @ApiModelProperty("结果集")
    private T data = null;


    public Response() {
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果(通用)
     */
    public static <T> Response<T> success() {
        return new Response<T>(ResponseBasicInfo.SUCCESS.getCode(), ResponseBasicInfo.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果(通用有数据)
     *
     * @param data 获取的数据
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseBasicInfo.SUCCESS.getCode(), ResponseBasicInfo.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果（自定义）
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Response<T> success(String message, T data) {
        return new Response<T>(ResponseBasicInfo.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果（通用）
     */
    public static <T> Response<T> error() {
        return new Response<T>(ResponseBasicInfo.ERROR.getCode(), ResponseBasicInfo.ERROR.getMessage(), null);
    }

    /**
     * 失败返回结果（通用）
     */
    public static <T> Response<T> error(String errorMsg) {
        return new Response<T>(ResponseBasicInfo.ERROR.getCode(), errorMsg, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Response<T> error(Integer code ,String errorMsg) {
        return new Response<T>(code, errorMsg, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

