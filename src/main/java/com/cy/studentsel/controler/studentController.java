package com.cy.studentsel.controler;

import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:28
 */
@RestController
@RequestMapping("/student")
public class studentController {
    @Resource
    private StudentHandler studentHandler;

    @GetMapping("/index")
    private List<StudentRecord> index(){
        return studentHandler.queryAllStudent();
    }

    public String login(String ID, String pwd){
        return studentHandler.login(ID, pwd);
    }
}
