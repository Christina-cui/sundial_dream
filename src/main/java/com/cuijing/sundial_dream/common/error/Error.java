//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cuijing.sundial_dream.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public final class Error implements Serializable, ErrorDetail {
    private final int code;
    private final int status;
    private final String message;
    private final Object data;
    private final String path;
    @JsonInclude(Include.NON_DEFAULT)
    private final long timestamp;

    public static Error.ErrorBuilder builder() {
        return new Error.ErrorBuilder();
    }

    public int getCode() {
        return this.code;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public String getPath() {
        return this.path;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Error)) {
            return false;
        } else {
            Error other = (Error)o;
            if (this.getCode() != other.getCode()) {
                return false;
            } else if (this.getStatus() != other.getStatus()) {
                return false;
            } else {
                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$path = this.getPath();
                Object other$path = other.getPath();
                if (this$path == null) {
                    if (other$path != null) {
                        return false;
                    }
                } else if (!this$path.equals(other$path)) {
                    return false;
                }

                if (this.getTimestamp() != other.getTimestamp()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public int hashCode() {
        boolean PRIME = true ;
        int result = 1;
        result = result * 59 + this.getCode();
        result = result * 59 + this.getStatus();
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        long $timestamp = this.getTimestamp();
        result = result * 59 + (int)($timestamp >>> 32 ^ $timestamp);
        return result;
    }

    public String toString() {
        return "Error(code=" + this.getCode() + ", status=" + this.getStatus() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", path=" + this.getPath() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public Error(final int code, final int status, final String message, final Object data, final String path, final long timestamp) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = timestamp;
    }

    public Error withCode(final int code) {
        return this.code == code ? this : new Error(code, this.status, this.message, this.data, this.path, this.timestamp);
    }

    public Error withStatus(final int status) {
        return this.status == status ? this : new Error(this.code, status, this.message, this.data, this.path, this.timestamp);
    }

    public Error withMessage(final String message) {
        return this.message == message ? this : new Error(this.code, this.status, message, this.data, this.path, this.timestamp);
    }

    public Error withData(final Object data) {
        return this.data == data ? this : new Error(this.code, this.status, this.message, data, this.path, this.timestamp);
    }

    public Error withPath(final String path) {
        return this.path == path ? this : new Error(this.code, this.status, this.message, this.data, path, this.timestamp);
    }

    public Error withTimestamp(final long timestamp) {
        return this.timestamp == timestamp ? this : new Error(this.code, this.status, this.message, this.data, this.path, timestamp);
    }

    public static class ErrorBuilder {
        private int code;
        private int status;
        private String message;
        private Object data;
        private String path;
        private long timestamp;

        ErrorBuilder() {
        }

        public Error.ErrorBuilder code(final int code) {
            this.code = code;
            return this;
        }

        public Error.ErrorBuilder status(final int status) {
            this.status = status;
            return this;
        }

        public Error.ErrorBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public Error.ErrorBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public Error.ErrorBuilder path(final String path) {
            this.path = path;
            return this;
        }

        public Error.ErrorBuilder timestamp(final long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Error build() {
            return new Error(this.code, this.status, this.message, this.data, this.path, this.timestamp);
        }

        public String toString() {
            return "Error.ErrorBuilder(code=" + this.code + ", status=" + this.status + ", message=" + this.message + ", data=" + this.data + ", path=" + this.path + ", timestamp=" + this.timestamp + ")";
        }
    }
}
