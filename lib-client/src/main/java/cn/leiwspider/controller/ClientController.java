package cn.leiwspider.controller;

import cn.hutool.core.util.ReUtil;
import cn.leiwspider.service.RequestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Resource
    private RequestClient requestClient;

    @GetMapping("welcome")
    public String welcome(){
        String html =  requestClient.getResponse().body();
        List<String> list = new ArrayList<>();
        ReUtil.findAll("https://wx1\\.pdlib.com/img/activity/.*?\\.jpg",html, 0,  list);
        return list.get(0);
    }

}
