package com.cy.studentsel.Config;

import com.cy.studentsel.controler.TokenVerify.JwtTokenVerify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author leaf-fulture
 * @date 2023/6/3 16:30
 */
@Configuration
public class VerifyConfig implements WebMvcConfigurer {

    /**
     *  拦截请求，验证token，排除登录请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // if /api/admin is requested, then verify the token with admin role
        registry.addInterceptor(jwtTokenVerifyAdmin())
                .addPathPatterns("/api/admin/**")
                .excludePathPatterns("api/verify");
        // if /api/teacher is requested, then verify the token with teacher role
        registry.addInterceptor(jwtTokenVerifyTeacher())
                .addPathPatterns("/api/teacher/**")
                .excludePathPatterns("/api/verify");
        // if /api/student is requested, then verify the token with student role
        registry.addInterceptor(jwtTokenVerifyStudent())
                .addPathPatterns("/api/student/**")
                .excludePathPatterns("/api/verify");
    }

    @Bean
    public JwtTokenVerify jwtTokenVerify() {
        return new JwtTokenVerify();
    }

    @Bean
    public JwtTokenVerify jwtTokenVerifyAdmin() {
        return new JwtTokenVerify("admin");
    }

    @Bean
    public JwtTokenVerify jwtTokenVerifyTeacher() {
        return new JwtTokenVerify("teacher");
    }

    @Bean
    public JwtTokenVerify jwtTokenVerifyStudent() {
        return new JwtTokenVerify("student");
    }

}
