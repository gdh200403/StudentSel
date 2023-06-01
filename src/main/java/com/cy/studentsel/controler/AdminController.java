package com.cy.studentsel.controler;

import com.cy.studentsel.entity.*;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.util.JsonResult;
import com.sun.net.httpserver.Authenticator;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
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


    /**
     * get page info of student with condition
     * @param page
     * @param pageSize
     * @param student_id
     * @param student_name
     * @param sex
     * @param age
     * @param major
     * @return
     */
    @GetMapping("/page/student")
    @ResponseBody
    public JsonResult<pageInfo<StudentRecord>> findPageStudent(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                               @RequestParam String student_id,
                                                               @RequestParam String student_name,
                                                               @RequestParam String sex,
                                                               @RequestParam Integer age,
                                                               @RequestParam String major
                                                               ) {
        JsonResult<pageInfo<StudentRecord>> jsonResult = new JsonResult<>();
        pageInfo<StudentRecord> pageInfo = new pageInfo<>();
        StudentRecord record = new StudentRecord(student_id, student_name, sex, age, major);
        List<StudentRecord> list = adminHandler.queryStudentByCondition(record);
        // get page info
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @PostMapping("/student/save")
    @ResponseBody
    public JsonResult<Void> addStudent(@RequestBody StudentRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.addStudent(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @PostMapping("/student/update")
    @ResponseBody
    public JsonResult<Void> updateStudent(@RequestBody StudentRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.updateStudent(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @DeleteMapping("/student/delete/{id}")
    @ResponseBody
    public JsonResult<Void> deleteStudent(@PathVariable String id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.deleteStudent(id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }

    /**
     * get page info of course Selection with condition
     * @param page
     * @param pageSize
     * @param student_id
     * @param student_name
     * @param course_id
     * @param course_name
     * @param teacher_id
     * @param teacher_name
     * @param type
     * @param credit
     * @param place
     * @return page info`
     */
    @GetMapping("/page/sc")
    @ResponseBody
    public JsonResult<pageInfo<SCRecord>> findPageSC(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                               @RequestParam String student_id,
                                                               @RequestParam String student_name,
                                                               @RequestParam String course_id,
                                                               @RequestParam String course_name,
                                                               @RequestParam String teacher_id,
                                                               @RequestParam String teacher_name,
                                                               @RequestParam String type,
                                                               @RequestParam int credit,
                                                               @RequestParam String place) {
        JsonResult<pageInfo<SCRecord>> jsonResult = new JsonResult<>();
        pageInfo<SCRecord> pageInfo = new pageInfo<>();
        SCRecord record = new SCRecord(student_id, course_id, teacher_id, student_name, course_name, teacher_name, type, credit, place);
        List<SCRecord> list = adminHandler.querySCByCondition(record);
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

    @PostMapping("/sc/save")
    @ResponseBody
    public JsonResult<Void> addSC(@RequestBody SCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.addSC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @PostMapping("/sc/update")
    @ResponseBody
    public JsonResult<Void> updateSC(@RequestBody SCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.updateSC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @DeleteMapping("/sc/delete/{student_id}/{course_id}")
    @ResponseBody
    public JsonResult<Void> deleteSC(@PathVariable String student_id, @PathVariable String course_id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.deleteSC(student_id, course_id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }

    /**
     * get page info of teacher with condition
     * @param page
     * @param pageSize
     * @param teacher_id
     * @param teacher_name
     * @param sex
     * @param age
     * @return
     */
    @GetMapping("/page/teacher")
    @ResponseBody
    public JsonResult<pageInfo<TeacherRecord>> findPageTeacher(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                               @RequestParam String teacher_id,
                                                               @RequestParam String teacher_name,
                                                               @RequestParam String sex,
                                                               @RequestParam Integer age
    ) {
        JsonResult<pageInfo<TeacherRecord>> jsonResult = new JsonResult<>();
        pageInfo<TeacherRecord> pageInfo = new pageInfo<>();
        TeacherRecord record = new TeacherRecord(teacher_id, teacher_name, sex, age);
        List<TeacherRecord> list = adminHandler.queryTeacherByCondition(record);
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

    @PostMapping("/teacher/save")
    @ResponseBody
    public JsonResult<Void> addTeacher(@RequestBody TeacherRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.addTeacher(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @PostMapping("/teacher/update")
    @ResponseBody
    public JsonResult<Void> updateTeacher(@RequestBody TeacherRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.updateTeacher(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @DeleteMapping("/teacher/delete/{id}")
    @ResponseBody
    public JsonResult<Void> deleteTeacher(@PathVariable String id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.deleteTeacher(id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }

    /**
     * get page info of teach course with condition
     * @param page
     * @param pageSize
     * @param teacher_id
     * @param course_id
     * @param teacher_name
     * @param course_name
     * @param type
     * @param credit
     * @param place
     * @return
     */
    @GetMapping("/page/tc")
    @ResponseBody
    public JsonResult<pageInfo<TCRecord>> findPageTC(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                     @RequestParam String teacher_id,
                                                     @RequestParam String course_id,
                                                     @RequestParam String teacher_name,
                                                     @RequestParam String course_name,
                                                     @RequestParam String type,
                                                     @RequestParam int credit,
                                                     @RequestParam String place
    ) {
        JsonResult<pageInfo<TCRecord>> jsonResult = new JsonResult<>();
        pageInfo<TCRecord> pageInfo = new pageInfo<>();
        TCRecord record = new TCRecord(teacher_id, course_id, teacher_name, course_name, type, credit, place);
        List<TCRecord> list = adminHandler.queryTCByCondition(record);
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @PostMapping("/tc/save")
    @ResponseBody
    public JsonResult<Void> addTC(@RequestBody TCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.addTC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @PostMapping("/tc/update")
    @ResponseBody
    public JsonResult<Void> updateTC(@RequestBody TCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.updateTC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @DeleteMapping("/tc/delete/{teacher_id}/{course_id}")
    @ResponseBody
    public JsonResult<Void> deleteTC(@PathVariable String teacher_id, @PathVariable String course_id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.deleteTC(teacher_id, course_id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }

    /**
     * get page info of course with condition
     * @param page
     * @param pageSize
     * @param course_id
     * @param course_name
     * @param type
     * @param credit
     * @return
     */
    @GetMapping("/page/course")
    @ResponseBody
    public JsonResult<pageInfo<CourseRecord>> findPageCourse(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                             @RequestParam String course_id,
                                                             @RequestParam String course_name,
                                                             @RequestParam String type,
                                                             @RequestParam int credit
    ) {
        JsonResult<pageInfo<CourseRecord>> jsonResult = new JsonResult<>();
        pageInfo<CourseRecord> pageInfo = new pageInfo<>();
        CourseRecord record = new CourseRecord(course_id, course_name, type, credit);
        List<CourseRecord> list = adminHandler.queryCourseByCondition(record);
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @PostMapping("/course/save")
    @ResponseBody
    public JsonResult<Void> addCourse(@RequestBody CourseRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.addCourse(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @PostMapping("/course/update")
    @ResponseBody
    public JsonResult<Void> updateCourse(@RequestBody CourseRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.updateCourse(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @DeleteMapping("/course/delete/{id}")
    @ResponseBody
    public JsonResult<Void> deleteCourse(@PathVariable String id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        adminHandler.deleteCourse(id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }
}
