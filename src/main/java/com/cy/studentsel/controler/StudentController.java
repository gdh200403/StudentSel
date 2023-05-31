package com.cy.studentsel.controler;

import com.cy.studentsel.entity.CourseRecord;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.min;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:28
 */
@RestController
@RequestMapping("api/student")
public class StudentController extends BaseController{
    @Resource
    private StudentHandler studentHandler;

    @GetMapping("/personal")
    @ResponseBody
    public JsonResult<StudentRecord> findPersonal(@RequestParam String student_id) {
        JsonResult<StudentRecord> jsonResult = new JsonResult<>();
        StudentRecord record = studentHandler.getStudent(student_id);
        jsonResult.setData(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @PostMapping("/sc/save")
    @ResponseBody
    public JsonResult<Void> addSC(@RequestBody SCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        studentHandler.addSC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("添加成功");
        return jsonResult;
    }

    @GetMapping("/page/sc")
    @ResponseBody
    public JsonResult<AdminController.pageInfo<SCRecord>> findPageSC(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                                     @RequestParam String student_id) {
        JsonResult<AdminController.pageInfo<SCRecord>> jsonResult = new JsonResult<>();
        AdminController.pageInfo<SCRecord> pageInfo = new AdminController.pageInfo<>();
        List<SCRecord> list = studentHandler.querySCByStudentId(student_id);
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
//
//    @GetMapping("/page/course")
//    @ResponseBody
//    public JsonResult<AdminController.pageInfo<CourseRecord>> findPageCourse(@RequestParam Integer page, @RequestParam Integer pageSize,
//                                                                             @RequestParam String course_id,
//                                                                             @RequestParam String course_name,
//                                                                             @RequestParam String type,
//                                                                             @RequestParam int credit,
//                                                                             @RequestParam int total_hours,
//                                                                             @RequestParam int teaching_hours,
//                                                                             @RequestParam int experiment_hours
//    ) {
//        JsonResult<AdminController.pageInfo<CourseRecord>> jsonResult = new JsonResult<>();
//        AdminController.pageInfo<CourseRecord> pageInfo = new AdminController.pageInfo<>();
//        CourseRecord record = new CourseRecord(course_id, course_name, type, credit);
//        List<CourseRecord> list = studentHandler.queryCourseByCondition(record);
//        int pageStart = (page - 1) * pageSize;
//        int limit = pageSize;
//        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
//        pageInfo.total = list.size();
//        jsonResult.setData(pageInfo);
//        jsonResult.setStatus(SUCCESS);
//        jsonResult.setMsg("查询成功");
//        return jsonResult;
//    }

}
