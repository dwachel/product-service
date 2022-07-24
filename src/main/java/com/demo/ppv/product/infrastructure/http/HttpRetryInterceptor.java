package com.demo.ppv.product.infrastructure.http;

import com.demo.ppv.product.infrastructure.exception.HttpClientResponseException;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class HttpRetryInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        var request = chain.request();

        var response = chain.proceed(request);

        var status = HttpStatus.valueOf(response.code());
        if (status.isError()) {
            throw new HttpClientResponseException("Request has failed.", response.code());
        }

        return response;
    }
}
