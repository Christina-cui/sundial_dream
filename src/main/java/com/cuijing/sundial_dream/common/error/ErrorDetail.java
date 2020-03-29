package com.cuijing.sundial_dream.common.error;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@JsonDeserialize(
        as = Error.class
)
public interface ErrorDetail {
    int getCode();

    int getStatus();

    String getMessage();

    Object getData();

    String getPath();

    long getTimestamp();

    default ErrorDetail withMessage(String format, Object... args) {
        return this.withMessage(String.format(format, args));
    }

    default ErrorDetail withMessage(String message) {
        return Errors.builder(this).message(message).build();
    }

    default ErrorDetail withTimestamp(long timestamp) {
        return Errors.builder(this).timestamp(timestamp).build();
    }

    default ErrorDetail withPath(String path) {
        return Errors.builder(this).path(path).build();
    }

    default ErrorDetail withCode(int code) {
        return Errors.builder(this).code(code).build();
    }

    default ErrorDetail withData(Object data) {
        return Errors.builder(this).data(data).build();
    }

    default ErrorDetailException asException() {
        return new ErrorDetailException(this.now());
    }

    default ErrorDetailException asException(Throwable cause) {
        return new ErrorDetailException(this.now(), cause);
    }

    default ErrorDetailException asException(String message) {
        return this.withMessage(message).asException();
    }

    default ErrorDetail check(boolean condition, String format, Object... args) {
        if (!condition) {
            throw this.asException(String.format(format, args));
        } else {
            return this;
        }
    }

    @Nonnull
    default <T> T checkNotNull(@Nullable T v, String message) {
        if (v == null) {
            throw this.asException(message);
        } else {
            return v;
        }
    }

    default ErrorDetail check(boolean condition, String message) {
        if (!condition) {
            throw this.asException(message);
        } else {
            return this;
        }
    }

    default ErrorDetail check(boolean condition) {
        if (!condition) {
            throw this.asException();
        } else {
            return this;
        }
    }

    default ErrorDetail now() {
        return this.withTimestamp(System.currentTimeMillis());
    }
}

