package com.rookies4.myspringbootlab.runner;

import com.rookies4.myspringbootlab.config.vo.MyEnvironment;
import com.rookies4.myspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import javax.swing.*;


@Component
public class MyPropRunner implements ApplicationRunner {
    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private MyEnvironment environment;

    @Autowired
    private MyPropProperties properties;

    //Logger 객체생성
    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("Logger 구현객체명 = {}",logger.getClass().getName());
        logger.info("MyBootProperties.getUsername() = {}", properties.getUsername());
        logger.info("MyBootProperties.getPort() = {}", properties.getPort());
        logger.debug("Properties myprop.username = {}", username);
        logger.debug("Properties myprop.age = {}", port);



    }
}