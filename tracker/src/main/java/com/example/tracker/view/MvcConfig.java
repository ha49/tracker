package com.example.tracker.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/application").setViewName("application");
        registry.addViewController("/admin").setViewName("adminpage");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/clientpage").setViewName("clientpage");
        registry.addViewController("/coachpage").setViewName("coachpage");
        registry.addViewController("/logout").setViewName("logout");
    }

}
