package com.demo.ppv.product.infrastructure.exception;

public class HttpClientResponseException extends RuntimeException {
    public HttpClientResponseException(String message, int code) {
        super(String.format("%s, response code: %d", message, code));
    }
}
