package com.jozsefpajor.flaretask.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author PJ
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate( RestTemplateBuilder builder ) {
        RestTemplate restTemplate = builder.build();
        //required to handle gzip
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return restTemplate;
    }

}
