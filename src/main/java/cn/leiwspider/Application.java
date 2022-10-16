package cn.leiwspider;


import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableKnife4j
@NacosPropertySource(dataId = "springboot-integration-test.yaml", groupId = "LEIW_GROUP", autoRefreshed = true)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}