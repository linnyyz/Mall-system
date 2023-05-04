package com.riverbeside.bilibili.util;


import java.io.Serializable;

/**
 * Json格式的数据响应
 */
public class JsonResult<E> implements Serializable {
    /**响应的状态码*/
    private Integer state;

    /**描述信息*/
    private String message;

    /**任何对应的数据*/
    private E data;


    public JsonResult(JsonResult<?> jsonResult) {

    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public static JsonResult fail(String message){
        return new JsonResult(100,message);
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
