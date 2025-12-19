/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.service;



import com.axcumet.codefeedback.dto.CodeFeedbackRequestDto;
import com.axcumet.codefeedback.dto.CodeFeedbackResponseDto;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class QwenFallbackService implements CodeFeedbackService {

    private static final Map<String, CodeFeedbackResponseDto> DUMMY_DATA = Map.of(
        "js-loop-var",
        new CodeFeedbackResponseDto(
            "Ámbito de variables con 'var'",
            "¿El valor de 'i' se evalúa al crear la función o al ejecutarla? Revisa el concepto de *hoisting* y cierre léxico.",
            List.of("Captura por referencia", "Efecto de la mutabilidad de 'i'"),
            true
        )
    );

    @Override
    public Mono<CodeFeedbackResponseDto> getFeedback(CodeFeedbackRequestDto request) {
        // Simulación basada en contenido (ej: código que contiene 'var i')
        if (request.code().contains("var i") && request.code().contains("setTimeout")) {
            return Mono.just(DUMMY_DATA.get("js-loop-var"));
        }
        return Mono.just(new CodeFeedbackResponseDto(
            "Simulación genérica",
            "Revisa la lógica de control y condiciones límite.",
            List.of("Índices fuera de rango", "Variables no inicializadas"),
            true
        ));
    }
}
