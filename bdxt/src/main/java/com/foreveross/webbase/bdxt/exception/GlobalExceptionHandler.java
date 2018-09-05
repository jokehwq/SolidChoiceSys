package com.foreveross.webbase.bdxt.exception;

import com.foreveross.webbase.bdxt.web.app.entity.response.ArgumentInvalidResult;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.CommonUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkun 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse MethodArgumentNotValidHandler(HttpServletRequest request,
                                                        MethodArgumentNotValidException exception) throws Exception {
        // 按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }
        return new CommonResponse.Builder(false, CommonUtils.validListToString(invalidArguments),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    @ExceptionHandler
    @ResponseBody
    public CommonResponse Exception(Exception ex) {
        logger.error("错误信息：" + ex);
        // 根据不同错误转向不同页面
        if (ex instanceof NullPointerException) {
            return new CommonResponse.Builder(false, ConstantsEnum.NULL_ERROR.getMsg() + ex.getMessage(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        } else if (ex instanceof IllegalArgumentException) {
            return new CommonResponse.Builder(false, ConstantsEnum.SYSTEM_ERROR.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        } else if (ex instanceof org.springframework.web.multipart.MultipartException) {
            return new CommonResponse.Builder(false, ConstantsEnum.UPLOAD_IMG_ERROR.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        } else if (ex instanceof UnknownAccountException) {
            return new CommonResponse.Builder(false, ConstantsEnum.LOGIN_ERROR_ACCOUNT_NO_EXIST.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        } else if (ex instanceof IncorrectCredentialsException) {
            return new CommonResponse.Builder(false, ConstantsEnum.LOGIN_ERROR_UNKOWNACCOUNT.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        } else {
            return new CommonResponse.Builder(false, ConstantsEnum.SYSTEM_ERROR.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        }
    }
}
