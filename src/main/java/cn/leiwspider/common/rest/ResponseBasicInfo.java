package cn.leiwspider.common.rest;

/**
 * 响应编码以及提示信息类
 */
public enum ResponseBasicInfo {
    SUCCESS(200, "响应成功"),
    ERROR(500, "响应异常"),
    BAD_REQUEST(400, "Bad Request");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

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

    ResponseBasicInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
