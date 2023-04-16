package cn.leiwspider.dao.mapper;

import cn.leiwspider.dao.domain.Header;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author leiw
* @description 针对表【header(请求头存储表)】的数据库操作Mapper
* @createDate 2022-11-11 17:39:32
* @Entity cn.leiwspider.dao.domain.Header
*/
@Mapper
public interface HeaderMapper extends BaseMapper<Header> {

}




