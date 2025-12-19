/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.config;

/**
 *
 * @author ANTONIO
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qwen")
public record QwenProperties(
    String apiKey,
    String apiUrl,
    String model,
    long timeoutMs
) {}
