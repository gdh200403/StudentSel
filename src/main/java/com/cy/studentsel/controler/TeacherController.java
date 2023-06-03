package com.cy.studentsel.controler;

import com.cy.studentsel.entity.CourseRecord;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.TCRecord;
import com.cy.studentsel.entity.TeacherRecord;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.min;

/**
 * @author leaf-fulture
 * @date 2023/5/28 16:54
 */

/**
 * All operation provided for teacher
 */
@RestController
@RequestMapping("api/teacher")
public class TeacherController extends BaseController{
    @Resource
    private TeacherHandler teacherHandler;

    @GetMapping("/personal")
    @ResponseBody
    public JsonResult<TeacherRecord> findPersonal(@RequestParam String teacher_id) {
        JsonResult<TeacherRecord> jsonResult = new JsonResult<>();
        TeacherRecord record = teacherHandler.queryTeacherByTeacherID(teacher_id);
        jsonResult.setData(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    @PostMapping("/tc/update")
    @ResponseBody
    public JsonResult<Void> updateTC(@RequestBody TCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        teacherHandler.updateTC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    @PostMapping("/sc/update")
    @ResponseBody
    public JsonResult<Void> updateSC(@RequestBody SCRecord record) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        teacherHandler.updateSC(record);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("修改成功");
        return jsonResult;
    }

    /**
     * All the student who choose the course
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
     * @return
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
        List<SCRecord> list = teacherHandler.querySCByCondition(record);
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

    /**
     * All the course the teacher teach
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
        List<TCRecord> list = teacherHandler.queryTCByCondition(record);
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        jsonResult.setData(pageInfo);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }


}
