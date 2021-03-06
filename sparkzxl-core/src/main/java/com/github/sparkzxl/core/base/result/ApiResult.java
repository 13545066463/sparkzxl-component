package com.github.sparkzxl.core.base.result;

import com.github.sparkzxl.core.support.ResponseResultStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description: API接口响应结果
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:46:27
 */
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -219969750248052449L;
    private int code;
    private boolean success;
    private String msg;
    private T data;

    public ApiResult(int code, boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<>();
    }

    /**
     * 返回结果
     *
     * @param code
     * @param msg
     * @return ApiResult<T>
     * @author zhouxinlei
     * @date 2019/5/26 0026
     */
    public static ApiResult apiResult(int code, String msg) {
        return ApiResult.builder().code(code).msg(msg).build();
    }

    public static <T> ApiResult apiResult(int code, String msg, T data) {
        return ApiResult.builder().code(code).msg(msg).data(data).build();
    }

    public static <T> ApiResult apiResult(ResponseResultStatus resultStatus) {
        return ApiResult.builder().code(resultStatus.getCode()).msg(resultStatus.getMessage()).build();
    }

    public static <T> ApiResult apiResult(ResponseResultStatus resultStatus, T data) {
        return ApiResult.builder().code(resultStatus.getCode()).msg(resultStatus.getMessage()).data(data).build();
    }

    public static <T> ApiResult timeOut() {
        return ApiResult.apiResult(ResponseResultStatus.SERVICE_DEGRADATION);
    }

    public static class ApiResultBuilder<T> {
        private int code;
        private boolean success;
        private String msg;
        private T data;

        private ApiResultBuilder() {

        }

        public ApiResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public ApiResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ApiResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResult<T> build() {
            return new ApiResult<>(this.code, this.code == ResponseResultStatus.SUCCESS.getCode(), this.msg, this.data);
        }
    }
}
