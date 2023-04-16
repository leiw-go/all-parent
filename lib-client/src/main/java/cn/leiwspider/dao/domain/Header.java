package cn.leiwspider.dao.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 请求头存储表
 * @TableName header
 */
@Data
public class Header implements Serializable {
    /**
     * 请求头所属域名
     */
    private String host;

    /**
     * 属性名称
     */
    private String headName;

    /**
     * 值
     */
    private String headerValue;

    private static final long serialVersionUID = 1L;
}