package cn.leiwspider.controller;


import cn.leiwspider.common.rest.Response;
import cn.leiwspider.model.User;
import cn.leiwspider.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试控制器")
@RestController
public class TestController {


    @Resource
    private UserService userService;

    @NacosValue(value = "${testAttribute}", autoRefreshed = true)
    private String testField;

    @GetMapping("/hello")
    @ApiOperation("hello Operation")
    public Response<String> hello() {

        return Response.success("hello, world");
    }

    @PostMapping("/getUser")
    @ApiOperation("获取用户")
    public Response<String> getUser(@RequestBody @Validated User user) {

        return Response.success("用户名：" + user.getName());
    }

    @PostMapping("/getNacosUser")
    @ApiOperation("获取Nacos用户")
    public Response<User> getNacosUser(@RequestBody @Validated User user) {

        User resultUser = userService.getUser(user);
        user.setName(testField);
        return Response.success(user);
    }
}
