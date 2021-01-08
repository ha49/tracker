package com.example.demo.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@Scope("prototype")
public class TestScopes {

    Logger logger = LoggerFactory.getLogger(TestScopes.class);

    @PostConstruct
    public void init() {
        logger.info("TestScopes bean is alive!");
    }

}