package com.encrypt.starter.response;

/**
 * ClassName: ResultData
 * Package: org.common.response
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/3/10 - 11:17
 * @Version: v1.0
 */

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一结果集返回
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应体
     */
    private T data;

    /**
     * 接口调用时间戳
     */
    private long timestamp;

    public ResultData(){
        /**
         * 设置当前系统时间戳
         */
        this.timestamp = System.currentTimeMillis();
    }

    public ResultData(T data){
        this();
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.message = ResponseCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public ResultData(ResponseCodeEnum responseCodeEnum){
        this();
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
        this.data = null;
    }

    public ResultData(int code,String message){
        this();
        this.code = code;
        this.message = message;
        this.data = null;
    }

    /**
     * 成功返回
     * @return
     * @param <T>
     */
    public static <T>  ResultData<T> success(){
        return new ResultData<>(ResponseCodeEnum.SUCCESS);
    }

    /**
     * 成功返回
     * @param data
     * @return
     * @param <T>
     */
    public static <T>  ResultData<T> success(T data){
        return new ResultData<>(data);
    }

    /**
     * 失败返回
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> fail(){
        return new ResultData<>(ResponseCodeEnum.FAIL);
    }

    /**
     * 失败返回
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> fail(int code, String message){
        return new ResultData<>(code, message);
    }
}
