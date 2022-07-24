package com.demo.ppv.product.infrastructure.http;

import com.demo.ppv.product.infrastructure.config.HttpClientProperties;
import com.demo.ppv.product.infrastructure.exception.HttpClientConnectionException;
import com.demo.ppv.product.infrastructure.exception.HttpClientResponseException;
import dev.failsafe.RetryPolicy;
import dev.failsafe.okhttp.FailsafeCall;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class HttpClientGateway {

    private final OkHttpClient httpClient;
    private final RetryPolicy<Response> retryPolicy;

    public HttpClientGateway(OkHttpClient httpClient, HttpClientProperties httpClientProperties) {
        this.httpClient = httpClient;
        retryPolicy = setupRetryPolicy(httpClientProperties);
    }

    public String get(Request request) {
        var call = httpClient.newCall(request);
        try (Response response = FailsafeCall.with(retryPolicy).compose(call).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            }
            throw new HttpClientResponseException("Request has failed", response.code());
        } catch (IOException e) {
            throw new HttpClientConnectionException("Request connection has failed", e);
        }
    }

    private RetryPolicy<Response> setupRetryPolicy(HttpClientProperties httpClientProperties) {
        return RetryPolicy.<Response>builder()
                .handle(List.of(HttpClientResponseException.class, SocketTimeoutException.class))
                .withBackoff(Duration.ofSeconds(httpClientProperties.getRetry().getRetryDelay()),
                        Duration.ofSeconds(httpClientProperties.getRetry().getMaxRetryDelay()))
                .withMaxRetries(httpClientProperties.getRetry().getMaxRetries())
                .onRetry(e -> log.warn("Retrying, count: [{}]", e.getAttemptCount()))
                .onFailedAttempt(e -> log.error("Execution failed", e.getLastException()))
                .onRetriesExceeded(e -> log.error("Number of retries exceeded, count: [{}]", e.getExecutionCount()))
                .build();
    }
}
