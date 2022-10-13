package cn.leiwspider.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket defaultDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("defaultGroup")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.leiwspider.controller")) // 设置扫描路径
                .build();
    }

    @Bean
    public Docket TestDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("TestGroup")
                .apiInfo(testApiInfo())
                //选择接口
                .select()
                //选择接口
                .apis(RequestHandlerSelectors.basePackage("cn.leiwspider.controller"))
                //build()一般表示工厂设计模式
                .build();
    }

    private ApiInfo testApiInfo(){
        //作者信息
        Contact contact = new Contact("leiw","","leiwsnc@qq.com");
        return new ApiInfo(
                "ApiTitle",
                "ApiDescription",
                "9.9",
                "api.leiwspider.cn",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }


}

