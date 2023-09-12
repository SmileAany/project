package com.smile.project.common.utils.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/7 3:57 下午
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    FAIL(500, "error"),

    /**
     * 请求失败
     */
    HTTP_STATUS_400(400, "bad request"),

    /**
     * 没有身份验证
     */
    HTTP_STATUS_401(401, "unauthorized"),

    /**
     * body 参数确实
     */
    HTTP_STATUS_402(402, "request body miss"),

    /**
     * 无权限
     */
    HTTP_STATUS_403(403, "forbidden"),

    /**
     * 未找到
     */
    HTTP_STATUS_404(404,"not found"),

    /**
     * 请求方式不支持
     **/
    HTTP_STATUS_405(405, "method not allowed"),

    /**
     * 请求超时
     **/
    HTTP_STATUS_408(408, "Request Timeout"),

    /**
     * 参数错误
     **/
    HTTP_STATUS_422(422, "invalid param"),

    /**
     * 接口频率限制
     **/
    HTTP_STATUS_429(429, "too many request"),

    /**
     * 服务器异常
     */
    HTTP_STATUS_500(500, "server error");

    /**
     * 状态
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String message;
}