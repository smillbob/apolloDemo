package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
@Slf4j
public class TestJavaConfigBean {
    @ApolloConfig("application")
    private Config config;
    //inject config for namespace application
    @ApolloConfig("TEST1.testNameSpace")
    private Config config2;

    @Value("${spring.datasource.url:1}")
    private String name;

    @Value("${cccc:test}")
    private String name2;
    //如果配置中心没有值，默认key为test的value值为test


    //config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        log.info(name);
        log.info("name2,{}",name2);
        log.info("config,{}",config.getProperty("test","1"));
        log.info("config2,{}",config2.getProperty("test2","1"));

        ConfigChange change = changeEvent.getChange("test");
        log.info(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }
    @ApolloConfigChangeListener("TEST1.testNameSpace")
    private void another2OnChange(ConfigChangeEvent changeEvent) {
        log.info(name);
        log.info(name2);
        ConfigChange change = changeEvent.getChange("test");
        log.info(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }
}