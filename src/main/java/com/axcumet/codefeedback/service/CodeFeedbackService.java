/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.service;

import com.axcumet.codefeedback.dto.CodeFeedbackRequestDto;
import com.axcumet.codefeedback.dto.CodeFeedbackResponseDto;
import reactor.core.publisher.Mono;

/**
 *
 * @author ANTONIO
 */
public interface CodeFeedbackService {
    Mono<CodeFeedbackResponseDto> getFeedback(CodeFeedbackRequestDto request);
}
