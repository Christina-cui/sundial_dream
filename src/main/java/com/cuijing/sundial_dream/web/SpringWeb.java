package com.cuijing.sundial_dream.web;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public interface SpringWeb {

    static RequestAttributes currentRequestAttributes() throws IllegalStateException {
        return RequestContextHolder.currentRequestAttributes();
    }

    /** 用于获取当前请求对象和响应等 */
    static ServletRequestAttributes currentServletRequestAttributes() throws IllegalStateException {
        return (ServletRequestAttributes) currentRequestAttributes();
    }
}

