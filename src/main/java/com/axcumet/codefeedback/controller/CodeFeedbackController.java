/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.controller;

import com.axcumet.codefeedback.dto.CodeFeedbackRequestDto;
import com.axcumet.codefeedback.dto.CodeFeedbackResponseDto;
import com.axcumet.codefeedback.service.CodeFeedbackService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author ANTONIO
 */
@RestController
public class CodeFeedbackController {
    
    private final CodeFeedbackService feedbackService;

    public CodeFeedbackController(CodeFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/api/feedback/code")
    public Mono<ResponseEntity<CodeFeedbackResponseDto>> evaluateCode(
        @Valid @RequestBody CodeFeedbackRequestDto request
    ) {
        return feedbackService.getFeedback(request)
            .map(ResponseEntity::ok);
    }
}
    
