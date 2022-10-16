package cn.leiwspider.service;

import cn.leiwspider.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 查询用户
     * @param user 用户实体参数
     */
    @Override
    public User getUser(User user) {
        user.setName("验证服务用户");
        return user;
    }
}
