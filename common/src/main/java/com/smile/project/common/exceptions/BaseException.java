package com.smile.project.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/14 8:20 下午
 */
@Getter
@Setter
public class BaseException extends RuntimeException {
    private static final String MESSAGE = "系统异常： ";

    public BaseException() {
        super(MESSAGE);
    }

    public BaseException(String message) {
        super(StringUtils.isBlank(message) ? MESSAGE : message);
    }

    public BaseException(String message, Exception exception) {
        super(StringUtils.isBlank(message) ? MESSAGE : message,exception);
    }
}