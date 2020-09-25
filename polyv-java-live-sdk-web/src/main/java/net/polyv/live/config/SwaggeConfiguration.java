package net.polyv.live.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 启动配置入口类
 * @author: thomas
 * @date: 2020/9/24
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggeConfiguration {
    
    @Bean()
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_12)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("直播SDK")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("net.polyv.live"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    
 
 
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("保利威直播SDK快速接入测试DEMO")
                .description("SDK功能测试，对接DEMO")
                .termsOfServiceUrl("")
                .contact(new Contact("wujie","https://www.polyv.net/","wujie@polyv.net"))
                .version("1.0")
                .build();
    }
    
}

