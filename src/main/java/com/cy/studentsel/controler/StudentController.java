package com.cy.studentsel.controler;

import com.cy.studentsel.handler.StudentHandler;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:28
 */
@RestController
@RequestMapping("api/student")
public class StudentController {
    @Resource
    private StudentHandler studentHandler;

}
