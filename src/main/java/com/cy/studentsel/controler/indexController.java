package com.cy.studentsel.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leaf-fulture
 * @date 2023/5/21 11:54
 */
@Controller
public class indexController{
    @RequestMapping("index")
    public String test(){
        return "index";
    }

    /**
     * 不要在类上标@RestController
     * 会把类的返回结果都变成JSON
     * 视图解析也就不会解析到页面了
     * 会直接返回页面
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public String test1(){
        return "index";
    }
}
