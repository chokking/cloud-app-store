package com.webcloud.error;

import com.google.common.collect.Lists;
import com.webcloud.utils.BindExceptionUtil;
import com.webcloud.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 全局异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseVo {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseVo bizExceptionHandler(BizException e) {
        String errorMsg = e.getErrorMsg();
        log.error("自定义异常:{}", errorMsg);
        return failed(errorMsg);
    }

    /**
     * 参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = BindExceptionUtil.getMessage(e);
        log.error("参数校验异常validated:{}", message);

        return failed(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseVo handleConstraintViolationException(HttpServletResponse resp, ConstraintViolationException ex) throws IOException {
        String msg = ex.getMessage();
        log.error("参数校验异常valid:{}", msg);
        List<String> msgList = Lists.newArrayList();
        Arrays.stream(StringUtils.split(msg, ",")).forEach(s -> msgList.add(StringUtils.split(s, ": ")[1]));
        return failed(StringUtils.join(msgList.toArray(), ","));
    }

    /**
     * 请求方式异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseVo httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式异常:{}", e);
        return failed("请求方式错误");
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseVo exceptionHandler(NullPointerException e) {
        log.error("空指针异常:{}", e);
        return failed(e.getMessage());
    }

    /**
     * 处理body没有携带参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseVo HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("body没有携带参数异常:{}", e);
        return failed("body没有携带参数");
    }

}