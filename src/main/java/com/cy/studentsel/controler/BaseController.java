package com.cy.studentsel.controler;

/**
 * @author leaf-fulture
 * @date 2023/5/22 14:58
 */

import com.cy.studentsel.controler.ex.ControllerException;
import com.cy.studentsel.handler.ex.HandlerException;
import com.cy.studentsel.util.JsonResult;
import org.apache.ibatis.exceptions.IbatisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Base controller
 */
public class BaseController {
    public static final Integer SUCCESS = 200;


    public static class pageInfo<E> {
        public int total;
        public List<E> list;
    };

    @ExceptionHandler(HandlerException.class)
    @ResponseBody
    public JsonResult<Void> handleHandlerException(Throwable e) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        switch (e.getClass().getSimpleName()) {
            case "UserNameNoFoundException" -> {
                jsonResult.setStatus(1000);
                jsonResult.setMsg(e.getMessage());
            }
            case "PasswordNoMatchException" -> {
                jsonResult.setStatus(1001);
                jsonResult.setMsg(e.getMessage());
            }
            case "UserIdDuplicateException" -> {
                jsonResult.setStatus(1002);
                jsonResult.setMsg(e.getMessage());
            }
            case "HandlerSqlException" -> {
                jsonResult.setStatus(5000);
                jsonResult.setMsg(e.getMessage());
            }
            default -> {
                jsonResult.setStatus(5001);
                jsonResult.setMsg("未知错误");
            }
        }
        return jsonResult;
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    public JsonResult<Void> handleControllerException(Throwable e) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        switch (e.getClass().getSimpleName()) {
            case "UsernameEmptyException" -> {
                jsonResult.setStatus(2000);
                jsonResult.setMsg(e.getMessage());
            }
            case "PasswordEmptyException" -> {
                jsonResult.setStatus(2001);
                jsonResult.setMsg(e.getMessage());
            }
            case "UserTypeEmptyException" -> {
                jsonResult.setStatus(2002);
                jsonResult.setMsg(e.getMessage());
            }
            default -> {
                jsonResult.setStatus(6004);
                jsonResult.setMsg("控制层未知错误");
            }
        }
        return jsonResult;
    }

//    @ExceptionHandler(SQLException.class)
//    @ResponseBody
//    public JsonResult<Void> handleSqlException(Throwable e) {
//        JsonResult<Void> jsonResult = new JsonResult<>();
//        jsonResult.setStatus(6000);
//        jsonResult.setMsg("Sql错误，填入的数据不符合要求");
//        return jsonResult;
//    }

}
