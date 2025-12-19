/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ANTONIO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeFeedbackResponseDto {
    private String concept;
    private String hint;
    private List<String> commonMistakes;
    private boolean isSimulated;
}