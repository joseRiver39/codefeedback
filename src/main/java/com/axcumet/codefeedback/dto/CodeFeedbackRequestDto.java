/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axcumet.codefeedback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author ANTONIO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Accessors(fluent = true)
public class CodeFeedbackRequestDto {

    @NotBlank(message = "El lenguaje no debe estar vacío")
    private String language;

    @NotBlank(message = "El tema no debe estar vacío")
    private String topic;

    @NotBlank(message = "El código no debe estar vacío")
    @Size(max = 5000, message = "El código no debe exceder 5000 caracteres")
    private String code;
}
