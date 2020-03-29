package com.cuijing.sundial_dream.common.error;


import java.util.Optional;

public interface Errors {
    static ErrorDetail with(int status, String message) {
        return with(status, status, message, (Object)null);
    }

    static ErrorDetail with(int status, int code, String message) {
        return with(status, status, message, (Object)null);
    }

    static ErrorDetail with(int status, int code, String message, Object data) {
        return new Error(code, status, message, data, (String)null, 0L);
    }

    static ErrorDetail with(int code, int status, String message, Object data, String path, long timestamp) {
        return new Error(code, status, message, data, path, timestamp);
    }

    static Optional<ErrorDetail> resolve(Exception ex) {
        boolean max = true;
        Object cause = ex;

        do {
            if (cause == null) {
                return Optional.empty();
            }

            cause = ((Throwable)cause).getCause();
        } while(!(cause instanceof ErrorDetailException));

        return Optional.of(((ErrorDetailException)cause).getDetail());
    }

    static Error.ErrorBuilder builder() {
        return Error.builder();
    }

    static Error.ErrorBuilder builder(ErrorDetail detail) {
        return builder().code(detail.getCode()).status(detail.getStatus()).message(detail.getMessage()).data(detail.getData()).path(detail.getPath()).timestamp(detail.getTimestamp());
    }

    static ErrorDetail badRequest() {
        return Errors.Holder.BAD_REQUEST;
    }

    static ErrorDetail notFound() {
        return Errors.Holder.NOT_FOUND;
    }

    static ErrorDetail tenantRequired() {
        return Errors.Holder.TENANT_REQUIRED;
    }

    static ErrorDetail forbidden() {
        return Errors.Holder.FORBIDDEN;
    }

    static ErrorDetail unauthorized() {
        return Errors.Holder.UNAUTHORIZED;
    }

    static ErrorDetail tokenInvalid() {
        return Errors.Holder.TOKEN_INVALID;
    }

    static ErrorDetail notImplemented() {
        return Errors.Holder.NOT_IMPLEMENTED;
    }

    static ErrorDetail serviceUnavailable() {
        return Errors.Holder.SERVICE_UNAVAILABLE;
    }

    static ErrorDetail methodNotAllowed() {
        return Errors.Holder.METHOD_NOT_ALLOWED;
    }

    static ErrorDetail conflict() {
        return Errors.Holder.CONFLICT;
    }

    static ErrorDetail notAcceptable() {
        return Errors.Holder.NOT_ACCEPTABLE;
    }

    static ErrorDetail unsupportedMediaType() {
        return Errors.Holder.UNSUPPORTED_MEDIA_TYPE;
    }

    static ErrorDetail internalServerError() {
        return Errors.Holder.INTERNAL_SERVER_ERROR;
    }

    static ErrorDetail gone() {
        return Errors.Holder.GONE;
    }

    public static class Holder {
        private static final ErrorDetail BAD_REQUEST = Errors.with(400, "错误的请求");
        private static final ErrorDetail UNAUTHORIZED = Errors.with(401, "未授权访问");
        private static final ErrorDetail TOKEN_INVALID = Errors.with(402, "凭证不存在或已过期");
        private static final ErrorDetail FORBIDDEN = Errors.with(403, "拒绝访问");
        private static final ErrorDetail NOT_FOUND = Errors.with(404, "内容未找到");
        private static final ErrorDetail TENANT_REQUIRED = Errors.with(400, "需要租户信息");
        private static final ErrorDetail METHOD_NOT_ALLOWED = Errors.with(405, "请求方法不支持");
        private static final ErrorDetail CONFLICT = Errors.with(409, "操作冲突");
        private static final ErrorDetail NOT_ACCEPTABLE = Errors.with(406, "不可接收的请求");
        private static final ErrorDetail GONE = Errors.with(410, "接口已不存在");
        private static final ErrorDetail UNSUPPORTED_MEDIA_TYPE = Errors.with(415, "不支持的请求类型");
        private static final ErrorDetail INTERNAL_SERVER_ERROR = Errors.with(500, "服务内部错误");
        private static final ErrorDetail NOT_IMPLEMENTED = Errors.with(501, "操作未实现");
        private static final ErrorDetail SERVICE_UNAVAILABLE = Errors.with(503, "服务暂不可用");

        public Holder() {
        }
    }
}
