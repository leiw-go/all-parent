package cn.leiwspider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.leiwspider.dao.domain.Cookie;
import cn.leiwspider.dao.domain.Header;
import cn.leiwspider.service.CookieService;
import cn.leiwspider.service.HeaderService;
import cn.leiwspider.service.RequestClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.*;

@Service
@Slf4j
public class RequestClientImpl implements RequestClient {

    @Resource
    CookieService cookieService;
    @Resource
    HeaderService headerService;




    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCookies(List<HttpCookie> cookies, String hostName) {
        Map<String, String> resultCookieMap = new HashMap<String, String>(){{
            put("name", "cookieName");
            put("value", "cookieValue");
        }};
        List<Cookie> resultCookie = BeanUtil.copyToList(cookies, Cookie.class,
                CopyOptions.create().setFieldMapping(resultCookieMap));
        UpdateWrapper<Cookie> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Host", hostName);
        int update;
        for (Cookie cookie : resultCookie) {
            updateWrapper.eq("cookie_name", cookie.getCookieName());
            update = cookieService.getBaseMapper().update(cookie, updateWrapper);
            if(update == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<HttpCookie> getCookies(String hostName) {
        QueryWrapper<Cookie> wrapper = new QueryWrapper<>();
        wrapper.eq("host", hostName);
        List<Cookie> cookieList = cookieService.getBaseMapper().selectList(wrapper);
        log.info("cookies in mysql--->" + cookieList);
        List<HttpCookie> list = new ArrayList<>();
        for (Cookie cookie : cookieList) {
            list.add(new HttpCookie(cookie.getCookieName(),cookie.getCookieValue()));
        }
        return list;
    }

    @Override
    public Map<String, String> getHeaderMap(String hostName) {
        QueryWrapper<Header> headWrapper = new QueryWrapper<>();
        headWrapper.eq("host", hostName);
        List<Header> headList = headerService.getBaseMapper().selectList(headWrapper);
        Map<String, String> headMap = new HashMap<>();
        headList.forEach(f -> {
            headMap.put(f.getHeadName(), f.getHeaderValue());
        });
        return headMap;
    }

    @Override
    public HttpResponse getResponse() {
        String dateStr1 = "2022-11-11 ";
        Date date1 = DateUtil.parse(dateStr1);
        int betweenDay = (int)DateUtil.between(date1, new Date(), DateUnit.DAY);
        String nowStr = String.valueOf(3144 + betweenDay);
        String hostName = "wx1.pdlib.com";


        //查询header
        Map<String, String> headerMap = getHeaderMap(hostName);
        log.info("headerMap--->" + headerMap.toString());

        //查询cookie
        List<HttpCookie> httpCookieList = getCookies(hostName);
        log.info("httpCookieList--->" + httpCookieList.toString());

        HttpResponse result = HttpRequest.get("https://wx1.pdlib.com/pudonglib-weixin/activity/detail/mine?id=" + nowStr)
                .headerMap(headerMap, true)
                .cookie(httpCookieList)
                .timeout(20000)//超时，毫秒
                .execute();

        //更新cookie
        List<HttpCookie> cookies = result.getCookies();
        log.info("responseCookies--->" + cookies.toString());
        saveCookies(cookies, hostName);

        log.info("status--->" + result.getStatus());
        return result;
    }

    public static void main(String[] args) {
        String dateStr1 = "2022-11-11 ";
        Date date1 = DateUtil.parse(dateStr1);
        int betweenDay = (int) DateUtil.between(date1, new Date(), DateUnit.DAY);
        System.out.println(String.valueOf(3144 + betweenDay));
    }
}
