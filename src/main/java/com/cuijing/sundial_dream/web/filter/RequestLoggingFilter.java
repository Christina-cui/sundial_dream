package com.cuijing.sundial_dream.web.filter;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Setter
@Getter
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final String DEFAULT_BEFORE_MESSAGE_PREFIX = "Before request [";
    private static final String DEFAULT_BEFORE_MESSAGE_SUFFIX = "]";
    private static final String DEFAULT_AFTER_MESSAGE_PREFIX = "After request [";
    private static final String DEFAULT_AFTER_MESSAGE_SUFFIX = "]";
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 1500;
    private boolean includeQueryString = false;
    private boolean includeClientInfo = false;
    private boolean includePayload = false;
    private int maxPayloadLength = DEFAULT_MAX_PAYLOAD_LENGTH;
    private String beforeMessagePrefix = DEFAULT_BEFORE_MESSAGE_PREFIX;
    private String beforeMessageSuffix = DEFAULT_BEFORE_MESSAGE_SUFFIX;
    private String afterMessagePrefix = DEFAULT_AFTER_MESSAGE_PREFIX;
    private String afterMessageSuffix = DEFAULT_AFTER_MESSAGE_SUFFIX;

    public RequestLoggingFilter() {
        setIncludeQueryString(true);
        setIncludeClientInfo(true);
        setIncludePayload(true);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!log.isInfoEnabled() || request.getRequestURI().equals("/")) {
            // 该路径主要用于健康检查,不记录日志
            filterChain.doFilter(request, response);
            return;
        }
        Stopwatch stopwatch = null;
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;

        if (isIncludePayload()
                && isFirstRequest
                && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request);
        }
        if (isFirstRequest) {
            stopwatch = Stopwatch.createStarted();
            beforeRequest(requestToUse, response, filterChain);
        }
        try {
            filterChain.doFilter(requestToUse, response);
        } finally {
            if (!isAsyncStarted(requestToUse)) {
                assert stopwatch != null;
                afterRequest(requestToUse, response, filterChain, stopwatch.stop());
            }
        }
    }

    private void afterRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Stopwatch stop) {
        StringBuilder builder = new StringBuilder();
        builder.append(afterMessagePrefix);
        appendBasic(builder, request);
        appendPayload(builder, request);
        builder.append(";time=").append(stop.toString());
        builder.append(";status=").append(response.getStatus());
        builder.append(afterMessageSuffix);
        log.info(builder.toString());
    }

    private void beforeRequest(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        StringBuilder builder = new StringBuilder();
        builder.append(beforeMessagePrefix);
        appendBasic(builder, request);
        appendPayload(builder, request);
        builder.append(beforeMessageSuffix);
        log.info(builder.toString());
    }

    private void appendPayload(StringBuilder msg, HttpServletRequest request) {
        if (isIncludePayload()) {
            ContentCachingRequestWrapper wrapper =
                    WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }
                    msg.append(";payload=").append(payload);
                }
            }
        }
    }

    private void appendBasic(StringBuilder msg, HttpServletRequest request) {
        msg.append("method=").append(request.getMethod()).append(';');
        msg.append("uri=").append(request.getRequestURI());
        if (isIncludeQueryString() && request.getQueryString() != null) {
            msg.append('?').append(request.getQueryString());
        }
        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";client=").append(client).append(':').append(request.getRemotePort());
            }
            msg.append(";server=")
                    .append(request.getServerName())
                    .append(':')
                    .append(request.getServerPort());
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";session=").append(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                msg.append(";user=").append(user);
            }
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                msg.append(";auth=").append(authorization);
            }
            String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
            if (!Strings.isNullOrEmpty(contentType)) {
                msg.append(";type=").append(contentType);
            }
            String ua = request.getHeader(HttpHeaders.USER_AGENT);
            if (!Strings.isNullOrEmpty(ua)) {
                msg.append(";user-agent=").append(ua);
            }
        }
    }
}
