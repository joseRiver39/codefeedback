/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author ANTONIO
 */
@Configuration
@EnableConfigurationProperties(QwenProperties.class)
public class QwenApiConfig {

    @Bean
    public WebClient qwenWebClient(QwenProperties props) {
        return WebClient.builder()
            .baseUrl(props.apiUrl())
            .build();
    }
}
