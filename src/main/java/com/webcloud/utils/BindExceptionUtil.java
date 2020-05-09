package com.webcloud.utils;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 *
 */
@UtilityClass
public class BindExceptionUtil {

    /**
     * 获取批量异常信息
     *
     * @return
     */
    public static String getMessage(MethodArgumentNotValidException e) {
        List<String> msgList = Lists.newArrayList();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            msgList.add(objectError.getDefaultMessage());
        }
        return StringUtils.join(msgList.toArray(), ",");
    }

}