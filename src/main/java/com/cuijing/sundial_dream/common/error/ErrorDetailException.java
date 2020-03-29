package com.cuijing.sundial_dream.common.error;


public class ErrorDetailException extends RuntimeException{
    private final ErrorDetail detail;

    public ErrorDetailException(ErrorDetail detail) {
        super(detail.getCode() + ": " + detail.getMessage());
        this.detail = detail;
    }

    public ErrorDetailException(ErrorDetail detail, Throwable cause) {
        super(detail.getCode() + ": " + detail.getMessage(), cause);
        this.detail = detail;
    }

    public ErrorDetail getDetail() {
        return this.detail ;
    }
}
