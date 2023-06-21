package com.example.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(LoggingInterceptor loggingInterceptor, Logbook logbook) {
        RestTemplate restTemplate = new RestTemplate();
        // Use created interceptor or the Logbook one.
        // restTemplate.setInterceptors(Collections.singletonList(loggingInterceptor));

        LogbookClientHttpRequestInterceptor interceptor = new LogbookClientHttpRequestInterceptor(logbook);
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}
