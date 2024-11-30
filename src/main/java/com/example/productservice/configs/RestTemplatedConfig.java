package com.example.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplatedConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder().build();
    }

//    @Bean
//    public  RestTemplate getRestTemplate(){
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        return new RestTemplate(requestFactory);
//    }
}
