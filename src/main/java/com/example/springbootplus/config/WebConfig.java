package com.example.springbootplus.config;

import com.example.springbootplus.interceptor.AuthorityInterceptor;
import com.example.springbootplus.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    //登录拦截器无需拦截的url
    public static List<String> loginExcludePathPatterns = Arrays.asList(
            "/system/login",
            "/css/**",
            "/fonts/**",
            "/js/**",
            "/images/**",
            "/error",
            "/cpacha/generate_cpacha"
    );

    //登录拦截器无需拦截的url
    public static List<String> authorityExcludePathPatterns = Arrays.asList(
            "/system/login",
            "/css/**",
            "/fonts/**",
            "/js/**",
            "/images/**",
            "/error",
            "/cpacha/generate_cpacha",
            "/system/no_right",
            "/system/index"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(loginExcludePathPatterns);
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**").excludePathPatterns(authorityExcludePathPatterns);
    }

}
