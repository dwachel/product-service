package com.demo.ppv.product.infrastructure.exception;

public class HttpClientConnectionException extends RuntimeException {
    public HttpClientConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
