package com.cuijing.sundial_dream.common.error;


public interface CommonErrors extends ErrorDetail {
    ErrorDetail BAD_REQUEST = Errors.with(400, "错误的请求");
    ErrorDetail UNAUTHORIZED = Errors.with(401, "未授权访问");
    ErrorDetail TOKEN_INVALID = Errors.with(402, "凭证不存在或已过期");
    ErrorDetail FORBIDDEN = Errors.with(403, "拒绝访问");
    ErrorDetail NOT_FOUND = Errors.with(404, "内容未找到");
    ErrorDetail INVALID_ARGUMENT = Errors.with(408, "参数错误");
    ErrorDetail CONFLICT = Errors.with(409, "操作冲突");
    ErrorDetail INTERNAL_SERVER_ERROR = Errors.with(500, "服务内部错误");
}
