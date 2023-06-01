package com.cy.studentsel.controler;

import com.cy.studentsel.entity.CourseRecord;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.entity.TCRecord;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.util.JsonResult;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class scInfo {
        int total_credit;
        int credit_passed;
        int credit_failed;
        int arith_ave;
        int weighted_ave;
        pageInfo<SCRecord> pageInfo;

    }

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
    public JsonResult<scInfo> findPageSC(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                                     @RequestParam String student_id) {
        JsonResult<scInfo> jsonResult = new JsonResult<>();
        pageInfo<SCRecord> pageInfo = new pageInfo<>();
        List<SCRecord> list = studentHandler.querySCByStudentId(student_id);
//        List<StudentRecord> list = adminHandler.queryAllStudent();
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        scInfo info = new scInfo(0, 0, 0, 0, 0, pageInfo);
        for (SCRecord record : list) {
            info.total_credit += record.getCredit();
            if (record.getGrade() == null || record.getGrade() == -1) {
                continue;
            }
            if (record.getGrade() >= 60) {
                info.credit_passed += record.getCredit();
            } else {
                info.credit_failed += record.getCredit();
            }
            info.arith_ave += record.getGrade();
            info.weighted_ave += record.getGrade() * record.getCredit();
        }
        info.arith_ave /= list.size();
        info.weighted_ave /= info.total_credit;
        jsonResult.setData(info);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }




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
        List<TCRecord> list = studentHandler.queryTCByCondition(record);
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
