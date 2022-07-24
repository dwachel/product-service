package com.demo.ppv.product.infrastructure.exception;

public class HttpClientParseBodyException extends RuntimeException {
    public HttpClientParseBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
