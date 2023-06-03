package com.cy.studentsel.controler;

import com.cy.studentsel.controler.dto.UserDto;
import com.cy.studentsel.controler.ex.PasswordEmptyException;
import com.cy.studentsel.controler.ex.UserTypeException;
import com.cy.studentsel.controler.ex.UsernameEmptyException;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.handler.impl.AdminHandlerImpl;
import com.cy.studentsel.util.JsonResult;
import com.cy.studentsel.util.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/22 14:21
 */
@RestController
@RequestMapping("/api/verify")
public class VerifyController extends BaseController{
    @Resource
    private StudentHandler studentHandler;
    @Resource
    private TeacherHandler teacherHandler;
    @Resource
    private AdminHandler adminHandler;

    @PostMapping
    @ResponseBody
    public JsonResult<UserDto> login(@RequestBody UserDto dto) {
        // 检查用户名和密码是否为空
        if (dto.getUsername() == null || Objects.equals(dto.getUsername(), "")) {
            throw new UsernameEmptyException("用户名不能为空");
        }
        if (dto.getPassword() == null || Objects.equals(dto.getPassword(), "")) {
            throw new PasswordEmptyException("密码不能为空");
        }
        UserDto info = new UserDto();
        info.setUsername(dto.getUsername());
        info.setUserType(dto.getUserType());

        String msg = switch (dto.getUserType()) {
            case "student" -> studentHandler.login(dto.getUsername(), dto.getPassword());
            case "teacher" -> teacherHandler.login(dto.getUsername(), dto.getPassword());
            case "admin" -> adminHandler.login(dto.getUsername(), dto.getPassword());
            default -> "user_type_error";
        };
        if (Objects.equals(msg, "user_type_error")) {
            throw new UserTypeException("用户类型错误");
        }
        // if no exception is thrown, then login successfully
        info.setUserToken(TokenUtils.getToken(dto));
        return new JsonResult<>(200, "登录成功", info);
    }
}
