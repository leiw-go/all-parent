package cn.leiwspider.controller;


import cn.leiwspider.common.rest.Response;
import cn.leiwspider.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试控制器")
@RestController
public class TestController {


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
}