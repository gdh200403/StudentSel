package com.cy.studentsel.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cy.studentsel.controler.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author leaf-fulture
 * @date 2023/6/3 15:47
 */


@Component
public class TokenUtils {

    /**
     * 生成Token
     */
    public static String getToken(UserDto userDto) {
        String token="";
        token= JWT.create().withClaim("username", userDto.getUsername())// 保存用户信息
                .withClaim("userType", userDto.getUserType())// 保存用户信息
                .withExpiresAt(DateUtil.offsetMinute(new Date(), 15))// 设置过期时间为 15 分钟
                .sign(Algorithm.HMAC256(userDto.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }

}
