package cn.leiwspider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.leiwspider.dao.domain.Header;
import cn.leiwspider.service.HeaderService;
import cn.leiwspider.dao.mapper.HeaderMapper;
import org.springframework.stereotype.Service;

/**
* @author leiw
* @description 针对表【header(请求头存储表)】的数据库操作Service实现
* @createDate 2022-11-11 17:39:32
*/
@Service
public class HeaderServiceImpl extends ServiceImpl<HeaderMapper, Header>
    implements HeaderService{

}




