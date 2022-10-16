package cn.leiwspider.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel(description = "用户请求实体")
public class User implements Serializable {

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", required = true)
    //@NotBlank(message = "用户姓名不能为空")
    private String name;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号", required = true)
    //@NotBlank(message = "用户手机号不能为空")
    //@Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "用户手机号格式不正确")
    private String mobilePhone;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱", required = true)
    //@Email(message = "邮箱格式不正确")
    //@NotBlank(message = "用户邮箱不能为空")
    private String email;

    /**
     * 用户身份证
     */
    @ApiModelProperty(value = "用户身份证", required = true)
    //@NotBlank(message = "用户身份证不能为空")
    //@Pattern(regexp = "^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "用户身份证格式不正确")
    private String idCard;
}
