package com.demo.ppv.product.infrastructure.config;

import com.demo.ppv.product.infrastructure.http.HttpRetryInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class HttpClientConfiguration {

    @Bean
    public OkHttpClient httpClient(HttpClientProperties properties) {
        var loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .readTimeout(Duration.ofSeconds(properties.getTimeout().getRead()))
                .callTimeout(Duration.ofSeconds(properties.getTimeout().getRead()))
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HttpRetryInterceptor())
                .build();
    }
}
