package com.example.demo2;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @Value("${spring.datasource.driver-class-name}")
    private String test;

    @ApolloConfig
    private Config config2;

    @RequestMapping("/hello")
    public String hello(){
        log.info("111");
        log.info("test,{}",test);
        Config config = ConfigService.getAppConfig();
        String value = config.getProperty("spring.datasource.url", "1");
        log.info("hhahhah{}",value);
        String value2 = config2.getProperty("spring.datasource.url", "1");
        log.info("hhahhah2,{}",value2);

        //new TestJavaConfigBean();
        return "hello";
    }
}
