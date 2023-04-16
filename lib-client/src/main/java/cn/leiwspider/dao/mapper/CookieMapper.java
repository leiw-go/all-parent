package cn.leiwspider.dao.mapper;

import cn.leiwspider.dao.domain.Cookie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author leiw
* @description 针对表【cookie(cookie存储表)】的数据库操作Mapper
* @createDate 2022-11-11 17:36:32
* @Entity cn.leiwspider.dao.domain.Cookie
*/
@Mapper
public interface CookieMapper extends BaseMapper<Cookie> {

}




