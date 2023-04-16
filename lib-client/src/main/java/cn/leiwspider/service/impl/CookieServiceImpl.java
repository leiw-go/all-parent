package cn.leiwspider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.leiwspider.dao.domain.Cookie;
import cn.leiwspider.service.CookieService;
import cn.leiwspider.dao.mapper.CookieMapper;
import org.springframework.stereotype.Service;

/**
* @author leiw
* @description 针对表【cookie(cookie存储表)】的数据库操作Service实现
* @createDate 2022-11-11 17:36:32
*/
@Service
public class CookieServiceImpl extends ServiceImpl<CookieMapper, Cookie>
    implements CookieService{

}




