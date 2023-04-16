package cn.leiwspider.dao.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * cookie存储表
 * @TableName cookie
 */
@Data
public class Cookie implements Serializable {
    /**
     * 所属Host
     */
    private String host;

    /**
     * cookie属性名称
     */
    private String cookieName;

    /**
     * cookie_值
     */
    private String cookieValue;

    private static final long serialVersionUID = 1L;
}