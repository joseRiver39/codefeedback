/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.service;

import com.axcumet.codefeedback.config.QwenProperties;
import com.axcumet.codefeedback.dto.CodeFeedbackRequestDto;
import com.axcumet.codefeedback.dto.CodeFeedbackResponseDto;
import com.axcumet.codefeedback.exception.QwenApiException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QwenService {

    private static final Logger log = LoggerFactory.getLogger(QwenService.class);

    private final WebClient webClient;
    private final QwenProperties props;

    @Autowired
    public QwenService(WebClient qwenWebClient, QwenProperties props) {
        this.webClient = qwenWebClient;
        this.props = props;
    }

    public Mono<CodeFeedbackResponseDto> getFeedback(CodeFeedbackRequestDto request) {
        String prompt = buildPrompt(request);
        log.debug("Prompt enviado a Qwen:\n{}", prompt);

        return webClient.post()
            .header("Authorization", "Bearer " + props.apiKey())
            .header("Content-Type", "application/json")
            .bodyValue(buildRequestBody(prompt))
            .retrieve()
            .bodyToMono(String.class)
            .map(this::parseResponse)
            .onErrorMap(ex -> new QwenApiException("Error llamando a Qwen API", ex));
    }

    private String buildPrompt(CodeFeedbackRequestDto req) {
        return """
        Eres un tutor de programación. Tu rol es ayudar con *pistas*, no soluciones.
        Reglas:
        - Explica el concepto involucrado.
        - Da máximo 2 pistas específicas.
        - NUNCA muestres código corregido.
        - Sé claro, breve y alentador.

        Tema: %s
        Lenguaje: %s

        Código del estudiante:
        ```%s
        %s
        ```

        Responde en JSON estricto con campos: {"concept": "...", "hint": "...", "commonMistakes": ["...", "..."]}
        """.formatted(req.topic(), req.language(), req.language(), req.code());
    }

    private Object buildRequestBody(String prompt) {
        // Formato DashScope: https://help.aliyun.com/zh/dashscope/developer-reference/api-details
        return Map.of(
            "model", props.model(),
            "input", Map.of("messages", List.of(
                Map.of("role", "system", "content", "Eres un tutor experto en programación."),
                Map.of("role", "user", "content", prompt)
            ))
        );
    }

    private CodeFeedbackResponseDto parseResponse(String rawResponse) {
        // Aquí iría el parseo real con Jackson (simplificado)
        // En prod: usa ObjectMapper + manejo de errores
        try {
            // Ejemplo de respuesta simulada para pruebas:
            if (rawResponse.contains("error")) throw new RuntimeException("API error");

            
            return new CodeFeedbackResponseDto(
                "Cierre léxico en JavaScript",
                "Revisa cómo 'var' afecta el alcance de variables en bucles. ¿El valor de 'i' se captura o se referencia?",
                List.of("Uso de 'var' en lugar de 'let'", "No entender el momento de ejecución de setTimeout"),
                false
            );
        } catch (Exception e) {
            log.error("Error parsing Qwen response", e);
            throw new QwenApiException("Respuesta inválida de Qwen", e);
        }
    }
}