package com.demo.IPMDemo.demo.Configuration;

import com.demo.IPMDemo.demo.interceptor.passportintercepetor;
import com.demo.IPMDemo.demo.interceptor.requiredintercepetor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class IPMConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private passportintercepetor passportintercepetor;
    @Autowired
    private requiredintercepetor requiredintercepetor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportintercepetor);
        registry.addInterceptor(requiredintercepetor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }

}
