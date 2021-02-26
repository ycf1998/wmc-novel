package com.wmc.novel.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @className: CommResp
 * @description: 统一接口返回类
 * @author money
 * @date  2020年11月16日
 */
public class CommResp<T> implements Serializable {

    private static final long serialVersionUID = -4676749123489081638L;

    private final static String SUCCESS_CODE = "0";

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 分页信息
     */
    private PageInfo page;

    /**
     * 其他内容
     */
    private Map<String, Object> ext;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public CommResp(){
    	this.code = SUCCESS_CODE;
    	this.msg = "SUCCESS";
    }

    public CommResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommResp(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommResp(String code, String msg, T data, Map<String, Object> ext) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.ext = ext;
    }

    public CommResp(String code, String msg, T data, PageInfo pageInfo) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = pageInfo;
    }

    public CommResp(String code, String msg, T data, Map<String, Object> ext, PageInfo pageInfo) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    public CommResp(String code, String msg, T data, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = pageInfo;
    }

    public CommResp(String code, String msg, T data, Map<String, Object> ext, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.ext = ext;
        this.page = pageInfo;
    }

    //快速返回成功
    public static <T>CommResp success(){
        return new CommResp<T>(SUCCESS_CODE,"请求成功",null);
    }

    public static <T>CommResp success(T result){
        return new CommResp<T>(SUCCESS_CODE,"请求成功",result);
    }

    public static <T>CommResp success(String msg, T result){
        return new CommResp<T>(SUCCESS_CODE,msg,result);
    }

    public static <T>CommResp success(String msg, T result, Map<String, Object> extra){
        return new CommResp<T>(SUCCESS_CODE,msg,result, extra);
    }

    public static <T>CommResp success(T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(SUCCESS_CODE,"请求成功",result, pageInfo);
    }
    
    public static <T>CommResp success(T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(SUCCESS_CODE,"请求成功",result, extra,pageInfo);
    }

    public static <T>CommResp success(String msg, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(SUCCESS_CODE,msg,result,pageInfo);
    }

    public static <T>CommResp success(String msg, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(SUCCESS_CODE,msg,result, extra,pageInfo);
    }

    //快速返回失败状态
    public static <T>CommResp fail(){
        return new CommResp<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
    }

    public static <T>CommResp fail(T result){
        return new CommResp<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),result);
    }
    
    public static <T>CommResp fail(String msg, T result){
        return new CommResp<T>(ErrorCode.SYSTEM_ERROR.getCode(),msg,result);
    }

    public <T>CommResp fail(String msg, T result, Map<String, Object> extra){
        return new CommResp<T>(ErrorCode.SYSTEM_ERROR.getCode(),msg,result, extra);
    }

    public static <T>CommResp fail(ErrorCode errorCode){
        return new CommResp<T>(errorCode.getCode(),errorCode.getMessage());
    }

    public static <T>CommResp fail(ErrorCode errorCode, T result){
        return new CommResp<T>(errorCode.getCode(),errorCode.getMessage(),result);
    }

    public static <T>CommResp fail(ErrorCode errorCode, String msg, T result){
        return new CommResp<T>(errorCode.getCode(),msg,result);
    }

    public static <T>CommResp fail(ErrorCode errorCode, String msg, T result, Map<String, Object> extra){
        return new CommResp<T>(errorCode.getCode(),msg,result, extra);
    }

    //快速返回自定义状态码
    public static <T>CommResp result(String statusCode, String msg){
        return new CommResp<T>(statusCode,msg);
    }

    public static <T>CommResp result(String statusCode, String msg, T result){
        return new CommResp<T>(statusCode,msg,result);
    }

    public static <T>CommResp result(String statusCode, String msg, T result, Map<String, Object> extra){
        return new CommResp<T>(statusCode,msg,result, extra);
    }

    public static <T>CommResp result(String statusCode, String msg, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(statusCode,msg,result, pageInfo);
    }

    public static <T>CommResp result(String statusCode, String msg, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return new CommResp<T>(statusCode,msg,result, extra,pageInfo);
    }

    //快速返回Http状态
    public static <T>CommResp httpStatus(HttpStatus httpStatus, String msg){
        return result(httpStatus.toString(),msg);
    }

    public static <T>CommResp httpStatus(HttpStatus httpStatus, String msg, T result){
        return result(httpStatus.toString(),msg,result);
    }

    public static <T>CommResp httpStatus(HttpStatus httpStatus, String msg, T result, Map<String, Object> extra){
        return result(httpStatus.toString(),msg,result, extra);
    }

    public static <T>CommResp httpStatus(HttpStatus httpStatus, String msg, T result, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return result(httpStatus.toString(),msg,result, total, pageNo, pageSize);
    }

    public static <T>CommResp httpStatus(HttpStatus httpStatus, String msg, T result, Map<String, Object> extra, Long total, Integer pageNo, Integer pageSize){
        PageInfo pageInfo = new PageInfo(total, pageNo, pageSize);
        return result(httpStatus.toString(),msg,result, extra, total, pageNo, pageSize);
    }

}
