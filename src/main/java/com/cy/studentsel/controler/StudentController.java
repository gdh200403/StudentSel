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
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.min;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:28
 */

/**
 * All operation provided for student
 */
@RestController
@RequestMapping("api/student")
public class StudentController extends BaseController{
    @Resource
    private StudentHandler studentHandler;

    /**
     * student information
     */
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

    @DeleteMapping("/sc/delete/{student_id}/{course_id}")
    @ResponseBody
    public JsonResult<Void> deleteSC(@PathVariable String student_id, @PathVariable String course_id) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        studentHandler.deleteSC(student_id, course_id);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("删除成功");
        return jsonResult;
    }

    /**
     * find all selected course within the page range
     * @param page
     * @param pageSize
     * @param student_id
     * @return
     */
    @GetMapping("/page/sc")
    @ResponseBody
    public JsonResult<scInfo> findPageSC(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                                     @RequestParam String student_id
                                         ) {
        JsonResult<scInfo> jsonResult = new JsonResult<>();
        pageInfo<SCRecord> pageInfo = new pageInfo<>();
        List<SCRecord> list = studentHandler.querySCByStudentId(student_id);
        int pageStart = (page - 1) * pageSize;
        int limit = pageSize;
        pageInfo.list = list.subList(pageStart, min(pageStart + limit, list.size()));
        pageInfo.total = list.size();
        scInfo info = new scInfo(0, 0, 0, 0, 0, pageInfo);
        // calculate info
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
        // catch exception when list is empty
        try {
            info.arith_ave /= list.size();
            info.weighted_ave /= info.total_credit;
        }
        catch (Exception e) {
            info.arith_ave = 0;
            info.weighted_ave = 0;
        }
        jsonResult.setData(info);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }

    /**
     * course selected in current semester
     * @param student_id
     * @param term
     * @return
     */
    @GetMapping("/current/sc")
    @ResponseBody
    public JsonResult<List<SCRecord>> findCurrentSC(@RequestParam String student_id, @RequestParam String term) {
        JsonResult<List<SCRecord>> jsonResult = new JsonResult<>();
        List<SCRecord> list = studentHandler.querySCByStudentIdAndTerm(student_id, term);
        jsonResult.setData(list);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }


    /**
     * course selected in current semester
     * @param student_id
     * @param term
     * @return
     */
    @GetMapping("/current/credit")
    @ResponseBody
    public JsonResult<Integer> findCurrentCredit(@RequestParam String student_id, @RequestParam String term) {
        JsonResult<Integer> jsonResult = new JsonResult<>();
        List<SCRecord> list = studentHandler.querySCByStudentIdAndTerm(student_id, term);
        // calculate total credit
        int total_credit = 0;
        for (SCRecord record : list) {
            total_credit += record.getCredit();
        }
        jsonResult.setData(total_credit);
        jsonResult.setStatus(SUCCESS);
        jsonResult.setMsg("查询成功");
        return jsonResult;
    }


    /**
     * course available for selection with condition query
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
                                                     @RequestParam String term,
                                                     @RequestParam String type,
                                                     @RequestParam int credit,
                                                     @RequestParam String place
    ) {
        JsonResult<pageInfo<TCRecord>> jsonResult = new JsonResult<>();
        pageInfo<TCRecord> pageInfo = new pageInfo<>();
        TCRecord record = new TCRecord(teacher_id, course_id, teacher_name, course_name, term, type, credit, place);
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

}
