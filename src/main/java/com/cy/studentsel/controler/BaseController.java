package com.cy.studentsel.controler;

/**
 * @author leaf-fulture
 * @date 2023/5/22 14:58
 */

import com.cy.studentsel.handler.ex.HandlerException;
import com.cy.studentsel.util.JsonResult;
import org.apache.ibatis.exceptions.IbatisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Base controller
 */
public class BaseController {
    public static final Integer SUCCESS = 200;

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


//    @ExceptionHandler(SQLException.class)
//    @ResponseBody
//    public JsonResult<Void> handleSqlException(Throwable e) {
//        JsonResult<Void> jsonResult = new JsonResult<>();
//        jsonResult.setStatus(6000);
//        jsonResult.setMsg("Sql错误，填入的数据不符合要求");
//        return jsonResult;
//    }

}
