package com.fehead.controller;

import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    // 定义exceptionHandler来解决controller层中未被吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerExcepetion(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException BusinessException = (BusinessException)ex;
            responseData.put("errorCode", BusinessException.getErrorCode());
            responseData.put("errorMsg", BusinessException.getErrorMsg());
        } else {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errorMsg", EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }

        return CommonReturnType.creat(responseData,"fail");
    }
}
