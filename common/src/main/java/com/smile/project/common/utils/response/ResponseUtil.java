package com.smile.project.common.utils.response;

import com.smile.project.common.utils.response.enums.ResponseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/7 3:57 下午
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseUtil <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ResponseUtil() {}

    /**
     * response timestamp.
     */
    @ApiModelProperty(value = "响应时间戳", dataType = "String", required = true, example = "1669369247829")
    private Long timestamp;

    /**
     * response status
     */
    @ApiModelProperty(value = "返回码", dataType = "String", required = true, example = "success")
    private String status;

    /**
     * response status
     */
    @ApiModelProperty(value = "返回code", dataType = "int", required = true, example = "200")
    private Integer code;

    /**
     * response message.
     */
    @ApiModelProperty(value = "提示信息", dataType = "String", required = true, example = "success")
    private String message;

    /**
     * response data.
     */
    @ApiModelProperty(value = "返回值", required = true)
    private T data;

    /**
     * 接口成功
     * @description 接口成功
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> success() {
        return success(null,null);
    }

    /**
     * 接口成功
     * @description 接口成功
     * @author smile
     * @date 2023/2/22
     * @param data 接口返回的数据
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> success(T data) {
        return success(data,null);
    }

    /**
     * 接口成功
     * @description 接口成功
     * @author smile
     * @date 2023/2/22
     * @param data 接口返回的数据
     * @param message 接口message
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> success(T data,String message) {
        return ResponseUtil.<T>builder().data(data)
                .message( message == null ?  ResponseStatus.SUCCESS.getMessage() : message)
                .status(ResponseStatus.SUCCESS.getMessage())
                .code(ResponseStatus.SUCCESS.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 接口失败
     * @description 接口失败
     * @author smile
     * @date 2023/2/22
     * @param data 接口返回的数据
     * @param message 接口message
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> fail(T data,String message) {
        return fail(data,message,null);
    }

    /**
     * 接口失败
     * @description 接口失败
     * @author smile
     * @date 2023/2/22
     * @param data 接口返回的数据
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> fail(T data) {
        return fail(data,null,null);
    }

    /**
     * 接口失败
     * @description 接口失败
     * @author smile
     * @date 2023/2/22
     * @param data 接口返回的数据
     * @param message 接口message
     * @param code 接口code
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> fail(T data,String message,Integer code) {
        return ResponseUtil.<T>builder().data(data)
                .message( message == null ?  ResponseStatus.FAIL.getMessage() : message)
                .status(ResponseStatus.FAIL.getMessage())
                .code(code == null ? ResponseStatus.FAIL.getCode() : code)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 接口请求失败
     * @description 接口请求失败
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> badRequest() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_400.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 没有身份验证
     * @description 没有身份验证
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> unauthorized() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_401.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 请求体缺失
     * @description 请求体缺失
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> bodyMiss() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_402.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 暂无接口权限
     * @description 暂无接口权限
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> forbidden() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_403.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 404
     * @description 404
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> notFound() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_404.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 请求方式允许
     * @description
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> methodNotAllowed() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_405.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 接口超时
     * @description 接口超时
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> timeout() {
        return ResponseUtil.<T>builder()
                .message(ResponseStatus.HTTP_STATUS_408.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 参数错误
     * @description 参数错误
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> unprocessable() {
        return ResponseUtil.<T>builder().data(null)
                .message(ResponseStatus.HTTP_STATUS_422.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 参数错误
     * @description 参数错误
     * @author smile
     * @date 2023/2/22
     * @param message message
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> unprocessable(String message) {
        return ResponseUtil.<T>builder().data(null)
                .message(StringUtils.isBlank(message) ? ResponseStatus.HTTP_STATUS_422.getMessage() : message)
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .build();
    }

    /**
     * 接口频率限制
     * @description 接口频率限制
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> manyRequest() {
        return ResponseUtil.<T>builder().data(null)
                .message(ResponseStatus.HTTP_STATUS_429.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.FAIL.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 系统异常
     * @description
     * @author smile
     * @date 2023/2/22
     * @return com.fs.learn.swagger.utils.response.ResponseUtil<T>
     **/
    public static <T> ResponseUtil<T> error() {
        return ResponseUtil.<T>builder().data(null)
                .message(ResponseStatus.HTTP_STATUS_500.getMessage())
                .status(ResponseStatus.FAIL.getMessage())
                .code(ResponseStatus.HTTP_STATUS_500.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}