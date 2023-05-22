package com.cy.studentsel.controler;

import com.cy.studentsel.controler.dto.UserDto;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.util.JsonResult;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:28
 */
@RestController
@RequestMapping("/student")
public class studentController {
    @Resource
    private StudentHandler studentHandler;

//    @GetMapping("/index")
//    private List<StudentRecord> index(){
//        return studentHandler.queryAllStudent();
//    }

    @PostMapping("/login")
    @ResponseBody
    public JsonResult<Void> login(@RequestBody UserDto dto){
        String msg = studentHandler.login(dto.getUsername(), dto.getPassword());
        if (Objects.equals(msg, "login_success")) {
            return new JsonResult<>(200, "登录成功", null);
        }
        return new JsonResult<>(500, "登录失败", null);
    }
}
