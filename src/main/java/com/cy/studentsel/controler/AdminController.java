package com.cy.studentsel.controler;

import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.entity.TeacherRecord;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.util.JsonResult;
import com.sun.net.httpserver.Authenticator;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

import static java.lang.Integer.min;

/**
 * @author leaf-fulture
 * @date 2023/5/24 19:00
 */
@RestController
@RequestMapping("api/admin")
public class AdminController extends BaseController{
    @Resource
    private AdminHandler adminHandler;
    @Resource
    private TeacherHandler teacherHandler;
    @Resource
    private StudentHandler studentHandler;

    public static class pageInfo<E> {
        public int total;
        public List<E> list;
    };

    @GetMapping("/page/student")
    @ResponseBody
    public JsonResult<pageInfo<StudentRecord>> findPageStudent(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                               @RequestParam String id,
                                                               @RequestParam String name,
                                                               @RequestParam String sex,
                                                               @RequestParam Integer age,
                                                               @RequestParam String major
                                                               ) {
        JsonResult<pageInfo<StudentRecord>> jsonResult = new JsonResult<>();
        pageInfo<StudentRecord> pageInfo = new pageInfo<>();
        StudentRecord record = new StudentRecord(id, name, sex, age, major);
        List<StudentRecord> list = adminHandler.queryStudentByCondition(record);
//        List<StudentRecord> list = adminHandler.queryAllStudent();
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @GetMapping("/page/teacher")
    @ResponseBody
    public JsonResult<pageInfo<TeacherRecord>> findPageTeacher(@RequestParam Integer page, @RequestParam Integer pageSize) {
        JsonResult<pageInfo<TeacherRecord>> jsonResult = new JsonResult<>();
        pageInfo<TeacherRecord> pageInfo = new pageInfo<>();
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = adminHandler.queryTeacherByPage(pageStart, limit);
        pageInfo.total = adminHandler.queryAllTeacher().size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }
}
