package cn.leiwspider.service;

import cn.hutool.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

@Service
public interface RequestClient {

    /**
     * 更新cookie
     * @param cookies
     * @param hostName
     * @return
     */
    boolean saveCookies(List<HttpCookie> cookies, String hostName);

    List<HttpCookie> getCookies(String hostName);

    Map<String, String> getHeaderMap(String hostName);

    HttpResponse getResponse();


}
