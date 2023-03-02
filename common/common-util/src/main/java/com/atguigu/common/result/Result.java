package com.atguigu.common.result;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-02 17:07
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局同意返回结果类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    /*
     * public class Test1<T> {
     *   private T name;
     *   public T getName(T name){
     *       return name;
     *   }
     *   public static <T> void printA(T a){
     *       System.out.println(a);
     *   }
     * }
     * 对于声明了<T>的方法，表示这个方法声明为泛型方法
     * 但是对于没有带static的方法属于类的一部分，而类已经声明为泛型，故不用加上<T>；而带有static的方法不属于
     * 类的一部分，需要手动在statci后加上<T>声明这个方法为泛型方法
     * */
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);

        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg) {
        this.message = msg;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

}
