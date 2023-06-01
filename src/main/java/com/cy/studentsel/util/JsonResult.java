package com.cy.studentsel.util;

import java.io.Serializable;

/**
 * @author leaf-fulture
 * @date 2023/5/21 21:34
 */

/**
 * JSON object interact with frontend
 * @param <E>
 */
public class JsonResult<E> implements Serializable {
    private Integer status;
    private String msg;
    private E data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public JsonResult() {
    }

    public JsonResult(Integer status) {
        this.status = status;
    }

    public JsonResult(Throwable e) {
        this.msg = e.getMessage();
    }

    public JsonResult(Integer status, E data) {
        this.status = status;
        this.data = data;
    }

    public JsonResult(Integer status, String msg, E data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
