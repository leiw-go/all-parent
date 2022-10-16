package cn.leiwspider.service;

import cn.leiwspider.model.User;

public interface UserService {

    /**
     * 查询用户
     * @param user 用户实体参数
     */
    User getUser(User user);
}
