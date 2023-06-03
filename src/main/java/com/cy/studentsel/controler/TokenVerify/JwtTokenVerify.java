package com.cy.studentsel.controler.TokenVerify;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.cy.studentsel.controler.ex.NoTokenException;
import com.cy.studentsel.controler.ex.VerifyTokenFailException;
import com.cy.studentsel.entity.AdminRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.entity.TeacherRecord;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.handler.ex.HandlerException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器，验证token
 * @author leaf-fulture
 * @date 2023/6/3 16:11
 */
public class JwtTokenVerify implements HandlerInterceptor {
    @Resource
    private AdminHandler adminHandler;
    @Resource
    private TeacherHandler teacherHandler;
    @Resource
    private StudentHandler studentHandler;

    private String VerifyType;

    public JwtTokenVerify(String verifyType) {
        VerifyType = verifyType;
    }

    public JwtTokenVerify() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod))
            return true;
        if (token == null || token.equals("")) {
            throw new NoTokenException("未登录");
        }

        String username;
        String userType;
        String password;
        try {
            username = JWT.decode(token).getClaim("username").asString();
            userType = JWT.decode(token).getClaim("userType").asString();
            // if the token is not for the requested user type, then throw exception
            if (!userType.equals(VerifyType)) {
                throw new VerifyTokenFailException("token验证失败");
            }
            // get the password from database
            switch (userType) {
                case "admin" -> {
                    AdminRecord adminRecord = adminHandler.getAdmin(username);
                    password = adminRecord.getPwd();
                }
                case "teacher" -> {
                    TeacherRecord teacherRecord = teacherHandler.getTeacher(username);
                    password = teacherRecord.getPwd();
                }
                case "student" -> {
                    StudentRecord studentRecord = studentHandler.getStudent(username);
                    password = studentRecord.getPwd();
                }
                default -> throw new VerifyTokenFailException("token验证失败");
            }
            // verify the token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
            jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new VerifyTokenFailException("token验证失败");
        }
        return true;
    }

}
