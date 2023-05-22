package com.cy.studentsel.controler;

import com.cy.studentsel.controler.dto.UserDto;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.handler.impl.AdminHandlerImpl;
import com.cy.studentsel.util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/22 14:21
 */
@RestController
@RequestMapping("/api/verify")
public class VerifyController {
    @Resource
    private StudentHandler studentHandler;
    @Resource
    private TeacherHandler teacherHandler;
    @Resource
    private AdminHandler adminHandler;

    @PostMapping
    @ResponseBody
    public JsonResult<Void> login(@RequestBody UserDto dto) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        String msg = switch (dto.getUserType()) {
            case "student" -> studentHandler.login(dto.getUsername(), dto.getPassword());
            case "teacher" -> teacherHandler.login(dto.getUsername(), dto.getPassword());
            case "admin" -> adminHandler.login(dto.getUsername(), dto.getPassword());
            default -> "user_type_error";
        };
        if (Objects.equals(msg, "login_success")) {
            jsonResult.setStatus(200);
            jsonResult.setMsg("登录成功");
            jsonResult.setData(null);
        } else {
            jsonResult.setStatus(500);
            jsonResult.setMsg("登录失败");
            jsonResult.setData(null);
        }
        return jsonResult;
    }
}
